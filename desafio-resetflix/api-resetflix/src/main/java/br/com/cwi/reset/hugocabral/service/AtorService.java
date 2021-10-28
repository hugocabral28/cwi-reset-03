package br.com.cwi.reset.hugocabral.service;

import br.com.cwi.reset.hugocabral.model.Ator;
import br.com.cwi.reset.hugocabral.repository.AtorRepository;
import br.com.cwi.reset.hugocabral.response.AtorEmAtividade;
import br.com.cwi.reset.hugocabral.validator.Constantes;
import br.com.cwi.reset.hugocabral.model.StatusCarreira;
import br.com.cwi.reset.hugocabral.exception.*;
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
    private AtorRepository atorRepository;

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
        validaDuplicidadeCadastro(atorRequest);
        if (atorRequest.getStatusCarreira() == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_STATUS_CARREIRA);
        }

        /* ### Cadastrando ### */
        final Ator ator = new Ator(atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
        atorRepository.save(ator);

    }

    // Demais métodos da classe
    public List<AtorEmAtividade> listarAtoresEmAtividade(Optional<String> filtroNome) throws Exception {
        final List<Ator> atoresCadastrados = atorRepository.findAll();

        if (atoresCadastrados.isEmpty()) {
            throw new SemCadastroException(TipoDominioException.ATOR.getSingular(), TipoDominioException.ATOR.getPlural());
        }
        //Verificando sem tem Ator em atividade
        List<AtorEmAtividade> listarAtoresEmAtividade = atorRepository.findByStatusCarreira(StatusCarreira.EM_ATIVIDADE);
        if(listarAtoresEmAtividade.isEmpty()) {
            throw new SemCadastroException(TipoDominioException.ATOR.getSingular(), TipoDominioException.ATOR.getPlural());
        }

        final List<AtorEmAtividade> retorno = new ArrayList<>();

        if (filtroNome.isPresent()) {
            for (Ator ator : atoresCadastrados) {
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

        final List<Ator> atores = atorRepository.findAll();

        for (Ator ator : atores) {
            if (id.equals(ator.getId())) {
                return ator;
            }
        }

        throw new ConsultaIdException(TipoDominioException.ATOR.getSingular(), id);

    }

    public List<Ator> consultarAtores() throws SemCadastroException {
        List<Ator> list = atorRepository.findAll();
        if (list.isEmpty()) {
            throw new SemCadastroException(TipoDominioException.ATOR.getSingular(), TipoDominioException.ATOR.getPlural());
        }
        return list;
    }

    public void atualizarAtor(Integer id, AtorRequest atorRequest) throws Exception {
        boolean temAtor = atorRepository.existsById(id);
        if (temAtor) {
            validator.validaAnoInicioAtividade(atorRequest.getDataNascimento(), atorRequest.getAnoInicioAtividade(), TipoDominioException.ATOR);
            validator.validaNomeESobrenome(atorRequest.getNome(), TipoDominioException.ATOR);

            //Verificando se já consta Nome do Ator cadastrado no Banco de Dado
            Ator nomeAtorExist = atorRepository.findByNomeEqualsIgnoreCase(atorRequest.getNome());
            if (nomeAtorExist != null && !nomeAtorExist.getId().equals(id)) {
                throw new CadastroDuplicadoException(TipoDominioException.ATOR.getSingular(), atorRequest.getNome());
            }

            Ator ator = atorRepository.findAllById(id);
            ator.setNome(atorRequest.getNome());
            ator.setDataNascimento(atorRequest.getDataNascimento());
            ator.setStatusCarreira(atorRequest.getStatusCarreira());
            ator.setAnoInicioAtividade(atorRequest.getAnoInicioAtividade());

            atorRepository.save(ator);

        } else {
            throw new ConsultaIdException(TipoDominioException.ATOR.getSingular(), id);
        }
    }

    public void removerAtor(Integer id) throws Exception {
        boolean temAtor = atorRepository.existsById(id);
        if (!temAtor) {
            throw new ConsultaIdException(TipoDominioException.ATOR.getSingular(), id);
        }

        Ator ator = atorRepository.findAllById(id);
        try {
            atorRepository.delete(ator);
        } catch (Exception e) {
            throw new VinculadoFilmeException("Este ator está vinculado a um ou mais personagens, para remover o ator é necessário remover os seus personagens de atuação.");
        }
    }

    private void validaDuplicidadeCadastro(AtorRequest atorRequests) throws CadastroDuplicadoException {
        String nomeDoAtor = atorRequests.getNome();
        List<Ator> atores = atorRepository.findAll();

        for (Ator ator : atores) {
            if (ator.getNome().toLowerCase(Locale.ROOT).equals(nomeDoAtor.toLowerCase(Locale.ROOT))) {
                throw new CadastroDuplicadoException(TipoDominioException.ATOR.getSingular(), nomeDoAtor);
            }
        }

    }


}
