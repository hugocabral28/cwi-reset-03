package br.com.cwi.reset.hugocabral;

import br.com.cwi.reset.hugocabral.domain.Ator;
import br.com.cwi.reset.hugocabral.domain.Diretor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeDatabase {

    private List<Ator> atores = new ArrayList<>();
    private List<Diretor> diretores = new ArrayList<>();

    public void persisteAtor(Ator ator) {
        atores.add(ator);
    }

    public List<Ator> recuperaAtores() {
        return atores;
    }

    public Optional<Ator> buscarAtorPorId(Integer id) {
        Optional<Ator> first = atores.stream()
                .filter(ator -> id.equals(ator.getId()))
                .findFirst();
        return first;
    }

    public void persisteDiretor(Diretor diretor) {
        diretores.add(diretor);
    }

    public List<Diretor> recuperaDiretores() {
        return diretores;
    }
}

