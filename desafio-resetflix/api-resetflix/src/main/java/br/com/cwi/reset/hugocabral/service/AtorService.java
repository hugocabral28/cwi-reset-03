package br.com.cwi.reset.hugocabral.service;


import br.com.cwi.reset.hugocabral.FakeDatabase;
import br.com.cwi.reset.hugocabral.request.AtorRequest;

public class AtorService {

    private FakeDatabase fakeDatabase;
    private Integer sequenceIdAtor = 0;

    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarAtor(AtorRequest atorRequest){
        atorRequest.setId(gerarIdAtor());
        fakeDatabase.persisteAtor(atorRequest);
    }
    // Demais métodos da classe

    private Integer gerarIdAtor(){
        return ++sequenceIdAtor;
    }
}
