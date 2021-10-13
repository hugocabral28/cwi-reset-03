package br.com.cwi.reset.hugocabral.domain;

public enum TipoAtuacao {
    PRINCIPAL("Principal"),
    COADJUVANTE("Coadjuvante");

    private String tipoAtuacao;

    TipoAtuacao(String tipoAtuacao) {
        this.tipoAtuacao = tipoAtuacao;
    }

    public String getTipoAtuacao() {
        return tipoAtuacao;
    }
}
