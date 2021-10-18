package br.com.cwi.reset.hugocabral.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionIdObrigatorio extends Exception {
    public ExceptionIdObrigatorio(String campo) {
        super("Campo obrigatório não informado. Favor informar o campo " + campo + ".");
    }
}
