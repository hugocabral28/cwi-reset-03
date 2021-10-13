package br.com.cwi.reset.hugocabral.domain;

import java.time.LocalDate;


public class Ator extends Pessoa {
    public final static String CAMPO_STATUS_CARREIRA = "Status da Carreira";

    private int id;
    private StatusCarreira statusCarreira;

    public Ator(String nome, LocalDate dataNascimento, StatusCarreira statusCarreira, Integer anoInicioAtividade) {
        super(nome, dataNascimento, anoInicioAtividade);
        this.statusCarreira = statusCarreira;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StatusCarreira getStatusCarreira() {
        return statusCarreira;
    }
}
