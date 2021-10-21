package br.com.cwi.reset.hugocabral.service;

import br.com.cwi.reset.hugocabral.FakeDatabase;
import br.com.cwi.reset.hugocabral.domain.*;
import br.com.cwi.reset.hugocabral.exception.*;
import br.com.cwi.reset.hugocabral.request.FilmeRequest;
import br.com.cwi.reset.hugocabral.request.PersonagemAtorRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FilmeService {
    private FakeDatabase fakeDatabase;
    private Integer sequenceIdFilme = 0;
    private AtorService atorService;
    private DiretorService diretorService;
    private EstudioService estudioService;
    private PersonagemAtorService personagemAtorService;

    public FilmeService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarFilme(FilmeRequest filmeRequest) throws Exception {
        validaCamposObrigatorios(filmeRequest);
        validaDuplicidadeCadastro(filmeRequest);

        Diretor temDiretor = diretorService.consultarDiretor(filmeRequest.getIdDiretor());
        Estudio temEstudio = estudioService.consultarEstudio(filmeRequest.getIdDiretor());


        List<Ator> atores = fakeDatabase.recuperaAtores();

        List<PersonagemAtor> personagens = new ArrayList<>();
        //List<PersonagemAtor> personagemCriados = new personagemAtorService.criarPersonagem(personagens);


        personagens = personagemAtorService.criarPersonagem(filmeRequest.getPersonagens());


        sequenceIdFilme = gerarIdFilme();


        final Filme filme = new Filme(sequenceIdFilme,
                filmeRequest.getNome(),
                filmeRequest.getAnoLancamento(),
                filmeRequest.getCapaFilme(),
                filmeRequest.getGenero(),
                temDiretor,
                temEstudio,
                personagens,
                filmeRequest.getResumo());

        fakeDatabase.persisteFilme(filme);
    }

    private Integer gerarIdFilme() {
        return ++sequenceIdFilme;
    }

    private void validaCamposObrigatorios(FilmeRequest filmeRequest) throws CampoObrigatorioException {

        String nome = filmeRequest.getNome();
        if (nome == null || nome.equals("")) {
            throw new CampoObrigatorioException(Constantes.CAMPO_NOME);
        }

        Integer anoLancamento = filmeRequest.getAnoLancamento();
        if (anoLancamento == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_ANO_LANCAMENTO);
        }

        String capaFilme = filmeRequest.getCapaFilme();
        if (capaFilme == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_CAPA_FILME);
        }

        List<Genero> listGenero = filmeRequest.getGenero();
        if (listGenero == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_GENERO);
        }

        Integer idDiretor = filmeRequest.getIdDiretor();
        if (idDiretor == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_ID_DIRETOR);
        }
        Integer idEstudio = filmeRequest.getIdEstudio();
        if (idEstudio == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_ID_ESTUDIO);
        }

        String resumo = filmeRequest.getResumo();
        if (resumo == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_RESUMO);
        }


    }

    private void validaDuplicidadeCadastro(FilmeRequest filmeRequest) throws CadastroDuplicadoException {
        String nomeDoFilme = filmeRequest.getNome();
        List<Filme> filmes = fakeDatabase.recuperaFilmes();
        for (Filme filme : filmes) {
            if (filme.getNome().toLowerCase(Locale.ROOT).equals(nomeDoFilme.toLowerCase(Locale.ROOT))) {
                throw new CadastroDuplicadoException(TipoDominioException.FILME.getSingular(), nomeDoFilme);
            }
        }
    }


}
