package br.com.cwi.reset.aula.dois;

public class ContaBancaria {

    private String cpfProprietario;

    private String numeroConta;

    private String agencia;

    private String banco;

    private Double saldo;

    private TipoContaBancaria tipoConta;

    private String chavePix;


    public ContaBancaria(String cpfProprietario, String numeroConta, String agencia, String banco, Double saldo, TipoContaBancaria tipoConta) {
        this.cpfProprietario = cpfProprietario;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.banco = banco;
        this.saldo = saldo;
        this.tipoConta = tipoConta;
    }


    public void sacarDinheiro(double valor) {
        saldo = saldo - valor;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }
}
