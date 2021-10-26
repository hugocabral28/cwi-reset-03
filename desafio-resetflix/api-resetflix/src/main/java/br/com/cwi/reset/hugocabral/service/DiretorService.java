package br.com.cwi.reset.hugocabral.service;

import br.com.cwi.reset.hugocabral.model.Diretor;
import br.com.cwi.reset.hugocabral.exception.*;
import br.com.cwi.reset.hugocabral.repository.DiretorRepository;
import br.com.cwi.reset.hugocabral.request.DiretorRequest;
import br.com.cwi.reset.hugocabral.validator.BasicInfoRequiredValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepository repository;

    private BasicInfoRequiredValidator validator;

    public DiretorService() {
        this.validator = new BasicInfoRequiredValidator();
    }

    public void cadastrarDiretor(DiretorRequest diretorRequest) throws Exception {

        /* ### Validações ### */
        validator.validaCamposObrigatorios(diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade(), TipoDominioException.DIRETOR);
        validator.validaDataNascimento(diretorRequest.getDataNascimento(), TipoDominioException.DIRETOR);
        validator.validaNomeESobrenome(diretorRequest.getNome(), TipoDominioException.DIRETOR);
        validator.validaAnoInicioAtividade(diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade(), TipoDominioException.DIRETOR);
        validaDuplicidadeCadastro(diretorRequest);

        /* ### Cadastrando ### */
        final Diretor diretor = new Diretor(diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade());
        repository.save(diretor);
    }

    // Demais métodos da classe
    public List<Diretor> listarDiretores(Optional<String> filtroNome) throws Exception {
        final List<Diretor> diretoresCadastrados = repository.findAll();

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

        final List<Diretor> diretores = repository.findAll();

        for (Diretor diretore : diretores) {
            if (id.equals(diretore.getId())) {
                return diretore;
            }
        }

        throw new ConsultaIdException(TipoDominioException.DIRETOR.getSingular(), id);

    }

    public List<Diretor> consultarDiretor() throws SemCadastroException {
        List<Diretor> list = repository.findAll();
        if (list.isEmpty()) {
            throw new SemCadastroException(TipoDominioException.DIRETOR.getSingular(), TipoDominioException.DIRETOR.getPlural());
        }
        return list;
    }

    public void atualizarDiretor(Integer id, DiretorRequest diretorRequest) throws Exception{
        boolean temDiretor = repository.existsById(id);
        if(temDiretor){
            Diretor diretor = repository.findAllById(id);
            validator.validaAnoInicioAtividade(diretorRequest.getDataNascimento(),diretorRequest.getAnoInicioAtividade(),TipoDominioException.DIRETOR);
            validator.validaNomeESobrenome(diretorRequest.getNome(),TipoDominioException.DIRETOR);
            validaDuplicidadeCadastro(diretorRequest);

            diretor.setNome(diretorRequest.getNome());
            diretor.setDataNascimento(diretorRequest.getDataNascimento());
            diretor.setAnoInicioAtividade(diretorRequest.getAnoInicioAtividade());
            repository.save(diretor);
        }else{
            throw new ConsultaIdException(TipoDominioException.DIRETOR.getSingular(),id);
        }
    }

    public void removerDiretores(Integer id) throws Exception {
        boolean temDiretores = repository.existsById(id);
        if(!temDiretores){
            throw new ConsultaIdException(TipoDominioException.DIRETOR.getSingular(),id);
        }

//        boolean diretorTemFilme = service.consultarDiretorFilme(id);
//        if(diretorTemFilme){
//            throw new Exception("Este diretor está vinculado a um ou mais filmes, para remover o diretor é necessário remover os seus filmes de participação.");
//        }

        Diretor diretor = repository.findAllById(id);
        try {
            repository.delete(diretor);
        }catch (Exception e){
            throw new VinculadoFilmeException("Este diretor está vinculado a um ou mais filmes, para remover o diretor é necessário remover os seus filmes de participação.");
        }
    }

    private void validaDuplicidadeCadastro(DiretorRequest diretorRequests) throws CadastroDuplicadoException {
        String nomeDoDiretor = diretorRequests.getNome();
        List<Diretor> diretores = repository.findAll();

        for (Diretor diretor : diretores) {
            if (diretor.getNome().toLowerCase(Locale.ROOT).equals(nomeDoDiretor.toLowerCase(Locale.ROOT))) {
                if(!repository.existsById(diretor.getId())){
                    throw new CadastroDuplicadoException(TipoDominioException.DIRETOR.getSingular(), nomeDoDiretor);
                }
            }
        }
    }


}
