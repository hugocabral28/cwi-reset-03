package br.com.cwi.reset.hugocabral.service;

import br.com.cwi.reset.hugocabral.model.Ator;
import br.com.cwi.reset.hugocabral.repository.AtorRepository;
import br.com.cwi.reset.hugocabral.response.AtorEmAtividade;
import br.com.cwi.reset.hugocabral.validator.Constantes;
import br.com.cwi.reset.hugocabral.model.StatusCarreira;
import br.com.cwi.reset.hugocabral.exception.*;
import br.com.cwi.reset.hugocabral.exception.comum.*;
import br.com.cwi.reset.hugocabral.request.AtorRequest;
import br.com.cwi.reset.hugocabral.validator.BasicInfoRequiredValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class AtorService {

    @Autowired
    private AtorRepository repository;
    private BasicInfoRequiredValidator validator;


    public AtorService() {
        this.validator = new BasicInfoRequiredValidator();
    }




    public void criarAtor(AtorRequest atorRequest) throws Exception {

        /* ### Validações ### */
        validator.validaCamposObrigatorios(atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getAnoInicioAtividade(), TipoDominioException.ATOR);
        validator.validaDataNascimento(atorRequest.getDataNascimento(), TipoDominioException.ATOR);
        validator.validaNomeESobrenome(atorRequest.getNome(), TipoDominioException.ATOR);
        validator.validaAnoInicioAtividade(atorRequest.getDataNascimento(), atorRequest.getAnoInicioAtividade(), TipoDominioException.ATOR);

        if (atorRequest.getStatusCarreira() == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_STATUS_CARREIRA);
        }

        final List<Ator> atoresCadastrados = repository.findAll();

        for (Ator atorCadastrado : atoresCadastrados) {
            if (atorCadastrado.getNome().equalsIgnoreCase(atorRequest.getNome())) {
                throw new CadastroDuplicadoException(TipoDominioException.ATOR.getSingular(), atorRequest.getNome());
            }
        }

        /* ### Cadastrando ### */
        final Ator ator = new Ator(atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
        repository.save(ator);

    }

    // Demais métodos da classe
    public List<AtorEmAtividade> listarAtoresEmAtividade(Optional<String> filtroNome) throws Exception {
        final List<Ator> atoresCadastrados = repository.findAll();

        if (atoresCadastrados.isEmpty()) {
            throw new SemCadastroException(TipoDominioException.ATOR.getSingular(), TipoDominioException.ATOR.getPlural());
        }

        final List<AtorEmAtividade> retorno = new ArrayList<>();

        if (filtroNome.isPresent()) {
            for (Ator ator : atoresCadastrados) {
                //Verificando se o filtroNome informado contem no nome
                final boolean containsFilter = atoresCadastrados.stream()
                        .allMatch(atorlistadoFilter -> ator.getNome()
                                .toLowerCase(Locale.ROOT)
                                .contains(filtroNome.get().toLowerCase(Locale.ROOT)));

                final boolean emAtividade = StatusCarreira.EM_ATIVIDADE.equals(ator.getStatusCarreira());
                if (containsFilter && emAtividade) {
                    retorno.add(new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()));
                }
            }
        } else {
            for (Ator ator : atoresCadastrados) {
                final boolean emAtividade = StatusCarreira.EM_ATIVIDADE.equals(ator.getStatusCarreira());
                if (emAtividade) {
                    retorno.add(new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()));
                }
            }
        }

        if (retorno.isEmpty() && filtroNome != null) {
            throw new FiltroNomeException(TipoDominioException.ATOR.getSingular(), filtroNome.get());
        }

        return retorno;

    }

    public Ator consultarAtor(Integer id) throws Exception {

        if (id == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_ID);
        }

        final List<Ator> atores = repository.findAll();

        for (Ator ator : atores) {
            if (id.equals(ator.getId())) {
                return ator;
            }
        }

        throw new ConsultaIdException(TipoDominioException.ATOR.getSingular(), id);

    }

    public List<Ator> consultarAtores() throws SemCadastroException {
        List<Ator> list = repository.findAll();
        if (list.isEmpty()) {
            throw new SemCadastroException(TipoDominioException.ATOR.getSingular(), TipoDominioException.ATOR.getPlural());
        }
        return list;
    }

}
