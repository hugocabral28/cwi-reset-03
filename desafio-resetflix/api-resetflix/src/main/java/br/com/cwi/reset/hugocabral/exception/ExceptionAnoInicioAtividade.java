package br.com.cwi.reset.hugocabral.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionAnoInicioAtividade extends Exception{
    public ExceptionAnoInicioAtividade(String tipo) {
        super("Ano de início de atividade inválido para o " + tipo + " cadastrado.");
    }
}
