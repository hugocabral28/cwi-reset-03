package br.com.cwi.reset.primeiroprojetospring.domain;

import java.time.LocalDate;

public class Diretor extends Pessoa{

    private int quantidadeFilmes;


    public Diretor(String nome, LocalDate dataNascimento, int quantidadeFilmes, Genero genero) {
        super(nome, dataNascimento, genero);
        this.quantidadeFilmes = quantidadeFilmes;
    }

    public int getQuantidadeFilmes() {
        return quantidadeFilmes;
    }

    public void setQuantidadeFilmes(int quantidadeFilmes) {
        this.quantidadeFilmes = quantidadeFilmes;
    }
}