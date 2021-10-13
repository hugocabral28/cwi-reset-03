package br.com.cwi.reset.hugocabral.domain;


import java.time.LocalDate;

public class Diretor extends Pessoa {

    public Diretor(String nome, LocalDate dataNascimento, int anoInicioAtividade) {
        super(nome, dataNascimento, anoInicioAtividade);
    }
}
