package br.com.cwi.reset.hugocabral.exception;

public class ExceptionCadastroDuplicado extends Exception {
    public ExceptionCadastroDuplicado(String nome, String requestNome) {
        System.out.println("Já existe um(a) "+ nome +" cadastrado para o nome " + requestNome + ".");
    }
}
