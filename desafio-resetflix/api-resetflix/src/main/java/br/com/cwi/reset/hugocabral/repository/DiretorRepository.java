package br.com.cwi.reset.hugocabral.repository;

import br.com.cwi.reset.hugocabral.model.Diretor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiretorRepository extends CrudRepository<Diretor, Integer> {
    Diretor findByNome(String nome);

    List<Diretor> findAll();

    Diretor findAllById(Integer id);
}
