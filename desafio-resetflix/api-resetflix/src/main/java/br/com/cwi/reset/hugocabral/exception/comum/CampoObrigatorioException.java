package br.com.cwi.reset.hugocabral.exception.comum;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CampoObrigatorioException extends Exception {
    public CampoObrigatorioException(String campo) {
            super(String.format("Campo obrigatório não informado. Favor informar o campo %s.", campo));
    }
}
