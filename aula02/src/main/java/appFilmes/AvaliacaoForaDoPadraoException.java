package appFilmes;

public class AvaliacaoForaDoPadraoException extends Exception{
    public AvaliacaoForaDoPadraoException() {
        super("Avaliação errada! Deve ser um valor entre 1 a 5");
    }
}
