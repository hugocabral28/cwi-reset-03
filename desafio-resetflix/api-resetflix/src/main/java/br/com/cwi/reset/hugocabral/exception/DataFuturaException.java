package br.com.cwi.reset.hugocabral.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataFuturaException extends Exception{
    public DataFuturaException(String tipo) {
        super(String.format("Não é possível cadastrar %s do futuro.",tipo));
    }
}
