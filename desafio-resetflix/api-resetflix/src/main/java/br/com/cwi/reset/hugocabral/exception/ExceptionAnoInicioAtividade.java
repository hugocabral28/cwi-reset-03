package br.com.cwi.reset.hugocabral.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionAnoInicioAtividade extends Exception{
    public ExceptionAnoInicioAtividade(String nome) {
        super("Ano de início de atividade inválido para o " + nome + " cadastrado.");
    }
}
