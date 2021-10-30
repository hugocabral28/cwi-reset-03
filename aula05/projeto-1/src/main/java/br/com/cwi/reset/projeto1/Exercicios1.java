package br.com.cwi.reset.projeto1;

import java.util.ArrayList;
import java.util.List;

public class Exercicios1 {

    public Integer somarLista(List<Integer> numeros) {
        int soma = 0;
        for(int i = 0; i < numeros.size(); i++) {
            soma += numeros.get(i);
        }
        return soma;
    }

    public Double calcularMedia(List<Integer> numeros) {

        double media = (somarLista(numeros)/numeros.size());
        return media;
    }

    public Integer obterMaiorNumero(List<Integer> numeros) {
        int maiorNumero = 0;
        for(int i = 0; i < numeros.size(); i++) {
            if(numeros.get(i) > maiorNumero) {
                maiorNumero = numeros.get(i);
            }
        }
        return maiorNumero;
    }

    public String obterPalavraInvertida(String palavra) {
        String palavraInvertida = "";
        for(int i = palavra.length()-1; i >= 0; i--){
            palavraInvertida += palavra.charAt(i);
        }
        return palavraInvertida;
    }

    public List<Integer> ordenarLista(List<Integer> numeros) {
        List<Integer> listaOrdenada = new ArrayList<>();
        int aux = 0;
        for(Integer numero : numeros){
            listaOrdenada.add(numero);
        }
        for(int i = 0; i < listaOrdenada.size(); i++){
            for(int j = i+1; j < listaOrdenada.size(); j++){
                if(listaOrdenada.get(j) < listaOrdenada.get(i)){
                   aux = listaOrdenada.get(j);

                   listaOrdenada.set(j, listaOrdenada.get(i));
                   listaOrdenada.set(i,aux);
                }
            }
        }
        return listaOrdenada;
    }
}

