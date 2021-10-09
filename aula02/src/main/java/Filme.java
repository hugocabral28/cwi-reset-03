public class Filme {
    private String nome;
    private String descricao;
    private double duracaoMinuto;
    private int anoLancamento;
    private int avaliacao;
    private int idDiretor;

    public Filme(String nome, String descricao, double duracaoMinuto, int anoLancamento, int avaliacao, int idDiretor) {
        this.nome = nome;
        this.descricao = descricao;
        this.duracaoMinuto = duracaoMinuto;
        this.anoLancamento = anoLancamento;
        this.avaliacao = avaliacao;
        this.idDiretor = idDiretor;
    }
    public void reproduzirFilme(){
        System.out.println("Nome do Filme: " + getNome());
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Duração em Minutos: " + getDuracaoMinuto());
        System.out.println("Nome do Diretor: " + getIdDiretor());
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

    public double getDuracaoMinuto() {
        return duracaoMinuto;
    }

    public void setDuracaoMinuto(double duracaoMinuto) {
        this.duracaoMinuto = duracaoMinuto;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getIdDiretor() {
        return idDiretor;
    }

    public void setIdDiretor(int idDiretor) {
        this.idDiretor = idDiretor;
    }
}








