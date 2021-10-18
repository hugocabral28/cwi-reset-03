package br.com.cwi.reset.hugocabral.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionNomeESobrenome extends Exception {
    public ExceptionNomeESobrenome(String tipo) {
        super("Deve ser informado no mínimo nome e sobrenome para o " + tipo + ".");
    }
}
