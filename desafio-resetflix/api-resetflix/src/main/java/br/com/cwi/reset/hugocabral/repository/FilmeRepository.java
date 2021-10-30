package br.com.cwi.reset.hugocabral.repository;

import br.com.cwi.reset.hugocabral.model.Diretor;
import br.com.cwi.reset.hugocabral.model.Filme;
import br.com.cwi.reset.hugocabral.model.PersonagemAtor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends CrudRepository<Filme, Integer> {
    Filme findByNome(String nome);

    List<Filme> findAll();

    Filme findAllById(Integer id);

    boolean existsBy();

    List<Filme> findAllByDiretorInAndPersonagensIn(List<Diretor> diretores, List<PersonagemAtor> personagens);

    List<Filme> findAllByNomeContainingIgnoreCaseAndDiretorInAndPersonagensIn(String nomeFilme, List<Diretor> diretores, List<PersonagemAtor> personagens);
}
