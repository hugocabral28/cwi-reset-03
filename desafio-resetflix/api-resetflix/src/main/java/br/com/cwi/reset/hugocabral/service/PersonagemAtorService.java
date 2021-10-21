package br.com.cwi.reset.hugocabral.service;

import br.com.cwi.reset.hugocabral.FakeDatabase;
import br.com.cwi.reset.hugocabral.domain.*;
import br.com.cwi.reset.hugocabral.exception.CampoObrigatorioException;
import br.com.cwi.reset.hugocabral.exception.ConsultaIdException;
import br.com.cwi.reset.hugocabral.exception.TipoDominioException;
import br.com.cwi.reset.hugocabral.request.PersonagemAtorRequest;

import java.util.ArrayList;
import java.util.List;

public class PersonagemAtorService {

    private FakeDatabase fakeDatabase;
    private Integer sequenceIdPersonagem = 0;
    private AtorService atorService;

    public PersonagemAtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public List<PersonagemAtor> criarPersonagem(List<PersonagemAtorRequest> personagenRequest) throws Exception {

        List<Ator> atoresListados = fakeDatabase.recuperaAtores();
        List<PersonagemAtor> listPersonagem = new ArrayList<>();

        for (PersonagemAtorRequest personagem : personagenRequest) {
            validaCamposObrigatorios(personagem);

            Ator ator = atorService.consultarAtor(personagem.getIdAtor());
            sequenceIdPersonagem = gerarIdPersonagem();
            PersonagemAtor personagemList = new PersonagemAtor(sequenceIdPersonagem,
                    ator,
                    personagem.getNomePersonagem(),
                    personagem.getDescricaoPersonagem(),
                    personagem.getTipoAtuacao());

            listPersonagem.add(personagemList);
        }

        return listPersonagem;
    }

    private Integer gerarIdPersonagem() {
        return ++sequenceIdPersonagem;
    }

    private void validaCamposObrigatorios(PersonagemAtorRequest personagemAtorRequest) throws CampoObrigatorioException {

//        Integer idAtor = personagemAtorRequest.getIdAtor();
//        if (idAtor == null) {
//            throw new ConsultaIdException(TipoDominioException.ATOR.getSingular(), idAtor);
//        }

        String nome = personagemAtorRequest.getNomePersonagem();
        if (nome == null || nome.equals("")) {
            throw new CampoObrigatorioException(Constantes.CAMPO_NOME_PERSONAGEM);
        }

        String descricao = personagemAtorRequest.getDescricaoPersonagem();
        if (descricao == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_DESCRICAO_PERSONAGEM);
        }

        TipoAtuacao tipoAtuacao = personagemAtorRequest.getTipoAtuacao();
        if (tipoAtuacao == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_TIPO_ATUACAO);
        }
    }

}
