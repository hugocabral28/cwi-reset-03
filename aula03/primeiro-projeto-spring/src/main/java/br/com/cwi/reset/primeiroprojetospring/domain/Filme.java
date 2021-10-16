package br.com.cwi.reset.primeiroprojetospring.domain;

public class Filme {
    private String nome;
    private String descricao;
    private Integer duracao;
    private int anoLancamento;
    private double avaliacao;
    private Diretor diretor;

    public Filme(String nome, String descricao, Integer duracaoMinuto, int anoLancamento, double avaliacao, Diretor diretor){

        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracaoMinuto;
        this.anoLancamento = anoLancamento;
        this.avaliacao = avaliacao;
        this.diretor = diretor;

    }
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getDuracao() {
        return duracao;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public Diretor getDiretor(){
        return diretor;
    }


}