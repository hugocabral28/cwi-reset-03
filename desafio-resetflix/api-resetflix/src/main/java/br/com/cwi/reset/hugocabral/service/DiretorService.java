package br.com.cwi.reset.hugocabral.service;

import br.com.cwi.reset.hugocabral.FakeDatabase;
import br.com.cwi.reset.hugocabral.domain.Diretor;
import br.com.cwi.reset.hugocabral.exception.*;
import br.com.cwi.reset.hugocabral.request.AtorRequest;
import br.com.cwi.reset.hugocabral.request.DiretorRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DiretorService {
    private final String diretorOuDiretora = "Diretor ou Diretora";

    private FakeDatabase fakeDatabase;
    private Integer sequenceIdDiretor = 0;

    public DiretorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void cadastrarDiretor(DiretorRequest diretorRequest) {
        try {
            diretorRequest.setId(gerarIdAtor());
            validaCamposObrigatorios(diretorRequest);
            validaNomeESobrenome(diretorRequest);
            validaDataNascimento(diretorRequest);
            validaAnoInicioAtividade(diretorRequest);
            validaDuplicidadeCadastro(diretorRequest);

            fakeDatabase.persisteDiretor(diretorRequest);

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
        //Caso ExceptionCadastroDuplicado
        catch (ExceptionCadastroDuplicado e) {
            e.printStackTrace();
        }
    }

    // Demais métodos da classe
    public Optional<Diretor> consultarDiretor(Integer id) throws ExceptionIdObrigatorio {

        if (id == null) {
            throw new ExceptionIdObrigatorio("id");
        }

        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        //filtrando Diretor pelo id
        Optional<Diretor> diretorEncontrado = diretores.stream()
                .filter(diretor -> id.equals(diretor.getId()))
                .findFirst();

        if (!diretorEncontrado.isPresent()) {
            throw new ExceptionIdObrigatorio("id");
        }

        return diretorEncontrado;
    }

    public List<Diretor> consultarDiretor() throws ExceptionSemCadastro {
        List<Diretor> list = fakeDatabase.recuperaDiretores();
        if (list.isEmpty()) {
            throw new ExceptionSemCadastro("Diretor");
        }
        return list;
    }

    private Integer gerarIdAtor() {
        return ++sequenceIdDiretor;
    }

    private void validaCamposObrigatorios(DiretorRequest diretorRequest) throws ExceptionCampoInvalido {
        List<String> campo = new ArrayList();
        boolean campoNulo = false;

        String nome = diretorRequest.getNome();
        if (nome == null || nome.equals("")) {
            campo.add(diretorRequest.CAMPO_NOME);
            campoNulo = true;
        }

        LocalDate dataNascimento = diretorRequest.getDataNascimento();
        if (dataNascimento == null) {
            campo.add(AtorRequest.CAMPO_DATA_NASCIMENTO);
            campoNulo = true;
        }

        Integer anoInicioAtividade = diretorRequest.getAnoInicioAtividade();
        if (anoInicioAtividade == null) {
            campo.add(diretorRequest.CAMPO_ANO_INICIO_ATIVIDADE);
            campoNulo = true;
        }

        //Os campos que for null active o ExceptionCampoInvalido
        if (campoNulo) {
            throw new ExceptionCampoInvalido(campo);
        }
    }

    private void validaNomeESobrenome(DiretorRequest diretorRequest) throws ExceptionNomeESobrenome {
        String[] nomeSobrenome = diretorRequest.getNome().trim().split(" ");
        if (nomeSobrenome.length < 2) {
            throw new ExceptionNomeESobrenome(diretorOuDiretora);
        }
    }

    private void validaDataNascimento(DiretorRequest diretorRequest) throws ExceptionDataDeNascimento {
        Integer anoAtual = LocalDate.now().getYear();
        Integer anoNascimento = diretorRequest.getDataNascimento().getYear();

        if (anoNascimento > anoAtual) {
            throw new ExceptionDataDeNascimento(diretorOuDiretora);
        }
    }

    private void validaAnoInicioAtividade(DiretorRequest diretorRequest) throws ExceptionAnoInicioAtividade {
        Integer anoNascimento = diretorRequest.getDataNascimento().getYear();
        Integer anoInicioAtividade = diretorRequest.getAnoInicioAtividade();

        if (anoInicioAtividade < anoNascimento) {
            throw new ExceptionAnoInicioAtividade(diretorOuDiretora);
        }
    }

    private void validaDuplicidadeCadastro(DiretorRequest diretorRequest) throws ExceptionCadastroDuplicado {
        String nomeDoDiretor = diretorRequest.getNome();
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        for (Diretor diretor : diretores) {
            if (diretor.getNome().equals(nomeDoDiretor)) {
                throw new ExceptionCadastroDuplicado(diretorOuDiretora, nomeDoDiretor);
            }
        }
    }
}
