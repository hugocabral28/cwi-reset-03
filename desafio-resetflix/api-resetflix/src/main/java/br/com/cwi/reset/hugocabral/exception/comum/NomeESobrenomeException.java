package br.com.cwi.reset.hugocabral.exception.comum;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NomeESobrenomeException extends Exception {
    public NomeESobrenomeException(String tipo) {
        super("Deve ser informado no mínimo nome e sobrenome para o " + tipo + ".");
    }
}
