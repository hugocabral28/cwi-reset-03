package br.com.cwi.reset.hugocabral.repository;

import br.com.cwi.reset.hugocabral.model.Ator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AtorRepository extends CrudRepository<Ator, Integer> {

    Ator findByNome(String nome);

    List<Ator> findAll();

}