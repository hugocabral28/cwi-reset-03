package br.com.cwi.reset.hugocabral.service;

import br.com.cwi.reset.hugocabral.FakeDatabase;
import br.com.cwi.reset.hugocabral.domain.Constantes;
import br.com.cwi.reset.hugocabral.domain.Diretor;
import br.com.cwi.reset.hugocabral.exception.*;
import br.com.cwi.reset.hugocabral.request.DiretorRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class DiretorService {

    private FakeDatabase fakeDatabase;
    private Integer sequenceIdDiretor = 0;

    public DiretorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void cadastrarDiretor(DiretorRequest diretorRequest) {
        try {
            validaCamposObrigatorios(diretorRequest);
            validaNomeESobrenome(diretorRequest);
            validaDataNascimento(diretorRequest);
            validaAnoInicioAtividade(diretorRequest);
            validaDuplicidadeCadastro(diretorRequest);
            sequenceIdDiretor = gerarIdAtor();
            final Diretor diretor = new Diretor(sequenceIdDiretor, diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade());
            fakeDatabase.persisteDiretor(diretor);

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
            throw new ExceptionSemCadastro(TipoDominioException.DIRETOR.getSingular(),TipoDominioException.DIRETOR.getPlural());
        }
        return list;
    }

    private Integer gerarIdAtor() {
        return ++sequenceIdDiretor;
    }

    private void validaCamposObrigatorios(DiretorRequest diretorRequest) throws ExceptionCampoInvalido {

        String nome = diretorRequest.getNome();
        if (nome == null || nome.equals("")) {
            throw new ExceptionCampoInvalido(Constantes.CAMPO_NOME);
        }

        LocalDate dataNascimento = diretorRequest.getDataNascimento();
        if (dataNascimento == null) {
            throw new ExceptionCampoInvalido(Constantes.CAMPO_DATA_NASCIMENTO);
        }

        Integer anoInicioAtividade = diretorRequest.getAnoInicioAtividade();
        if (anoInicioAtividade == null) {
            throw new ExceptionCampoInvalido(Constantes.CAMPO_ANO_INICIO_ATIVIDADE);
        }

    }

    private void validaNomeESobrenome(DiretorRequest diretorRequest) throws ExceptionNomeESobrenome {
        String[] nomeSobrenome = diretorRequest.getNome().trim().split(" ");
        if (nomeSobrenome.length < 2) {
            throw new ExceptionNomeESobrenome(TipoDominioException.DIRETOR.getSingular());
        }
    }

    private void validaDataNascimento(DiretorRequest diretorRequest) throws ExceptionDataDeNascimento {
        Integer anoAtual = LocalDate.now().getYear();
        Integer anoNascimento = diretorRequest.getDataNascimento().getYear();

        if (anoNascimento > anoAtual) {
            throw new ExceptionDataDeNascimento(TipoDominioException.DIRETOR.getSingular());
        }
    }

    private void validaAnoInicioAtividade(DiretorRequest diretorRequest) throws ExceptionAnoInicioAtividade {
        Integer anoNascimento = diretorRequest.getDataNascimento().getYear();
        Integer anoInicioAtividade = diretorRequest.getAnoInicioAtividade();

        if (anoInicioAtividade < anoNascimento) {
            throw new ExceptionAnoInicioAtividade(TipoDominioException.DIRETOR.getSingular());
        }
    }

    private void validaDuplicidadeCadastro(DiretorRequest diretorRequest) throws ExceptionCadastroDuplicado {
        String nomeDoDiretor = diretorRequest.getNome();
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        for (Diretor diretor : diretores) {
            if (diretor.getNome().equals(nomeDoDiretor)) {
                throw new ExceptionCadastroDuplicado(TipoDominioException.DIRETOR.getSingular(), nomeDoDiretor);
            }
        }
    }
}
