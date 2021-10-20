package br.com.cwi.reset.hugocabral.service;

import br.com.cwi.reset.hugocabral.FakeDatabase;
import br.com.cwi.reset.hugocabral.domain.Constantes;
import br.com.cwi.reset.hugocabral.domain.Diretor;
import br.com.cwi.reset.hugocabral.exception.*;
import br.com.cwi.reset.hugocabral.request.DiretorRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


public class DiretorService {

    private FakeDatabase fakeDatabase;
    private Integer sequenceIdDiretor = 0;

    public DiretorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void cadastrarDiretor(DiretorRequest diretorRequest) throws CampoObrigatorioException,
            NomeESobrenomeException, DataDeNascimentoInvalidaException,
            AnoInicioAtividadeInvalidoException, CadastroDuplicadoException {

        validaCamposObrigatorios(diretorRequest);
        validaNomeESobrenome(diretorRequest);
        validaDataNascimento(diretorRequest);
        validaAnoInicioAtividade(diretorRequest);
        validaDuplicidadeCadastro(diretorRequest);
        sequenceIdDiretor = gerarIdDiretor();

        final Diretor diretor = new Diretor(sequenceIdDiretor, diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade());
        fakeDatabase.persisteDiretor(diretor);
    }

    // Demais métodos da classe
    public List<Diretor> listarDiretores(Optional<String> filtroNome) throws Exception {
        final List<Diretor> diretoresCadastrados = fakeDatabase.recuperaDiretores();

        if (diretoresCadastrados.isEmpty()) {
            throw new SemCadastroException(TipoDominioException.DIRETOR.getSingular(), TipoDominioException.DIRETOR.getPlural());
        }

        final List<Diretor> retorno = new ArrayList<>();

        if (filtroNome.isPresent()) {
            for (Diretor diretor : diretoresCadastrados) {
                //Verificando se o filtroNome informado contem no nome
                final boolean containsFilter = diretoresCadastrados.stream()
                        .allMatch(atorlistadoFilter -> diretor.getNome()
                                .toLowerCase(Locale.ROOT)
                                .contains(filtroNome.get().toLowerCase(Locale.ROOT)));

                if (containsFilter) {
                    retorno.add(diretor);
                }
            }
        } else {
            retorno.addAll(diretoresCadastrados);
        }

        if (retorno.isEmpty() && filtroNome != null) {
            throw new FiltroNomeException(TipoDominioException.DIRETOR.getSingular(), filtroNome.get());
        }

        return retorno;
    }


    public Diretor consultarDiretor(Integer id) throws Exception {

        if (id == null) {
            throw new CampoObrigatorioException("id");
        }

        final List<Diretor> diretores = fakeDatabase.recuperaDiretores();

        for (Diretor diretore : diretores) {
            if (id.equals(diretore.getId())) {
                return diretore;
            }
        }

        throw new ConsultaIdException(TipoDominioException.DIRETOR.getSingular(), id);

    }

    public List<Diretor> consultarDiretor() throws SemCadastroException {
        List<Diretor> list = fakeDatabase.recuperaDiretores();
        if (list.isEmpty()) {
            throw new SemCadastroException(TipoDominioException.DIRETOR.getSingular(), TipoDominioException.DIRETOR.getPlural());
        }
        return list;
    }

    private Integer gerarIdDiretor() {
        return ++sequenceIdDiretor;
    }

    private void validaCamposObrigatorios(DiretorRequest diretorRequest) throws CampoObrigatorioException {

        String nome = diretorRequest.getNome();
        if (nome == null || nome.equals("")) {
            throw new CampoObrigatorioException(Constantes.CAMPO_NOME);
        }

        LocalDate dataNascimento = diretorRequest.getDataNascimento();
        if (dataNascimento == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_DATA_NASCIMENTO);
        }

        Integer anoInicioAtividade = diretorRequest.getAnoInicioAtividade();
        if (anoInicioAtividade == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_ANO_INICIO_ATIVIDADE);
        }

    }

    private void validaNomeESobrenome(DiretorRequest diretorRequest) throws NomeESobrenomeException {
        String[] nomeSobrenome = diretorRequest.getNome().trim().split(" ");
        if (nomeSobrenome.length < 2) {
            throw new NomeESobrenomeException(TipoDominioException.DIRETOR.getSingular());
        }
    }

    private void validaDataNascimento(DiretorRequest diretorRequest) throws DataDeNascimentoInvalidaException {
        Integer anoAtual = LocalDate.now().getYear();
        Integer anoNascimento = diretorRequest.getDataNascimento().getYear();

        if (anoNascimento > anoAtual) {
            throw new DataDeNascimentoInvalidaException(TipoDominioException.DIRETOR.getSingular());
        }
    }

    private void validaAnoInicioAtividade(DiretorRequest diretorRequest) throws AnoInicioAtividadeInvalidoException {
        Integer anoNascimento = diretorRequest.getDataNascimento().getYear();
        Integer anoInicioAtividade = diretorRequest.getAnoInicioAtividade();

        if (anoInicioAtividade < anoNascimento) {
            throw new AnoInicioAtividadeInvalidoException(TipoDominioException.DIRETOR.getSingular());
        }
    }

    private void validaDuplicidadeCadastro(DiretorRequest diretorRequest) throws CadastroDuplicadoException {
        String nomeDoDiretor = diretorRequest.getNome();
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        for (Diretor diretor : diretores) {
            if (diretor.getNome().toLowerCase(Locale.ROOT).equals(nomeDoDiretor.toLowerCase(Locale.ROOT))) {
                throw new CadastroDuplicadoException(TipoDominioException.DIRETOR.getSingular(), nomeDoDiretor);
            }
        }
    }
}
