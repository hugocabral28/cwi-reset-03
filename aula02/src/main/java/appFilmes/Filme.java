package appFilmes;

public class Filme {
    private String nome;
    private String descricao;
    private Integer duracao;
    private int anoLancamento;
    private double avaliacao;
    private Diretor diretor;

    public Filme(String nome, String descricao, Integer duracaoMinuto, int anoLancamento, double avaliacao, Diretor diretor) throws AvaliacaoForaDoPadraoException {
        if (avaliacao < 1 || avaliacao > 5) {
            throw new AvaliacaoForaDoPadraoException();
        }
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracaoMinuto;
        this.anoLancamento = anoLancamento;
        this.avaliacao = avaliacao;
        this.diretor = diretor;

    }

    public void reproduzirFilme(){
        System.out.println("Nome do Filme: " + getNome());
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Duração em Minutos: " + getDuracao());
        System.out.println("Nome do Diretor: " + diretor.getNome());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }
}








