package br.com.cwi.reset.hugocabral.service;

import br.com.cwi.reset.hugocabral.exception.ConsultaIdException;
import br.com.cwi.reset.hugocabral.model.*;
import br.com.cwi.reset.hugocabral.exception.*;
import br.com.cwi.reset.hugocabral.exception.CadastroDuplicadoException;
import br.com.cwi.reset.hugocabral.exception.SemCadastroException;
import br.com.cwi.reset.hugocabral.repository.FilmeRepository;
import br.com.cwi.reset.hugocabral.request.FilmeRequest;
import br.com.cwi.reset.hugocabral.validator.FilmeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
            throw new DeveInformaException("Genero", TipoDominioException.FILME.getSingular());
        }

        final Set<Genero> generoSet = new HashSet<>();
        for (Genero genero : filme.getGenero()) {
            if (generoSet.contains(genero)) {
                throw new NaoPermitidoInformarException("Genero", TipoDominioException.FILME.getSingular());
            } else {
                generoSet.add(genero);
            }
        }

        filmeRepository.save(filme);
    }

    public void removerFilme(Integer id) throws Exception {

        boolean temFilme = filmeRepository.existsById(id);
        if (!temFilme) {
            throw new ConsultaIdException(TipoDominioException.FILME.getSingular(), id);
        }

        Filme filme = filmeRepository.findAllById(id);

        List<PersonagemAtor> listPersonagem = filme.getPersonagens();

        //Removendo todos os personagens do filme
        personagemAtorService.removerPersonagem(listPersonagem);

        filmeRepository.delete(filme);
    }

    public List<Filme> consultarFilmes(
            String nomeFilme,
            String nomeDiretor,
            String nomePersonagem,
            String nomeAtor) throws Exception {

        if (!filmeRepository.existsBy()) {
            throw new SemCadastroException(TipoDominioException.FILME.getSingular(), TipoDominioException.FILME.getPlural());
        }
        //caso não tenha nenhum filtro retorna todos filmes
        if (isNull(nomeFilme) && isNull(nomeDiretor) && isNull(nomePersonagem) && isNull(nomeAtor)) {
            return filmeRepository.findAll();
        }

        final List<Diretor> diretores = diretorService.listarDiretores(nomeDiretor);
        final List<PersonagemAtor> personagens = personagemAtorService.consultarPersonagemAtorPorNomeEAtor(nomePersonagem, nomeAtor);

        List<Filme> filmes;
        if (isNull(nomeFilme)) {
            filmes = filmeRepository.findAllByDiretorInAndPersonagensIn(diretores, personagens);
        } else {
            filmes = filmeRepository.findAllByNomeContainingIgnoreCaseAndDiretorInAndPersonagensIn(nomeFilme, diretores, personagens);
        }

        //Se não tiver nenhum filme com os filtros
        if (isNull(filmes) || filmes.isEmpty()) {
            throw new FiltroFilmesException(
                    TipoDominioException.FILME.getSingular(),
                    nomeFilme,
                    nomeDiretor,
                    nomePersonagem,
                    nomeAtor
            );
        }

        Set<Filme> filmeSet = new HashSet<>();

        for (Filme filme : filmes) {
            if (!filmeSet.contains(filme)) {
                filmeSet.add(filme);
            }
        }

        return filmeSet.stream().collect(Collectors.toList());
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
