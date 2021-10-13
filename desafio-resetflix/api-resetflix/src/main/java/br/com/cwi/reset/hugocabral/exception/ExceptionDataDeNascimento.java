package br.com.cwi.reset.hugocabral.exception;

public class ExceptionDataDeNascimento extends Exception{
    public ExceptionDataDeNascimento(String nome) {
        System.out.println("Não é possível cadastrar " + nome + " não nascidos.");
    }
}
