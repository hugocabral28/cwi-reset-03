package br.com.cwi.reset.projeto1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PetNaoExistenteException extends Exception{
    public PetNaoExistenteException(String nome) {
        super(String.format("Filme com o nome %s não existe",nome));
    }
}
