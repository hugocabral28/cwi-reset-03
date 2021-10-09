package appFilmes;

public class Ator {
    private String nome;
    private int idade;
    private int oscarsVencidos;
    private Genero genero;

    public Ator(String nome, int idade, int oscarsVencidos, Genero genero) {
        this.nome = nome;
        this.idade = idade;
        this.oscarsVencidos = oscarsVencidos;
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

    public int getOscarsVencidos() {
        return oscarsVencidos;
    }

    public void setOscarsVencidos(int oscarsVencidos) {
        this.oscarsVencidos = oscarsVencidos;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}
