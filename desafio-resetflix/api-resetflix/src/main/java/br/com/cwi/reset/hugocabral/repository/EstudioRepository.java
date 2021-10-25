package br.com.cwi.reset.hugocabral.repository;

import br.com.cwi.reset.hugocabral.model.Estudio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudioRepository extends CrudRepository<Estudio, Integer> {
    Estudio findByNome(String nome);
    List<Estudio> findAll();
}
