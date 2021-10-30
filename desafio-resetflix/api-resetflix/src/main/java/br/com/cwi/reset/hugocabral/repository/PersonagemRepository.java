package br.com.cwi.reset.hugocabral.repository;

import br.com.cwi.reset.hugocabral.model.Ator;
import br.com.cwi.reset.hugocabral.model.PersonagemAtor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonagemRepository extends CrudRepository<PersonagemAtor, Integer> {

    List<PersonagemAtor> findAll();

    List<PersonagemAtor> findAllByAtorIn(List<Ator> atores);

    List<PersonagemAtor> findAllByNomePersonagemContainingIgnoreCaseAndAtorIn(String nomePersonagem, List<Ator> atores);
}
