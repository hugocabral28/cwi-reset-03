package br.com.cwi.reset.hugocabral.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SemCadastroException extends Exception{
    public SemCadastroException(String tipo, String tipoPlural) {
        super(String.format("Nenhum %s cadastrado, favor cadastar %s.", tipo, tipoPlural));
    }
}
