package appFilmes;

public class Aplicacao {
    public static void main(String[] args){
        Diretor diretor1 =new Diretor("Diretor1",39,2,Genero.FEMININO);
        Ator ator = new Ator("appFilmes.Ator 1",32,3,Genero.MASCULINO);

        Filme filme1 = new Filme("Nome do filme","Terro",190,2019,5,diretor1);
        Filme filme2 = new Filme("Nome do filme2","Aventura",182,2020,4,diretor1);

        filme1.reproduzirFilme();
        System.out.println("______________");
        filme2.reproduzirFilme();
        System.out.println("______________");
        diretor1.imprimarDados();
        ator.imprimarDados();

    }
}
