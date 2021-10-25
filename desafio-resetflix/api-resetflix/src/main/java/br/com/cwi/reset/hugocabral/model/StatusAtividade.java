package br.com.cwi.reset.hugocabral.model;

public enum StatusAtividade {
    EM_ATIVIDADE("Em Atividade"),
    ENCERRADO("Encerrado");

    private String statusAtividade;

    StatusAtividade(String statusCarreira) {
        this.statusAtividade = statusAtividade;
    }

    public String getStatusAtividade() {
        return statusAtividade;
    }
}
