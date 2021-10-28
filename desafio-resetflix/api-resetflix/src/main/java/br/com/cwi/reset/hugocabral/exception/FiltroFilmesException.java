package br.com.cwi.reset.hugocabral.exception;

public class FiltroFilmesException extends Exception {
    public FiltroFilmesException(String tipo, String nomeFilme, String nomeDiretor, String nomePersonagem, String nomeAtor) {
        super(String.format("%s não encontrado com os filtros nomeFilme= %s," +
                " nomeDiretor= %s," +
                " nomePersonagem= %s," +
                " nomeAtor= %s," +
                " favor informar outros filtros.", tipo, nomeFilme, nomeDiretor, nomePersonagem, nomeAtor));
    }
}
