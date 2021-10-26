package br.com.cwi.reset.hugocabral.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VinculadoFilmeException extends Exception{
    public VinculadoFilmeException(String message) {
        super(message);
    }
}
