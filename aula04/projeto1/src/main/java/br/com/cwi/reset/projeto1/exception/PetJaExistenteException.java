package br.com.cwi.reset.projeto1.exception;

public class PetJaExistenteException extends Exception{
    public PetJaExistenteException(String nome) {
        super(String.format("Pet com o nome %s já existe",nome));
    }
}
