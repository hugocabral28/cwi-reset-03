package br.com.cwi.reset.hugocabral.validator;

import br.com.cwi.reset.hugocabral.model.Genero;
import br.com.cwi.reset.hugocabral.exception.comum.CampoObrigatorioException;
import br.com.cwi.reset.hugocabral.request.FilmeRequest;

import java.util.List;

public class FilmeValidator {
    public void validaCamposObrigatorios(FilmeRequest filmeRequest) throws Exception {

        String nome = filmeRequest.getNome();
        if (nome == null || nome.equals("")) {
            throw new CampoObrigatorioException(Constantes.CAMPO_NOME);
        }

        Integer anoLancamento = filmeRequest.getAnoLancamento();
        if (anoLancamento == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_ANO_LANCAMENTO);
        }

        String capaFilme = filmeRequest.getCapaFilme();
        if (capaFilme == null || capaFilme.equals("")) {
            throw new CampoObrigatorioException(Constantes.CAMPO_CAPA_FILME);
        }

        List<Genero> listGenero = filmeRequest.getGeneros();
        if (listGenero == null || listGenero.equals("")) {
            throw new CampoObrigatorioException(Constantes.CAMPO_GENERO);
        }

        String resumo = filmeRequest.getResumo();
        if (resumo == null || resumo.equals("")) {
            throw new CampoObrigatorioException(Constantes.CAMPO_RESUMO);
        }

    }
}
