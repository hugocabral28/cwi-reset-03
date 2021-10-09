public class Diretor {
    private String nome;
    private int idade;
    private int quantidadeFilmes;

    public Diretor(String nome, int idade, int quantidadeFilmes) {
        this.nome = nome;
        this.idade = idade;
        this.quantidadeFilmes = quantidadeFilmes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getQuantidadeFilmes() {
        return quantidadeFilmes;
    }

    public void setQuantidadeFilmes(int quantidadeFilmes) {
        this.quantidadeFilmes = quantidadeFilmes;
    }
}
