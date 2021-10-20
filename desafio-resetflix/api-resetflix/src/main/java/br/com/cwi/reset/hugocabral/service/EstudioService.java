package br.com.cwi.reset.hugocabral.service;

import br.com.cwi.reset.hugocabral.FakeDatabase;
import br.com.cwi.reset.hugocabral.domain.*;
import br.com.cwi.reset.hugocabral.exception.*;
import br.com.cwi.reset.hugocabral.request.EstudioRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class EstudioService {
    private FakeDatabase fakeDatabase;
    private Integer sequenceIdEstudio = 0;

    public EstudioService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarEstudio(EstudioRequest estudioRequest) throws Exception{
        validaCamposObrigatorios(estudioRequest);
        validaDataCriacao(estudioRequest);
        validaDuplicidadeCadastro(estudioRequest);

        sequenceIdEstudio = gerarIdEstudio();

        final Estudio estudio = new Estudio(sequenceIdEstudio, estudioRequest.getNome(), estudioRequest.getDescricao(), estudioRequest.getDataCriacao(),estudioRequest.getStatusAtividade());
        fakeDatabase.persisteEstudio(estudio);
    }

    public List<Estudio> consultarEstudios(Optional<String> filtroNome) throws Exception {
        final List<Estudio> estudiosCadastrados = fakeDatabase.recuperaEstudios();

        if (estudiosCadastrados.isEmpty()) {
            throw new SemCadastroException(TipoDominioException.ESTUDIO.getSingular(), TipoDominioException.ESTUDIO.getPlural());
        }

        final List<Estudio> retorno = new ArrayList<>();

        if (filtroNome.isPresent()) {
            for (Estudio estudio : estudiosCadastrados) {
                //Verificando se o filtroNome informado contem no nome
                final boolean containsFilter = estudiosCadastrados.stream()
                        .allMatch(atorlistadoFilter -> estudio.getNome()
                                .toLowerCase(Locale.ROOT)
                                .contains(filtroNome.get().toLowerCase(Locale.ROOT)));

                if (containsFilter) {
                    retorno.add(estudio);
                }
            }
        } else {
            retorno.addAll(estudiosCadastrados);
        }

        if (retorno.isEmpty() && filtroNome != null) {
            throw new FiltroNomeException(TipoDominioException.ESTUDIO.getSingular(), filtroNome.get());
        }

        return retorno;
    }

    public Estudio consultarEstudio(Integer id) throws Exception {

        if (id == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_ID);
        }

        final List<Estudio> estudios = fakeDatabase.recuperaEstudios();

        for (Estudio estudio : estudios) {
            if (id.equals(estudio.getId())) {
                return estudio;
            }
        }

        throw new ConsultaIdException(TipoDominioException.ESTUDIO.getSingular(), id);

    }

    private Integer gerarIdEstudio() {
        return ++sequenceIdEstudio;
    }

    private void validaCamposObrigatorios(EstudioRequest estudioRequest) throws CampoObrigatorioException {

        String nome = estudioRequest.getNome();
        if (nome == null || nome.equals("")) {
            throw new CampoObrigatorioException(Constantes.CAMPO_NOME);
        }

        String descricao = estudioRequest.getDescricao();
        if (descricao == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_DESCRICAO);
        }

        LocalDate dataCriacao = estudioRequest.getDataCriacao();
        if (dataCriacao == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_DATA_CRIACAO);
        }

        StatusAtividade statusAtividade = estudioRequest.getStatusAtividade();
        if (statusAtividade == null) {
            throw new CampoObrigatorioException(Constantes.CAMPO_STATUS_ATIVIDADE);
        }

    }

    private void validaDataCriacao(EstudioRequest estudioRequest) throws DataFuturaException {
        LocalDate anoAtual = LocalDate.now();
        LocalDate dataCriacao = estudioRequest.getDataCriacao();

        if (dataCriacao.isAfter(anoAtual)) {
            throw new DataFuturaException(TipoDominioException.ESTUDIO.getPlural());
        }
    }

    private void validaDuplicidadeCadastro(EstudioRequest estudioRequest) throws CadastroDuplicadoException {
        String nomeDoEstudio = estudioRequest.getNome();
        List<Estudio> estudios = fakeDatabase.recuperaEstudios();
        for (Estudio estudio : estudios) {
            if (estudio.getNome().toLowerCase(Locale.ROOT).equals(nomeDoEstudio.toLowerCase(Locale.ROOT))) {
                throw new CadastroDuplicadoException(TipoDominioException.ESTUDIO.getSingular(), nomeDoEstudio);
            }
        }
    }

}
