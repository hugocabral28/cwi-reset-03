package br.com.cwi.reset.hugocabral.service;

import br.com.cwi.reset.hugocabral.model.*;
import br.com.cwi.reset.hugocabral.repository.PersonagemRepository;
import br.com.cwi.reset.hugocabral.request.PersonagemRequest;
import br.com.cwi.reset.hugocabral.validator.PersonagemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;
    @Autowired
    private AtorService atorService;
    private PersonagemValidator validator;

    public PersonagemService() {
        this.atorService = new AtorService();
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

        final PersonagemAtor personagemAtor = new PersonagemAtor(
                ator,
                personagemAtorRequest.getNomePersonagem(),
                personagemAtorRequest.getDescricaoPersonagem(),
                personagemAtorRequest.getTipoAtuacao());

        personagemRepository.save(personagemAtor);

        return personagemAtor;
    }
    public List<PersonagemAtor> consultarPersonagemAtor(String nome) throws Exception {
        return personagemRepository.findAll();
    }

    public void removerPersonagem(final List<PersonagemAtor> personagens) throws Exception {
        this.personagemRepository.deleteAll(personagens);
    }

}
