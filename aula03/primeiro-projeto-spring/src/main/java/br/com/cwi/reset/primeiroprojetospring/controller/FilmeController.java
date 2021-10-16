package br.com.cwi.reset.primeiroprojetospring.controller;


import br.com.cwi.reset.primeiroprojetospring.domain.AvaliacaoForaDoPadraoException;
import br.com.cwi.reset.primeiroprojetospring.domain.Diretor;
import br.com.cwi.reset.primeiroprojetospring.domain.Filme;
import br.com.cwi.reset.primeiroprojetospring.domain.Genero;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/filme")
public class FilmeController {
    private static List<Filme> filmes = new ArrayList<>();

    /*@GetMapping
    public Filme getFilme() throws AvaliacaoForaDoPadraoException {
        Diretor diretor =new Diretor("Ruben Fleischer", LocalDate.of(1965, 05, 18), 2, Genero.FEMININO);
        Filme filme = new Filme(
                "Venom",
                "O jornalista Eddie Brock desenvolve força e poder sobre-humanos. quando",
                190,
                2018,
                4.5,
                diretor
        );

        return filme;
    }*/

    @PostMapping
    public Filme cadastrarFilme(@RequestBody Filme filme) throws AvaliacaoForaDoPadraoException{
        filmes.add(filme);

        return filme;

    }
    @PutMapping
    public Filme atualizarFilme(@RequestBody Filme filme){
        Filme filmeCadastrado = buscarFilmes(filme.getNome());
        if(filmeCadastrado != null){
            filmes.remove(filmeCadastrado);
            filmes.add(filme);
            return filme;
        }
        return null;
    }

    @DeleteMapping("/{nome}")
    public void deleteFilme(@PathVariable String nome){
        Filme filmeCadastrado = buscarFilmes(nome);
        if(filmeCadastrado != null){
            filmes.remove(filmeCadastrado);
        }
    }

    @GetMapping
    public List<Filme> todosfilmes(){
        return filmes;
    }
    @GetMapping("/{nome}")
    public Filme todosfilmes(@PathVariable String nome){
        Filme filmeListado = buscarFilmes(nome);
        if(filmeListado != null){
            return filmeListado;
        }
        return null;
    }

    public Filme buscarFilmes(String nome){
        for (Filme filme : filmes){
            if (filme.getNome().equals(nome)) {
                return filme;
            }
        }
        return null;
    }

    

}
