package br.com.cwi.reset.hugocabral.domain;


import java.time.LocalDate;

public class Diretor extends Pessoa {
    private Integer id;

    public Diretor(String nome, LocalDate dataNascimento, Integer anoInicioAtividade) {
        super(nome, dataNascimento, anoInicioAtividade);
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
