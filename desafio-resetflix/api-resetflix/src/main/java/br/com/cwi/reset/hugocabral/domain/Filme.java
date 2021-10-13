package br.com.cwi.reset.hugocabral.domain;

import java.util.List;

public class Filme {
    private Integer id;
    private String nome;
    private Integer anoLancamento;
    private List<Genero> capaFilme;
    private Diretor diretor;
    private List<PersonagemAtor> personagens;
    private String resumo;
}
