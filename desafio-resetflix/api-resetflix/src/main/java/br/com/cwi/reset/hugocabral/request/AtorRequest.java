package br.com.cwi.reset.hugocabral.request;

import br.com.cwi.reset.hugocabral.domain.Ator;
import br.com.cwi.reset.hugocabral.domain.StatusCarreira;

import java.time.LocalDate;

public class AtorRequest extends Ator {

    public AtorRequest(String nome, LocalDate dataNascimento, StatusCarreira statusCarreira, Integer anoInicioAtividade){
        super(nome, dataNascimento,statusCarreira, anoInicioAtividade);
    }

}
