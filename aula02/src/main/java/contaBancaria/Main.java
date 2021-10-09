package br.com.cwi.reset.aula.dois;

public class Main {

    public static void main(String[] args) {
        ContaBancaria contaBancaria = new ContaBancaria("00000000000", "0001-0",
            "0001", "Banco do Brasil", 500.0, TipoContaBancaria.CONTA_CORRENTE);

        contaBancaria.setChavePix("leonardo@cwi.com");

        contaBancaria.sacarDinheiro(200);
        contaBancaria.depositar(100);



        System.out.println("Saldo: " +  contaBancaria.getSaldo());
    }
}


