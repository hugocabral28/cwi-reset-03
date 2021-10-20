package br.com.cwi.reset.hugocabral.service;


import br.com.cwi.reset.hugocabral.FakeDatabase;
import br.com.cwi.reset.hugocabral.domain.Ator;
import br.com.cwi.reset.hugocabral.domain.AtorEmAtividade;
import br.com.cwi.reset.hugocabral.domain.Constantes;
import br.com.cwi.reset.hugocabral.domain.StatusCarreira;
import br.com.cwi.reset.hugocabral.exception.*;
import br.com.cwi.reset.hugocabral.request.AtorRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class AtorService {

    private FakeDatabase fakeDatabase;
    private Integer sequenceIdAtor = 0;

    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarAtor(AtorRequest atorRequest) throws CampoObrigatorioException,
            NomeESobrenomeException, DataDeNascimentoInvalidaException,
            AnoInicioAtividadeInvalidoException, CadastroDuplicadoException {

        validaCamposObrigatorios(atorRequest);
        validaNomeESobrenome(atorRequest);
        validaDataNascimento(atorRequest);
        validaAnoInicioAtividade(atorRequest);
        validaDuplicidadeCadastro(atorRequest);

        sequenceIdAtor = gerarIdAtor();

        final Ator ator = new Ator(sequenceIdAtor, atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
        fakeDatabase.persisteAtor(ator);

    }

    // Demais métodos da classe
    public List<AtorEmAtividade> listarAtoresEmAtividade(Optional<String> filtroNome) throws Exception {
        final List<Ator> atoresCadastrados = fakeDatabase.recuperaAtores();

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
            throw new CampoObrigatorioException("id");
        }

        final List<Ator> atores = fakeDatabase.recuperaAtores();

        for (Ator ator : atores) {
            if (id.equals(ator.getId())) {
                return ator;
            }
        }

        throw new ConsultaIdException(TipoDominioException.ATOR.getSingular(), id);

    }

    public List<Ator> consultarAtores() throws SemCadastroException {
        List<Ator> list = fakeDatabase.recuperaAtores();
        if (list.isEmpty()) {
            throw new SemCadastroException(TipoDominioException.ATOR.getSingular(), TipoDominioException.ATOR.getPlural());
        }
        return list;
    }

    private Integer gerarIdAtor() {
        return ++sequenceIdAtor;
    }

    private void validaCamposObrigatorios(AtorRequest atorRequest) throws CampoObrigatorioException {

        String nome = atorRequest.getNome();
        if (nome == null || nome.equals("")) {
            throw new CampoObrigatorioException(Constantes.CAMPO_NOME);
        }

        LocalDate dataNascimento = atorRequest.getDataNascimento();
        if (dataNascimento == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_DATA_NASCIMENTO);
        }

        Integer anoInicioAtividade = atorRequest.getAnoInicioAtividade();
        if (anoInicioAtividade == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_ANO_INICIO_ATIVIDADE);
        }

        StatusCarreira statusCarreira = atorRequest.getStatusCarreira();
        if (statusCarreira == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_STATUS_CARREIRA);
        }

    }

    private void validaNomeESobrenome(AtorRequest atorRequest) throws NomeESobrenomeException {
        String[] nomeSobrenome = atorRequest.getNome().split(" ");
        if (nomeSobrenome.length < 2) {
            throw new NomeESobrenomeException(TipoDominioException.ATOR.getSingular());
        }
    }

    private void validaDataNascimento(AtorRequest atorRequest) throws DataDeNascimentoInvalidaException {
        Integer anoAtual = LocalDate.now().getYear();
        Integer anoNascimento = atorRequest.getDataNascimento().getYear();

        if (anoNascimento > anoAtual) {
            throw new DataDeNascimentoInvalidaException(TipoDominioException.ATOR.getSingular());
        }
    }

    private void validaAnoInicioAtividade(AtorRequest atorRequest) throws AnoInicioAtividadeInvalidoException {
        Integer anoNascimento = atorRequest.getDataNascimento().getYear();
        Integer anoInicioAtividade = atorRequest.getAnoInicioAtividade();

        if (anoInicioAtividade < anoNascimento) {
            throw new AnoInicioAtividadeInvalidoException(TipoDominioException.ATOR.getSingular());
        }
    }

    private void validaDuplicidadeCadastro(AtorRequest atorRequest) throws CadastroDuplicadoException {
        String nomeDoAtor = atorRequest.getNome();
        List<Ator> atores = fakeDatabase.recuperaAtores();
        for (Ator ator : atores) {
            if (ator.getNome().toLowerCase(Locale.ROOT).equals(nomeDoAtor.toLowerCase(Locale.ROOT))) {
                throw new CadastroDuplicadoException(TipoDominioException.ATOR.getSingular(), nomeDoAtor);
            }
        }
    }
}
