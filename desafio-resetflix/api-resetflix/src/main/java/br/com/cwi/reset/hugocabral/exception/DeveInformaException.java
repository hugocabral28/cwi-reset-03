package br.com.cwi.reset.hugocabral.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DeveInformaException extends Exception {
    public DeveInformaException(String requestNome, String tipo) {
        super(String.format("Não é permitido informar o mesmo %s mais de uma vez para o mesmo %s.", requestNome, tipo));
    }
}
