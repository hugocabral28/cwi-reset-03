package br.com.cwi.reset.hugocabral.service;

import br.com.cwi.reset.hugocabral.FakeDatabase;
import br.com.cwi.reset.hugocabral.model.*;
import br.com.cwi.reset.hugocabral.exception.*;
import br.com.cwi.reset.hugocabral.exception.comum.CadastroDuplicadoException;
import br.com.cwi.reset.hugocabral.exception.comum.SemCadastroException;
import br.com.cwi.reset.hugocabral.repository.FilmeRepository;
import br.com.cwi.reset.hugocabral.request.FilmeRequest;
import br.com.cwi.reset.hugocabral.validator.FilmeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Objects.isNull;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;
    @Autowired
    private DiretorService diretorService;
    @Autowired
    private EstudioService estudioService;
    @Autowired
    private PersonagemService personagemAtorService;

    private FilmeValidator validator;

    public FilmeService() {
        this.diretorService = new DiretorService();
        this.estudioService = new EstudioService();
        this.personagemAtorService = new PersonagemService();
        this.validator = new FilmeValidator();
    }

    public void criarFilme(FilmeRequest filmeRequest) throws Exception {

        validator.validaCamposObrigatorios(filmeRequest);
        validaDuplicidadeCadastro(filmeRequest);

        final Filme filme = new Filme(filmeRequest.getNome(),
                filmeRequest.getAnoLancamento(),
                filmeRequest.getCapaFilme(),
                filmeRequest.getGeneros(),
                diretorService.consultarDiretor(filmeRequest.getIdDiretor()),
                estudioService.consultarEstudio(filmeRequest.getIdEstudio()),
                filmeRequest.getResumo(),
                personagemAtorService.cadastrarPersonagensFilme(filmeRequest.getPersonagens())
                );

        if (filme.getGenero().isEmpty()) {
            throw new DeveInformaException("Genero",TipoDominioException.FILME.getSingular());
        }

        final Set<Genero> generoSet = new HashSet<>();
        for (Genero genero : filme.getGenero()) {
            if (generoSet.contains(genero)) {
                throw new NaoPermitidoInformarException("Genero",TipoDominioException.FILME.getSingular());
            } else {
                generoSet.add(genero);
            }
        }

        filmeRepository.save(filme);
    }

    public List<Filme> consultarFilmes(
            Optional<String> nomeFilme,
            Optional<String> nomeDiretor,
            Optional<String> nomePersonagem,
            Optional<String> nomeAtor) throws Exception {

        final List<Filme> filmesCadastrados = filmeRepository.findAll();

        if (filmesCadastrados.isEmpty()) {
            throw new SemCadastroException(TipoDominioException.FILME.getSingular(), TipoDominioException.FILME.getPlural());
        }

        final List<Filme> filtroFinal;

        if(nomeFilme.isPresent() | nomeDiretor.isPresent() | nomePersonagem.isPresent() | nomeAtor.isPresent()){

            final List<Filme> filtrarNomePersonagem = filtrarNomePersonagem(filmesCadastrados, nomePersonagem.get());
            final List<Filme> filtrarNomeAtor = filtrarNomeAtor(filtrarNomePersonagem, nomeAtor.get());
            final List<Filme> filtrarNomeDiretor = filtrarNomeDiretor(filtrarNomeAtor, nomeDiretor.get());
            filtroFinal = filtrarNomeFilme(filtrarNomeDiretor, nomeFilme.get());

        }else{
            filtroFinal = filmesCadastrados;
        }



        if (filtroFinal.isEmpty()) {
            throw new FiltroFilmesException(
                TipoDominioException.FILME.getSingular(),
                nomeFilme.get(),
                nomeDiretor.get(),
                nomePersonagem.get(),
                nomeAtor.get()
            );
        }

        return filtroFinal;
    }

    private List<Filme> filtrarNomeFilme(final List<Filme> listaOriginal, final String nome) {
        if (isNull(nome)) {
            return listaOriginal;
        }

        final List<Filme> filmeFiltrado = new ArrayList<>();

        for (Filme filme : listaOriginal) {
            if (filme.getNome().toLowerCase(Locale.ROOT).equalsIgnoreCase(nome.toLowerCase(Locale.ROOT))) {
                filmeFiltrado.add(filme);
            }
        }

        return filmeFiltrado;
    }

    private List<Filme> filtrarNomeDiretor(final List<Filme> listaOriginal, final String nome) {
        if (isNull(nome)) {
            return listaOriginal;
        }

        final List<Filme> filmeFiltrado = new ArrayList<>();

        for (Filme filme : listaOriginal) {
            if (filme.getDiretor().getNome().toLowerCase(Locale.ROOT).equalsIgnoreCase(nome.toLowerCase(Locale.ROOT))) {
                filmeFiltrado.add(filme);
            }
        }

        return filmeFiltrado;
    }

    private List<Filme> filtrarNomeAtor(final List<Filme> listaOriginal, final String nome) {
        if (isNull(nome)) {
            return listaOriginal;
        }

        final List<Filme> filmeFiltrado = new ArrayList<>();

        for (Filme filme : listaOriginal) {
            for (PersonagemAtor personagens : filme.getPersonagens()) {
                if (personagens.getAtor().getNome().toLowerCase(Locale.ROOT).equalsIgnoreCase(nome.toLowerCase(Locale.ROOT))) {
                    filmeFiltrado.add(filme);
                    break;
                }
            }
        }

        return filmeFiltrado;
    }

    private List<Filme> filtrarNomePersonagem(final List<Filme> listaOriginal, final String nome) {
        if (isNull(nome)) {
            return listaOriginal;
        }

        final List<Filme> filmeFiltrado = new ArrayList<>();

        for (Filme filme : listaOriginal) {
            for (PersonagemAtor personagens : filme.getPersonagens()) {
                if (personagens.getNomePersonagem()
                        .toLowerCase(Locale.ROOT)
                        .equalsIgnoreCase(nome.toLowerCase(Locale.ROOT))
                ) {
                    filmeFiltrado.add(filme);
                    break;
                }
            }
        }
        return filmeFiltrado;
    }

    private void validaDuplicidadeCadastro(FilmeRequest filmeRequest) throws CadastroDuplicadoException {
        String nomeDoFilme = filmeRequest.getNome();
        List<Filme> filmes = filmeRepository.findAll();
        for (Filme filme : filmes) {
            if (filme.getNome().toLowerCase(Locale.ROOT).equals(nomeDoFilme.toLowerCase(Locale.ROOT))) {
                throw new CadastroDuplicadoException(TipoDominioException.FILME.getSingular(), nomeDoFilme);
            }
        }
    }


}
