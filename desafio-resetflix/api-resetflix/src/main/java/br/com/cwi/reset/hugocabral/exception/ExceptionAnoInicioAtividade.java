package br.com.cwi.reset.hugocabral.exception;

public class ExceptionAnoInicioAtividade extends Exception{
    public ExceptionAnoInicioAtividade(String nome) {
        System.out.println("Ano de início de atividade inválido para o(a) " + nome + " cadastrado.");
    }
}
