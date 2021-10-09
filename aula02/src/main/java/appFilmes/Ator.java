package appFilmes;

public class Ator extends Pessoa {

    private int oscarsVencidos;


    public Ator(String nome, int idade, int oscarsVencidos, Genero genero) {
        super(nome = nome,idade = idade, genero = genero);
        this.oscarsVencidos = oscarsVencidos;
    }

    public int getOscarsVencidos() {
        return oscarsVencidos;
    }

    public void setOscarsVencidos(int oscarsVencidos) {
        this.oscarsVencidos = oscarsVencidos;
    }

}
