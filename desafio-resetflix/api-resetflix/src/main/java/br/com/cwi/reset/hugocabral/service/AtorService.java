package br.com.cwi.reset.hugocabral.service;


import br.com.cwi.reset.hugocabral.FakeDatabase;
import br.com.cwi.reset.hugocabral.domain.StatusCarreira;
import br.com.cwi.reset.hugocabral.exception.ExceptionAnoInicioAtividade;
import br.com.cwi.reset.hugocabral.exception.ExceptionCampoInvalido;
import br.com.cwi.reset.hugocabral.exception.ExceptionDataDeNascimento;
import br.com.cwi.reset.hugocabral.exception.ExceptionNomeESobrenome;
import br.com.cwi.reset.hugocabral.request.AtorRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AtorService {
    private final String atorOuAtriz = "Ator ou Atriz";

    private FakeDatabase fakeDatabase;
    private Integer sequenceIdAtor = 0;

    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarAtor(AtorRequest atorRequest) {
        try {
            validaCamposObrigatorios(atorRequest);
            validaNomeESobrenome(atorRequest);
            validaDataNascimento(atorRequest);
            validaAnoInicioAtividade(atorRequest);

            atorRequest.setId(gerarIdAtor());
            fakeDatabase.persisteAtor(atorRequest);

        }//Caso ExceptionCampoInvalido
        catch (ExceptionCampoInvalido e) {
            e.printStackTrace();
        }
        //Caso ExceptionNomeESobrenome
        catch (ExceptionNomeESobrenome e) {
            e.printStackTrace();
        }
        //Caso ExceptionDataDeNascimento
        catch (ExceptionDataDeNascimento e) {
            e.printStackTrace();
        }
        //Caso ExceptionAnoInicioAtividade
        catch (ExceptionAnoInicioAtividade e) {
            e.printStackTrace();
        }
    }

    // Demais métodos da classe
    private Integer gerarIdAtor() {
        return ++sequenceIdAtor;
    }

    private void validaCamposObrigatorios(AtorRequest atorRequest) throws ExceptionCampoInvalido {
        List<String> campo = new ArrayList<String>();
        boolean campoNulo = false;

        String nome = atorRequest.getNome();
        if (nome == null || nome.equals("")) {
            campo.add(atorRequest.CAMPO_NOME);
            campoNulo = true;
        }

        LocalDate dataNascimento = atorRequest.getDataNascimento();
        if (dataNascimento == null) {
            campo.add(AtorRequest.CAMPO_DATA_NASCIMENTO);
            campoNulo = true;
        }

        Integer anoInicioAtividade = atorRequest.getAnoInicioAtividade();
        if (anoInicioAtividade == null) {
            campo.add(atorRequest.CAMPO_ANO_INICIO_ATIVIDADE);
            campoNulo = true;
        }

        StatusCarreira statusCarreira = atorRequest.getStatusCarreira();
        if (statusCarreira == null) {
            campo.add(atorRequest.CAMPO_STATUS_CARREIRA);
            campoNulo = true;
        }

        //Os campos que for null active o ExceptionCampoInvalido
        if (campoNulo) {
            throw new ExceptionCampoInvalido(campo);
        }
    }

    private void validaNomeESobrenome(AtorRequest atorRequest) throws ExceptionNomeESobrenome {
        String[] nomeSobrenome = atorRequest.getNome().split(" ");
        if (nomeSobrenome.length < 2) {
            throw new ExceptionNomeESobrenome(atorOuAtriz);
        }
    }

    private void validaDataNascimento(AtorRequest atorRequest) throws ExceptionDataDeNascimento {
        Integer anoAtual = LocalDate.now().getYear();
        Integer anoNascimento = atorRequest.getDataNascimento().getYear();

        if (anoNascimento > anoAtual) {
            throw new ExceptionDataDeNascimento(atorOuAtriz);
        }
    }

    private void validaAnoInicioAtividade(AtorRequest atorRequest) throws ExceptionAnoInicioAtividade {
        Integer anoNascimento = atorRequest.getDataNascimento().getYear();
        Integer anoInicioAtividade = atorRequest.getAnoInicioAtividade();

        if (anoInicioAtividade < anoNascimento) {
            throw new ExceptionAnoInicioAtividade(atorOuAtriz);
        }
    }


}
