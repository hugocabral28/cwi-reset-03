package br.com.cwi.reset.hugocabral.exception;

import java.util.List;

public class ExceptionCampoInvalido extends Exception {
    public ExceptionCampoInvalido(List<String> campos) {
        for(int i = 0; i < campos.size(); i++){
            System.out.println("Campo obrigatório não informado. Favor informar o campo " + campos.get(i) + ".");
        }
    }
}
