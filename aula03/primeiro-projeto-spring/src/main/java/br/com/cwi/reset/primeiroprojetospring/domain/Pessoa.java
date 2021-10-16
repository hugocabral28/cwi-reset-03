package br.com.cwi.reset.primeiroprojetospring.domain;

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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}