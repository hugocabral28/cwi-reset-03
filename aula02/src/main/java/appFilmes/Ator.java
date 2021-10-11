package appFilmes;

import java.time.LocalDate;

public class Ator extends Pessoa {

    private int oscarsVencidos;


    public Ator(String nome, LocalDate dataNascimento, int oscarsVencidos, Genero genero) {
        super(nome,dataNascimento,genero);
        this.oscarsVencidos = oscarsVencidos;
    }


    public int getOscarsVencidos() {
        return oscarsVencidos;
    }

    public void setOscarsVencidos(int oscarsVencidos) {
        this.oscarsVencidos = oscarsVencidos;
    }

}
