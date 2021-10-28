package br.com.cwi.reset.hugocabral.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Integer anoLancamento;
    private String capaFilme;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Genero> genero;
    @ManyToOne
    @JoinColumn(name = "id_diretor")
    private Diretor diretor;
    @ManyToOne
    @JoinColumn(name = "id_estudio")
    private Estudio estudio;
    @OneToMany
    @JoinColumn(name = "id_filme")
    private List<PersonagemAtor> personagens;
    private String resumo;

    public Filme() {
    }

    public Filme(
                 String nome,
                 Integer anoLancamento,
                 String capaFilme,
                 List<Genero> genero,
                 Diretor diretor,
                 Estudio estudio,
                 String resumo,
                 List<PersonagemAtor> personagens
                 ) {
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.capaFilme = capaFilme;
        this.genero = genero;
        this.diretor = diretor;
        this.estudio = estudio;
        this.resumo = resumo;
        this.personagens = personagens;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public Estudio getEstudio() {
        return estudio;
    }

    public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }

    public List<PersonagemAtor> getPersonagens() {
        return personagens;
    }

    public void setPersonagens(List<PersonagemAtor> personagens) {
        this.personagens = personagens;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filme filme = (Filme) o;
        return Objects.equals(id, filme.id) && Objects.equals(nome, filme.nome) && Objects.equals(anoLancamento, filme.anoLancamento) && Objects.equals(capaFilme, filme.capaFilme) && Objects.equals(genero, filme.genero) && Objects.equals(diretor, filme.diretor) && Objects.equals(estudio, filme.estudio) && Objects.equals(personagens, filme.personagens) && Objects.equals(resumo, filme.resumo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, anoLancamento, capaFilme, genero, diretor, estudio, personagens, resumo);
    }
}
