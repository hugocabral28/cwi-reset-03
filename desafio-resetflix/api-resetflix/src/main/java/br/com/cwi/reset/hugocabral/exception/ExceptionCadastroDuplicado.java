package br.com.cwi.reset.hugocabral.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionCadastroDuplicado extends Exception {
    public ExceptionCadastroDuplicado(String nome, String requestNome) {
        super("Já existe um "+ nome +" cadastrado para o nome " + requestNome + ".");
    }
}
