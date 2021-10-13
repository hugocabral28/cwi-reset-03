package br.com.cwi.reset.hugocabral.domain;

public enum StatusCarreira {
    EM_ATIVIDADE("Em Atividade"),
    ENCERRADO("Encerrado");

    private String statusCarreira;

    StatusCarreira(String statusCarreira) {
        this.statusCarreira = statusCarreira;
    }

    public String getStatusCarreira() {
        return statusCarreira;
    }
}
