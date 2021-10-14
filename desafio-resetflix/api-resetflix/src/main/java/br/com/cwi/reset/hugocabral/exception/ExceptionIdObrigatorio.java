package br.com.cwi.reset.hugocabral.exception;

public class ExceptionIdObrigatorio extends Exception {
    public ExceptionIdObrigatorio(String campo) {
            System.out.println("Campo obrigatório não informado. Favor informar o campo " + campo + ".");
    }
}
