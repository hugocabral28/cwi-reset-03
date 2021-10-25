package br.com.cwi.reset.hugocabral.exception.comum;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataDeNascimentoInvalidaException extends Exception{
    public DataDeNascimentoInvalidaException(String tipo) {
        super("Não é possível cadastrar " + tipo + " não nascidos.");
    }
}
