package br.com.cwi.reset.aula.dois;

public enum TipoContaBancaria {
    POUPANCA("Conta Poupança"),
    CONTA_CORRENTE("Conta Corrente Bradesco"),
    CONTA_SALARIO("Conta Salario Bradesco");

    private String nome;

    TipoContaBancaria(String nome) {
        this.nome = nome;
    }
}
