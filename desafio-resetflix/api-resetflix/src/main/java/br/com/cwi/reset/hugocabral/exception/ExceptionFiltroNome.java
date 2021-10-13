package br.com.cwi.reset.hugocabral.exception;

public class ExceptionFiltroNome extends Exception{
    public ExceptionFiltroNome(String nome, String requestNome) {
        System.out.println(nome + " não encontrato com o filtro "+ requestNome + ", favor informar outro filtro.");
    }
}
