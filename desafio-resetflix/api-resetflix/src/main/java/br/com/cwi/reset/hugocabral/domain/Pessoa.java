package br.com.cwi.reset.hugocabral.domain;

import java.time.LocalDate;

public abstract class Pessoa {
    private String nome;
    private LocalDate dataNascimento;
    private Integer anoInicioAtividade;

    public Pessoa(String nome, LocalDate dataNascimento, int anoInicioAtividade){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.anoInicioAtividade = anoInicioAtividade;
    }

    public String getNome() {
        return nome;
    }
}
