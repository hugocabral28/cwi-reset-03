package br.com.cwi.reset.primeiroprojetospring.controller;


import br.com.cwi.reset.primeiroprojetospring.domain.Diretor;
import br.com.cwi.reset.primeiroprojetospring.domain.Filme;
import br.com.cwi.reset.primeiroprojetospring.domain.Genero;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/filme")
public class FilmeController {
    @GetMapping
    public Filme getFilme() {
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
    }

}
