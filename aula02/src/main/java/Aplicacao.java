public class Aplicacao {
    public static void main(String[] args){
        Diretor diretor1 =new Diretor("Diretor1",39,2);

        Filme filme1 = new Filme("Nome do filme","Terro",190,2019,5,diretor1);
        Filme filme2 = new Filme("Nome do filme2","Aventura",182,2020,4,diretor1);

        filme1.reproduzirFilme();
        System.out.println("______________");
        filme2.reproduzirFilme();
    }
}
