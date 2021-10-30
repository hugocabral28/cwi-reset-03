package br.com.cwi.reset.projeto1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class Exercicios1Test {
    @Test
    public void testSomarList() {
        // Arrange
        Exercicios1 exercicios1 = new Exercicios1();
        Integer expected = 71;
        List<Integer> numeros = Arrays.asList(10, 15, 20, 11, 15);

        // Action
        Integer result = exercicios1.somarLista(numeros);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testSomarListInteiroENegativo() {
        // Arrange
        Exercicios1 exercicios1 = new Exercicios1();
        Integer expected = 41;
        List<Integer> numeros = Arrays.asList(10, 15, 20, 11, -15);

        // Action
        Integer result = exercicios1.somarLista(numeros);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testSomarListNegativos() {
        // Arrange
        Exercicios1 exercicios1 = new Exercicios1();
        Integer expected = -30;
        List<Integer> numeros = Arrays.asList(-10, -5, -15);

        // Action
        Integer result = exercicios1.somarLista(numeros);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testSomarListZero() {
        // Arrange
        Exercicios1 exercicios1 = new Exercicios1();
        Integer expected = 0;
        List<Integer> numeros = Arrays.asList(0, 0, 0, 0);

        // Action
        Integer result = exercicios1.somarLista(numeros);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testSomarListVazia() {
        // Arrange
        Exercicios1 exercicios1 = new Exercicios1();
        Integer expected = 0;
        List<Integer> numeros = Arrays.asList();

        // Action
        Integer result = exercicios1.somarLista(numeros);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testMediaList() {
        Exercicios1 exercicios1 = new Exercicios1();
        List<Integer> numeros = Arrays.asList(10, 6, 7, 9, 8);
        Double expected = 8.0;

        Double result = exercicios1.calcularMedia(numeros);
        // Assert
        Assertions.assertEquals(expected, result);

    }

    @Test
    public void testInverted() {
        Exercicios1 exercicios1 = new Exercicios1();
        String primeiraPalavra = "Abacate";
        String segundaPalavra = "Banana";
        String terceiraPalavra = "Pessego";
        String quartaPalavra = "Morango";

        String expected1 = "etacabA";
        String expected2 = "ananaB";
        String expected3 = "ogesseP";
        String expected4 = "ognaroM";

        String result1 = exercicios1.obterPalavraInvertida(primeiraPalavra);
        String result2 = exercicios1.obterPalavraInvertida(segundaPalavra);
        String result3 = exercicios1.obterPalavraInvertida(terceiraPalavra);
        String result4 = exercicios1.obterPalavraInvertida(quartaPalavra);

        // Assert
        Assertions.assertEquals(expected1, result1);
        Assertions.assertEquals(expected2, result2);
        Assertions.assertEquals(expected3, result3);
        Assertions.assertEquals(expected4, result4);
    }

}