package br.com.cwi.reset.hugocabral.validator;

import br.com.cwi.reset.hugocabral.exception.NaoPermitidoInformarException;
import br.com.cwi.reset.hugocabral.exception.TipoDominioException;
import br.com.cwi.reset.hugocabral.exception.CampoObrigatorioException;
import br.com.cwi.reset.hugocabral.request.PersonagemRequest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.isNull;

public class PersonagemValidator {

    public void validaCamposObrigatorios(final PersonagemRequest personagemRequest) throws Exception {
        if (isNull(personagemRequest.getIdAtor())) {
            throw new CampoObrigatorioException(Constantes.CAMPO_ID_ATOR);
        }

        if (isNull(personagemRequest.getNomePersonagem())) {
            throw new CampoObrigatorioException(Constantes.CAMPO_NOME_PERSONAGEM);
        }

        if (isNull(personagemRequest.getDescricaoPersonagem())) {
            throw new CampoObrigatorioException(Constantes.CAMPO_DESCRICAO_PERSONAGEM);
        }

        if (isNull(personagemRequest.getTipoAtuacao())) {
            throw new CampoObrigatorioException(Constantes.CAMPO_TIPO_ATUACAO);
        }
    }

    public void validarPersonagensAtoresFilme(final List<PersonagemRequest> personagens) throws Exception {

        final Set<PersonagemRequest> personagemRequestSet = new HashSet<>();

        for (PersonagemRequest personagemRequest : personagens) {
            validaCamposObrigatorios(personagemRequest);
            //Validar se um personagem já tem um ator
            if (personagemRequestSet.contains(personagemRequest)) {
                throw new NaoPermitidoInformarException(personagemRequest.getNomePersonagem(), TipoDominioException.FILME.getSingular());
            } else {
                personagemRequestSet.add(personagemRequest);
            }
        }
    }
}