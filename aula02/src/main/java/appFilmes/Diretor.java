package appFilmes;

public class Diretor {
    private String nome;
    private int idade;
    private int quantidadeFilmes;
    private Genero genero;

    public Diretor(String nome, int idade, int quantidadeFilmes , Genero genero) {
        this.nome = nome;
        this.idade = idade;
        this.quantidadeFilmes = quantidadeFilmes;
        this.genero = genero;
    }
    public void imprimarDados(){
        System.out.println("Nome: "+ getNome());
        System.out.println("Idade: "+ getIdade());
        System.out.println("Gênero: " + genero.getDescricao());
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
