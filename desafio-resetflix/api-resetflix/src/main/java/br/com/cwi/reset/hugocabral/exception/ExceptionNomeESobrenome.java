package br.com.cwi.reset.hugocabral.exception;

public class ExceptionNomeESobrenome extends Exception {
    public ExceptionNomeESobrenome(String nome) {
        System.out.println("Deve ser informado no mínimo nome e sobrenome para o(a) " + nome + ".");
    }
}
