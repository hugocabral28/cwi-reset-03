package appFilmes;

import java.time.LocalDate;


public class Aplicacao {
    public static void main(String[] args) throws Exception {
        Diretor diretor =new Diretor("Diretor",LocalDate.of(1965, 05, 18), 2, Genero.FEMININO);
        Ator ator = new Ator("Ator",LocalDate.of(1985, 10, 28),3,Genero.MASCULINO);

        Filme filme1 = new Filme(
                "Venom",
                "O jornalista Eddie Brock desenvolve força e poder sobre-humanos. quando",
                190,
                2018,
                4.5,
                diretor
        );
        Filme filme2 = new Filme(
                "Infiltrado","Um homem misterioso que trabalha para uma empresa de carros-fortes.",
                182,
                2021,
                4.2,
                diretor
        );

        filme1.reproduzirFilme();
        System.out.println("______________");
        filme2.reproduzirFilme();
        System.out.println("______________");
        diretor.imprimarDados();
        System.out.println("______________");
        ator.imprimarDados();

    }
}
