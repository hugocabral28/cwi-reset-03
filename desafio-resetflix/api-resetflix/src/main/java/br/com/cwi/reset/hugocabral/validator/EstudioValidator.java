package br.com.cwi.reset.hugocabral.validator;

import br.com.cwi.reset.hugocabral.exception.TipoDominioException;
import br.com.cwi.reset.hugocabral.exception.comum.CampoObrigatorioException;
import br.com.cwi.reset.hugocabral.exception.comum.DataFuturaException;
import br.com.cwi.reset.hugocabral.request.EstudioRequest;

import java.time.LocalDate;

import static java.util.Objects.isNull;

public class EstudioValidator {

    public void validaCamposObrigatorios(EstudioRequest estudioRequest) throws Exception {
        if (isNull(estudioRequest.getNome())) {
            throw new CampoObrigatorioException(Constantes.CAMPO_NOME);
        }

        if (isNull(estudioRequest.getDataCriacao())) {
            throw new CampoObrigatorioException(Constantes.CAMPO_DATA_CRIACAO);
        }

        if (isNull(estudioRequest.getDescricao())) {
            throw new CampoObrigatorioException(Constantes.CAMPO_DESCRICAO);
        }

        if (isNull(estudioRequest.getStatusAtividade())) {
            throw new CampoObrigatorioException(Constantes.CAMPO_STATUS_ATIVIDADE);
        }
    }

    public void validaDataCriacao(LocalDate dataCriacao, TipoDominioException tipoDominioException) throws DataFuturaException {
        LocalDate anoAtual = LocalDate.now();
        if (dataCriacao.isAfter(anoAtual)) {
            throw new DataFuturaException(tipoDominioException.getPlural());
        }
    }

}