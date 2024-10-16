import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnaliseFrequencia {

    // Frequências das letras na língua portuguesa
    static HashMap<Character, Double> frequenciasPortugues = new HashMap<>(Map.ofEntries(
            Map.entry('A', 14.63),
            Map.entry('B', 1.04),
            Map.entry('C', 3.88),
            Map.entry('D', 4.99),
            Map.entry('E', 12.57),
            Map.entry('F', 1.02),
            Map.entry('G', 1.30),
            Map.entry('H', 1.28),
            Map.entry('I', 6.18),
            Map.entry('J', 0.40),
            Map.entry('K', 0.02),
            Map.entry('L', 2.78),
            Map.entry('M', 4.74),
            Map.entry('N', 5.05),
            Map.entry('O', 10.73),
            Map.entry('P', 2.52),
            Map.entry('Q', 1.20),
            Map.entry('R', 6.53),
            Map.entry('S', 7.81),
            Map.entry('T', 4.34),
            Map.entry('U', 4.63),
            Map.entry('V', 1.67),
            Map.entry('W', 0.01),
            Map.entry('X', 0.21),
            Map.entry('Y', 0.01),
            Map.entry('Z', 0.47)
    ));
    // Método para contar a frequência de letras no texto cifrado

    // Método para decifrar o texto com base nas frequências
    public static String decifrarPorFrequencia(String textoCifrado) {
        // Contar a frequência das letras no texto cifrado

        Map<Character, Double> frequenciasCifrado = contarFrequencia(textoCifrado);

        // Ordenar as letras do texto cifrado pela frequência (do mais comum ao menos comum)
        List<Map.Entry<Character, Double>> listaFrequenciasCifrado = new ArrayList<>(frequenciasCifrado.entrySet());
        listaFrequenciasCifrado.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        // Ordenar as letras do português pela frequência (do mais comum ao menos comum)
        List<Map.Entry<Character, Double>> listaFrequenciasPortugues = new ArrayList<>(frequenciasPortugues.entrySet());
        listaFrequenciasPortugues.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        // Criar o mapeamento das letras mais frequentes no texto cifrado para as do português
        Map<Character, Character> mapaSubstituicao = new HashMap<>();
        for (int i = 0; i < Math.min(listaFrequenciasCifrado.size(), listaFrequenciasPortugues.size()); i++) {
            char letraCifrada = listaFrequenciasCifrado.get(i).getKey();
            char letraPortugues = listaFrequenciasPortugues.get(i).getKey();
            mapaSubstituicao.put(letraCifrada, letraPortugues);
        }
        System.out.println(mapaSubstituicao.toString());
        // Usar o mapeamento para decifrar o texto
        StringBuilder textoDecifrado = new StringBuilder();
        for (char c : textoCifrado.toCharArray()) {
            if (Character.isLetter(c)) {
                char letraOriginal = Character.isLowerCase(c)
                        ? Character.toLowerCase(mapaSubstituicao.getOrDefault(Character.toUpperCase(c), c))
                        : mapaSubstituicao.getOrDefault(c, c);
                textoDecifrado.append(letraOriginal);
            } else {
                textoDecifrado.append(c);  // Manter caracteres não-letras inalterados
            }
        }

        return textoDecifrado.toString();
    }
    public static String decifrarCesar(String textoCifrado, int deslocamento) {
        StringBuilder textoDecifrado = new StringBuilder();
        for (char c : textoCifrado.toCharArray()) {
            if (Character.isLetter(c)) {
                char letraBase = Character.isLowerCase(c) ? 'a' : 'A';
                // Deslocar a letra
                char letraDecifrada = (char) ((c - letraBase - deslocamento + 26) % 26 + letraBase);
                textoDecifrado.append(letraDecifrada);
            } else {
                textoDecifrado.append(c);  // Manter caracteres não-letras inalterados
            }
        }
        return textoDecifrado.toString();
    }

    // Método para contar a frequência das letras no texto
    public static Map<Character, Double> contarFrequencia(String texto) {
        Map<Character, Integer> frequencias = new HashMap<>();
        int totalLetras = 0;

        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                char letra = Character.toUpperCase(c);  // Padronizar para maiúsculas
                frequencias.put(letra, frequencias.getOrDefault(letra, 0) + 1);
                totalLetras++;
            }
        }

        // Converter as frequências absolutas em percentuais
        Map<Character, Double> frequenciasPercentuais = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : frequencias.entrySet()) {
            frequenciasPercentuais.put(entry.getKey(), (entry.getValue() * 100.0) / totalLetras);
        }

        return frequenciasPercentuais;
    }

    // Método para calcular a similaridade da frequência com a tabela de português
    public static double calcularSimilaridade(Map<Character, Double> frequenciasDecifradas) {
        double similaridade = 0.0;
        for (Map.Entry<Character, Double> entry : frequenciasPortugues.entrySet()) {
            double freqCifrada = frequenciasDecifradas.getOrDefault(entry.getKey(), 0.0);
            similaridade += Math.abs(freqCifrada - entry.getValue());
        }
        return similaridade;
    }

    public static void main(String[] args) {
        String textoCifrado = "";

        String melhorTexto = "";
        double menorSimilaridade = Double.MAX_VALUE;

        // Testar todas as chaves de 0 a 25
        for (int i = 0; i < 26; i++) {
            String textoDecifrado = decifrarCesar(textoCifrado, i);
            Map<Character, Double> frequenciasDecifradas = contarFrequencia(textoDecifrado);
            double similaridade = calcularSimilaridade(frequenciasDecifradas);

            // Verificar se a similaridade é a menor
            if (similaridade < menorSimilaridade) {
                menorSimilaridade = similaridade;
                melhorTexto = textoDecifrado;
            }
        }

        // Exibir o melhor texto decifrado encontrado
        System.out.println("Melhor Texto Decifrado: " + melhorTexto);
        System.out.println("Similaridade: " + menorSimilaridade);
    }

}
