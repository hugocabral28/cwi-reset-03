package br.com.cwi.reset.hugocabral.service;

import br.com.cwi.reset.hugocabral.FakeDatabase;
import br.com.cwi.reset.hugocabral.model.Diretor;
import br.com.cwi.reset.hugocabral.exception.*;
import br.com.cwi.reset.hugocabral.exception.comum.*;
import br.com.cwi.reset.hugocabral.request.DiretorRequest;
import br.com.cwi.reset.hugocabral.validator.BasicInfoRequiredValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


public class DiretorService {

    private FakeDatabase fakeDatabase;
    private Integer sequenceIdDiretor = 0;
    private BasicInfoRequiredValidator validator;

    public DiretorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.validator = new BasicInfoRequiredValidator();
    }

    public void cadastrarDiretor(DiretorRequest diretorRequest) throws Exception {

        /* ### Validações ### */
        validator.validaCamposObrigatorios(diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade(), TipoDominioException.DIRETOR);
        validator.validaDataNascimento(diretorRequest.getDataNascimento(), TipoDominioException.DIRETOR);
        validator.validaNomeESobrenome(diretorRequest.getNome(), TipoDominioException.DIRETOR);
        validator.validaAnoInicioAtividade(diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade(), TipoDominioException.DIRETOR);

        final List<Diretor> diretoresCadastrados = fakeDatabase.recuperaDiretores();
        for (Diretor diretorCadastrado : diretoresCadastrados) {
            if (diretorCadastrado.getNome().equalsIgnoreCase(diretorRequest.getNome())) {
                throw new CadastroDuplicadoException(TipoDominioException.DIRETOR.getSingular(), diretorRequest.getNome());
            }
        }

        /* ### Cadastrando ### */
        sequenceIdDiretor = gerarIdDiretor();
        final Diretor diretor = new Diretor(sequenceIdDiretor, diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade());
        fakeDatabase.persisteDiretor(diretor);
    }

    // Demais métodos da classe
    public List<Diretor> listarDiretores(Optional<String> filtroNome) throws Exception {
        final List<Diretor> diretoresCadastrados = fakeDatabase.recuperaDiretores();

        if (diretoresCadastrados.isEmpty()) {
            throw new SemCadastroException(TipoDominioException.DIRETOR.getSingular(), TipoDominioException.DIRETOR.getPlural());
        }

        final List<Diretor> retorno = new ArrayList<>();

        if (filtroNome.isPresent()) {
            for (Diretor diretor : diretoresCadastrados) {
                //Verificando se o filtroNome informado contem no nome
                final boolean containsFilter = diretoresCadastrados.stream()
                        .allMatch(atorlistadoFilter -> diretor.getNome()
                                .toLowerCase(Locale.ROOT)
                                .contains(filtroNome.get().toLowerCase(Locale.ROOT)));

                if (containsFilter) {
                    retorno.add(diretor);
                }
            }
        } else {
            retorno.addAll(diretoresCadastrados);
        }

        if (retorno.isEmpty() && filtroNome != null) {
            throw new FiltroNomeException(TipoDominioException.DIRETOR.getSingular(), filtroNome.get());
        }

        return retorno;
    }

    public Diretor consultarDiretor(Integer id) throws Exception {

        if (id == null) {
            throw new ConsultaIdException(TipoDominioException.DIRETOR.getSingular(), id);
        }

        final List<Diretor> diretores = fakeDatabase.recuperaDiretores();

        for (Diretor diretore : diretores) {
            if (id.equals(diretore.getId())) {
                return diretore;
            }
        }

        throw new ConsultaIdException(TipoDominioException.DIRETOR.getSingular(), id);

    }

    public List<Diretor> consultarDiretor() throws SemCadastroException {
        List<Diretor> list = fakeDatabase.recuperaDiretores();
        if (list.isEmpty()) {
            throw new SemCadastroException(TipoDominioException.DIRETOR.getSingular(), TipoDominioException.DIRETOR.getPlural());
        }
        return list;
    }

    private Integer gerarIdDiretor() {
        return ++sequenceIdDiretor;
    }

}
