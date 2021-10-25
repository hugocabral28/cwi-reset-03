package br.com.cwi.reset.projeto1.service;

import br.com.cwi.reset.projeto1.domain.Ator;
import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.AtorNaoExistenteException;
import br.com.cwi.reset.projeto1.repository.AtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtorService {

    @Autowired
    private AtorRepository repository;

    public Ator cadastrarAtor(Ator ator) throws AtorNaoExistenteException{
        Ator atorExists = repository.findByNome(ator.getNome());
        if(atorExists == null){
            throw new AtorNaoExistenteException(ator.getNome());
        }
        return repository.save(ator);
    }
}
