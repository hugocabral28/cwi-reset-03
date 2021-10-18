package br.com.cwi.reset.hugocabral.service;


import br.com.cwi.reset.hugocabral.FakeDatabase;
import br.com.cwi.reset.hugocabral.domain.Ator;
import br.com.cwi.reset.hugocabral.domain.AtorEmAtividade;
import br.com.cwi.reset.hugocabral.domain.Constantes;
import br.com.cwi.reset.hugocabral.domain.StatusCarreira;
import br.com.cwi.reset.hugocabral.exception.*;
import br.com.cwi.reset.hugocabral.request.AtorRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AtorService {

    private FakeDatabase fakeDatabase;
    private Integer sequenceIdAtor = 0;

    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarAtor(AtorRequest atorRequest) throws ExceptionCampoInvalido,
            ExceptionNomeESobrenome,ExceptionDataDeNascimento,
            ExceptionAnoInicioAtividade,ExceptionCadastroDuplicado{

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
    public List<AtorEmAtividade> listarAtoresEmAtividade(Optional<String> filtroNome) throws ExceptionFiltroNome {
        List<Ator> atores = fakeDatabase.recuperaAtores();

        StatusCarreira statusEmAtividade = StatusCarreira.EM_ATIVIDADE;
        boolean filtroComNome = filtroNome.isPresent();
        String[] nome = {};

        if (filtroComNome) {
            //Caso tenha espaços em brancos remove do inicio e final
            nome = filtroNome.toString().trim().split(" ");
        }
        //Caso tenha filtroNome filtrar ator pelo Nome
        List<AtorEmAtividade> atoresFiltrados = atores.stream()
                .filter(ator -> filtroComNome ? ator.getNome().contains(filtroNome.get()) : true)
                .filter(ator -> ator.getStatusCarreira().equals(statusEmAtividade))
                .map(ator -> new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()))
                .collect(Collectors.toList());


        if (atoresFiltrados.isEmpty()) {
            String filtro = filtroNome.map(Object::toString).orElse(null);
            throw new ExceptionFiltroNome("Ator", filtro);
        }

        return atoresFiltrados;

    }

    public Ator consultarAtor(Integer id) throws Exception, ExceptionConsultaId {

        if (id == null) {
            throw new ExceptionIdObrigatorio("id");
        }
        final List<Ator> atores = fakeDatabase.recuperaAtores();

            for (Ator ator : atores) {
                if (id.equals(ator.getId())) {
                    return ator;
                }
            }

            throw new ExceptionConsultaId(TipoDominioException.ATOR.getSingular(), id);

    }

    public List<Ator> consultarAtores() throws ExceptionSemCadastro {
        List<Ator> list = fakeDatabase.recuperaAtores();
        if (list.isEmpty()) {
            throw new ExceptionSemCadastro(TipoDominioException.ATOR.getSingular(),TipoDominioException.ATOR.getPlural());
        }
        return list;
    }

    private Integer gerarIdAtor() {
        return ++sequenceIdAtor;
    }

    private void validaCamposObrigatorios(AtorRequest atorRequest) throws ExceptionCampoInvalido {

        String nome = atorRequest.getNome();
        if (nome == null || nome.equals("")) {
            throw new ExceptionCampoInvalido(Constantes.CAMPO_NOME);
        }

        LocalDate dataNascimento = atorRequest.getDataNascimento();
        if (dataNascimento == null) {
            throw new ExceptionCampoInvalido(Constantes.CAMPO_DATA_NASCIMENTO);
        }

        Integer anoInicioAtividade = atorRequest.getAnoInicioAtividade();
        if (anoInicioAtividade == null) {
            throw new ExceptionCampoInvalido(Constantes.CAMPO_ANO_INICIO_ATIVIDADE);
        }

        StatusCarreira statusCarreira = atorRequest.getStatusCarreira();
        if (statusCarreira == null) {
            throw new ExceptionCampoInvalido(Constantes.CAMPO_STATUS_CARREIRA);
        }

    }

    private void validaNomeESobrenome(AtorRequest atorRequest) throws ExceptionNomeESobrenome {
        String[] nomeSobrenome = atorRequest.getNome().split(" ");
        if (nomeSobrenome.length < 2) {
            throw new ExceptionNomeESobrenome(TipoDominioException.ATOR.getSingular());
        }
    }

    private void validaDataNascimento(AtorRequest atorRequest) throws ExceptionDataDeNascimento {
        Integer anoAtual = LocalDate.now().getYear();
        Integer anoNascimento = atorRequest.getDataNascimento().getYear();

        if (anoNascimento > anoAtual) {
            throw new ExceptionDataDeNascimento(TipoDominioException.ATOR.getSingular());
        }
    }

    private void validaAnoInicioAtividade(AtorRequest atorRequest) throws ExceptionAnoInicioAtividade {
        Integer anoNascimento = atorRequest.getDataNascimento().getYear();
        Integer anoInicioAtividade = atorRequest.getAnoInicioAtividade();

        if (anoInicioAtividade < anoNascimento) {
            throw new ExceptionAnoInicioAtividade(TipoDominioException.ATOR.getSingular());
        }
    }

    private void validaDuplicidadeCadastro(AtorRequest atorRequest) throws ExceptionCadastroDuplicado {
        String nomeDoAtor = atorRequest.getNome();
        List<Ator> atores = fakeDatabase.recuperaAtores();
        for (Ator ator : atores) {
            if (ator.getNome().equals(nomeDoAtor)) {
                throw new ExceptionCadastroDuplicado(TipoDominioException.ATOR.getSingular(), nomeDoAtor);
            }
        }
    }
}
