package br.com.cwi.reset.projeto1.exception;

public class PetNaoExistenteException extends Exception{
    public PetNaoExistenteException(String nome) {
        super(String.format("Filme com o nome %s não existe",nome));
    }
}
