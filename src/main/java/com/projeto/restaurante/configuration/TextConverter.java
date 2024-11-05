package com.projeto.restaurante.configuration;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TextConverter {

    // Lista de palavras que devem ficar em minúsculas, como "de", "da", etc.
    private static final Set<String> PALAVRAS_LIGACAO = new HashSet<>();

    static {
        PALAVRAS_LIGACAO.add("da");
        PALAVRAS_LIGACAO.add("de");
        PALAVRAS_LIGACAO.add("do");
        PALAVRAS_LIGACAO.add("das");
        PALAVRAS_LIGACAO.add("dos");
        PALAVRAS_LIGACAO.add("e");
        PALAVRAS_LIGACAO.add("em");
        PALAVRAS_LIGACAO.add("com");
        PALAVRAS_LIGACAO.add("um");
        PALAVRAS_LIGACAO.add("uma");
        PALAVRAS_LIGACAO.add("uns");
        PALAVRAS_LIGACAO.add("umas");
        PALAVRAS_LIGACAO.add("o");
        PALAVRAS_LIGACAO.add("a");
    }

    public static String stringConverter(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto; // Retorna texto vazio ou nulo diretamente
        }

        // Divide o texto em palavras
        String[] palavras = texto.split(" ");
        String resultado = "";

        for (int i = 0; i < palavras.length; i++) {
            String palavra = palavras[i].toLowerCase();

            // Verifica se a palavra é uma palavra de ligação e não é a primeira palavra
            if (!PALAVRAS_LIGACAO.contains(palavra) || i == 0) {
                // Capitaliza a primeira letra
                palavra = palavra.substring(0, 1).toUpperCase() + palavra.substring(1);
            }

            // Adiciona a palavra ao resultado, com espaço entre as palavras
            resultado += (i > 0 ? " " : "") + palavra;
        }

        return resultado;
    }

}

