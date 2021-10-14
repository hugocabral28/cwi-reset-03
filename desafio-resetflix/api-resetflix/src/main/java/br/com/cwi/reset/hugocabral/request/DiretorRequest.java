package br.com.cwi.reset.hugocabral.request;


import br.com.cwi.reset.hugocabral.domain.Diretor;

import java.time.LocalDate;

public class DiretorRequest extends Diretor {
    public DiretorRequest(String nome, LocalDate dataNascimento,  Integer anoInicioAtividade){
        super(nome, dataNascimento, anoInicioAtividade);
    }
}
