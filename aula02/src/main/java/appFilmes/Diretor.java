package appFilmes;

public class Diretor extends Pessoa{

    private int quantidadeFilmes;


    public Diretor(String nome, int idade, int quantidadeFilmes , Genero genero) {
        super(nome = nome,idade = idade, genero = genero);
        this.quantidadeFilmes = quantidadeFilmes;
    }

    public int getQuantidadeFilmes() {
        return quantidadeFilmes;
    }

    public void setQuantidadeFilmes(int quantidadeFilmes) {
        this.quantidadeFilmes = quantidadeFilmes;
    }
}
