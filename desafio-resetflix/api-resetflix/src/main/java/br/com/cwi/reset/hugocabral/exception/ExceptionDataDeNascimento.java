package br.com.cwi.reset.hugocabral.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionDataDeNascimento extends Exception{
    public ExceptionDataDeNascimento(String nome) {
        super("Não é possível cadastrar " + nome + " não nascidos.");
    }
}
