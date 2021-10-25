package br.com.cwi.reset.hugocabral.model;

public enum Genero {
    ACAO("Ação"),
    AVENTURA("Aventura"),
    COMEDIA("Comédia"),
    DOCUMENTARIO("Documentário"),
    DRAMA("Drama"),
    ESPIONAGEM("Espionagem"),
    FICCAO_CIENTIFICA("Ficção Científica"),
    GUERRA("Guerra"),
    MISTERIO("Mistério"),
    MUSICAL("Musical"),
    POLICIAL("Policial"),
    ROMANCE("Romance"),
    TERROR("Terror");
    private String statusGenero;

    Genero(String statusCarreira) {
        this.statusGenero = statusGenero;
    }

    public String getStatusGenero() {
        return statusGenero;
    }
}
