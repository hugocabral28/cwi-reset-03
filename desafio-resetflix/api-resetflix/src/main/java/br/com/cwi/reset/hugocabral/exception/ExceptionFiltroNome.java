package br.com.cwi.reset.hugocabral.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionFiltroNome extends Exception{
    public ExceptionFiltroNome(String tipo, String requestNome) {
        super(tipo + " não encontrato com o filtro "+ requestNome + ", favor informar outro filtro.");
    }
}
