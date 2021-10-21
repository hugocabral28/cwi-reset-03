package br.com.cwi.reset.hugocabral.request;

import br.com.cwi.reset.hugocabral.domain.Diretor;
import br.com.cwi.reset.hugocabral.domain.Estudio;
import br.com.cwi.reset.hugocabral.domain.Genero;

import java.util.List;

public class FilmeRequest {
    private String nome;
    private Integer anoLancamento;
    private String capaFilme;
    private List<Genero> genero;
    private Integer idDiretor;
    private Integer idEstudio;
    private String resumo;
    private List<PersonagemAtorRequest> personagens;

    public FilmeRequest(String nome,
                        Integer anoLancamento,
                        String capaFilme,
                        List<Genero> genero,
                        Diretor idDiretor,
                        Estudio idEstudio,
                        List<PersonagemAtorRequest> personagens,
                        String resumo
    ) {
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.capaFilme = capaFilme;
        this.genero = genero;
        this.idDiretor = idDiretor.getId();
        this.idEstudio = idEstudio.getId();
        this.resumo = resumo;
        this.personagens = personagens;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getCapaFilme() {
        return capaFilme;
    }

    public void setCapaFilme(String capaFilme) {
        this.capaFilme = capaFilme;
    }

    public List<Genero> getGenero() {
        return genero;
    }

    public void setGenero(List<Genero> genero) {
        this.genero = genero;
    }

    public Integer getIdDiretor() {
        return idDiretor;
    }

    public void setIdDiretor(Integer idDiretor) {
        this.idDiretor = idDiretor;
    }

    public Integer getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(Integer idEstudio) {
        this.idEstudio = idEstudio;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public List<PersonagemAtorRequest> getPersonagens() {
        return personagens;
    }

    public void setPersonagens(List<PersonagemAtorRequest> personagens) {
        this.personagens = personagens;
    }
}
