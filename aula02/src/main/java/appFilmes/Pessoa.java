package appFilmes;

import java.time.LocalDate;
import java.time.Period;

public abstract class Pessoa {
    private String nome;
    private LocalDate dataNascimento;
    private Genero genero;

    public Pessoa(String nome, LocalDate dataNascimento , Genero genero) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
    }

    public void imprimarDados(){
        System.out.println("Nome: "+ getNome());
        System.out.println("Idade: "+ this.calcularIdade());
        System.out.println("Gênero: " + genero.getDescricao());
    }

    public int calcularIdade(){
        return Period.between(dataNascimento,LocalDate.now()).getYears();
    }

    public String getNome() {
        return nome;
    }
}
