package br.com.cwi.reset.hugocabral.validator;

import br.com.cwi.reset.hugocabral.exception.*;
import br.com.cwi.reset.hugocabral.exception.AnoInicioAtividadeInvalidoException;
import br.com.cwi.reset.hugocabral.exception.CampoObrigatorioException;
import br.com.cwi.reset.hugocabral.exception.DataDeNascimentoInvalidaException;
import br.com.cwi.reset.hugocabral.exception.NomeESobrenomeException;


import java.time.LocalDate;


public class BasicInfoRequiredValidator {
    public void validaCamposObrigatorios(final String nome,
                                         final LocalDate dataNascimento,
                                         final Integer anoInicioAtividade,
                                         final TipoDominioException tipoDominioException) throws Exception {

        if (nome == null || nome.equals("")) {
            throw new CampoObrigatorioException(Constantes.CAMPO_NOME);
        }

        if (dataNascimento == null || dataNascimento.equals("")) {
            throw new CampoObrigatorioException(Constantes.CAMPO_DATA_NASCIMENTO);
        }

        if (anoInicioAtividade == null || anoInicioAtividade.equals("")) {
            throw new CampoObrigatorioException(Constantes.CAMPO_ANO_INICIO_ATIVIDADE);
        }
    }

    public void validaNomeESobrenome(final String nomeSobrenome, final TipoDominioException tipoDominioException) throws NomeESobrenomeException {
        if (nomeSobrenome.trim().split(" ").length < 2) {
            throw new NomeESobrenomeException(tipoDominioException.getSingular());
        }
    }

    public void validaDataNascimento(final LocalDate dataNascimento, final TipoDominioException tipoDominioException) throws DataDeNascimentoInvalidaException {
        Integer anoAtual = LocalDate.now().getYear();
        Integer anoNascimento = dataNascimento.getYear();

        if (anoNascimento > anoAtual) {
            throw new DataDeNascimentoInvalidaException(tipoDominioException.getSingular());
        }
    }

    public void validaAnoInicioAtividade(final LocalDate dataNascimento,
                                         final Integer anoInicioAtividade,
                                         TipoDominioException tipoDominioException) throws AnoInicioAtividadeInvalidoException {
        Integer anoNascimento = dataNascimento.getYear();

        if (anoInicioAtividade < anoNascimento) {
            throw new AnoInicioAtividadeInvalidoException(tipoDominioException.getSingular());
        }
    }

}
