package br.com.cwi.reset.hugocabral.exception;

public class ExceptionSemCadastro extends Exception{
    public ExceptionSemCadastro(String nome) {
        System.out.println("Nenhum " + nome + " cadastrado, favor cadastar "+ nome + ".");
    }
}
