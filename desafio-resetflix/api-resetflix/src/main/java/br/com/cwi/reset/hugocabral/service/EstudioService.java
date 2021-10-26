package br.com.cwi.reset.hugocabral.service;

import br.com.cwi.reset.hugocabral.model.*;
import br.com.cwi.reset.hugocabral.exception.*;
import br.com.cwi.reset.hugocabral.repository.EstudioRepository;
import br.com.cwi.reset.hugocabral.request.EstudioRequest;
import br.com.cwi.reset.hugocabral.validator.EstudioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class EstudioService {

    @Autowired
    private EstudioRepository repository;
    private EstudioValidator validator;

    public EstudioService() {
        this.validator = new EstudioValidator();
    }

    public void criarEstudio(EstudioRequest estudioRequest) throws Exception {
        /* ### Validações ### */
        validator.validaCamposObrigatorios(estudioRequest);
        validator.validaDataCriacao(estudioRequest.getDataCriacao(), TipoDominioException.ESTUDIO);
        validaDuplicidadeCadastro(estudioRequest);

        /* ### Cadastrando ### */
        final Estudio estudio = new Estudio(estudioRequest.getNome(), estudioRequest.getDescricao(), estudioRequest.getDataCriacao(), estudioRequest.getStatusAtividade());
        repository.save(estudio);
    }

    public List<Estudio> consultarEstudios(Optional<String> filtroNome) throws Exception {
        final List<Estudio> estudiosCadastrados = repository.findAll();

        if (estudiosCadastrados.isEmpty()) {
            throw new SemCadastroException(TipoDominioException.ESTUDIO.getSingular(), TipoDominioException.ESTUDIO.getPlural());
        }

        final List<Estudio> retorno = new ArrayList<>();

        if (filtroNome.isPresent()) {
            for (Estudio estudio : estudiosCadastrados) {
                //Verificando se o filtroNome informado contem no nome
                final boolean containsFilter = estudiosCadastrados.stream()
                        .allMatch(atorlistadoFilter -> estudio.getNome()
                                .toLowerCase(Locale.ROOT)
                                .contains(filtroNome.get().toLowerCase(Locale.ROOT)));

                if (containsFilter) {
                    retorno.add(estudio);
                }
            }
        } else {
            retorno.addAll(estudiosCadastrados);
        }

        if (retorno.isEmpty() && filtroNome != null) {
            throw new FiltroNomeException(TipoDominioException.ESTUDIO.getSingular(), filtroNome.get());
        }

        return retorno;
    }

    public Estudio consultarEstudio(Integer id) throws Exception {

        if (id == null) {
            throw new ConsultaIdException(TipoDominioException.ESTUDIO.getSingular(), id);
        }

        final List<Estudio> estudios = repository.findAll();

        for (Estudio estudio : estudios) {
            if (id.equals(estudio.getId())) {
                return estudio;
            }
        }

        throw new ConsultaIdException(TipoDominioException.ESTUDIO.getSingular(), id);

    }

    private void validaDuplicidadeCadastro(EstudioRequest estudioRequest) throws CadastroDuplicadoException {
        String nomeDoEstudio = estudioRequest.getNome();
        List<Estudio> estudios = repository.findAll();
        for (Estudio estudio : estudios) {
            if (estudio.getNome().toLowerCase(Locale.ROOT).equals(nomeDoEstudio.toLowerCase(Locale.ROOT))) {
                throw new CadastroDuplicadoException(TipoDominioException.ESTUDIO.getSingular(), nomeDoEstudio);
            }
        }
    }

}
