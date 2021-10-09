public class Diretor {
    private int idDiretor;
    private String nome;
    private int idade;
    private int quantidadeFilmes;

    public Diretor(int idDiretor, String nome, int idade, int quantidadeFilmes) {
        this.idDiretor = idDiretor;
        this.nome = nome;
        this.idade = idade;
        this.quantidadeFilmes = quantidadeFilmes;
    }

    public int getIdDiretor() {
        return idDiretor;
    }

    public void setIdDiretor(int idDiretor) {
        this.idDiretor = idDiretor;
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
