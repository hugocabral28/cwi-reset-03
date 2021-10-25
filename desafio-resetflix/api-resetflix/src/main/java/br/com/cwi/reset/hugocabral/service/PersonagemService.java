package br.com.cwi.reset.hugocabral.service;

import br.com.cwi.reset.hugocabral.FakeDatabase;
import br.com.cwi.reset.hugocabral.model.*;
import br.com.cwi.reset.hugocabral.request.PersonagemRequest;
import br.com.cwi.reset.hugocabral.validator.PersonagemValidator;

import java.util.ArrayList;
import java.util.List;


public class PersonagemService {

    private FakeDatabase fakeDatabase;
    private Integer sequenceIdPersonagem = 0;
    private AtorService atorService;
    private PersonagemValidator validator;

    public PersonagemService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.atorService = new AtorService(fakeDatabase);
        this.validator = new PersonagemValidator();
    }

    public List<PersonagemAtor> cadastrarPersonagensFilme(final List<PersonagemRequest> personagens) throws Exception {
        validator.validarPersonagensAtoresFilme(personagens);

        final List<PersonagemAtor> personagensAtores = new ArrayList<>();

        for (PersonagemRequest request : personagens) {
            personagensAtores.add(criarPersonagem(request));
        }

        return personagensAtores;
    }

    public PersonagemAtor criarPersonagem(PersonagemRequest personagemAtorRequest) throws Exception {
        /* ### Validações ### */
        validator.validaCamposObrigatorios(personagemAtorRequest);

        /* ### Cadastrando ### */
        final Ator ator = atorService.consultarAtor(personagemAtorRequest.getIdAtor());
        sequenceIdPersonagem = gerarIdPersonagem();
        final PersonagemAtor personagemAtor = new PersonagemAtor(sequenceIdPersonagem,
                ator,
                personagemAtorRequest.getNomePersonagem(),
                personagemAtorRequest.getDescricaoPersonagem(),
                personagemAtorRequest.getTipoAtuacao());

        fakeDatabase.persistePersonagem(personagemAtor);

        return personagemAtor;
    }
    public List<PersonagemAtor> consultarPersonagemAtor(String nome) throws Exception {
        return fakeDatabase.recuperaPersonagens();
    }

    private Integer gerarIdPersonagem() {
        return ++sequenceIdPersonagem;
    }


}
