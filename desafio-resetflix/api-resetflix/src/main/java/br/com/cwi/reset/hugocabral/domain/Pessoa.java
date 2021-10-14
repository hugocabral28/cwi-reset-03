package br.com.cwi.reset.hugocabral.domain;

import java.time.LocalDate;

public abstract class Pessoa {
    public final static String CAMPO_ID = "Id";
    public final static String CAMPO_NOME = "Nome";
    public final static String CAMPO_DATA_NASCIMENTO = "Data de Nascimento";
    public final static String CAMPO_ANO_INICIO_ATIVIDADE = "Ano de Inicio de Atividade";

    private String nome;
    private LocalDate dataNascimento;
    private Integer anoInicioAtividade;

    public Pessoa(String nome, LocalDate dataNascimento, Integer anoInicioAtividade){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.anoInicioAtividade = anoInicioAtividade;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Integer getAnoInicioAtividade() {
        return anoInicioAtividade;
    }
}
