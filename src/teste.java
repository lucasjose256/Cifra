import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class teste {

    // Frequências típicas da língua portuguesa (exemplo simplificado)
    static Map<Character, Double> frequenciasPortugues = new HashMap<>() {{
        put('A', 14.63);
        put('B', 1.54);
        put('C', 3.88);
        put('D', 4.99);
        put('E', 12.57);
        put('F', 1.02);
        put('G', 1.30);
        put('H', 1.28);
        put('I', 6.18);
        put('J', 0.40);
        put('K', 0.02);
        put('L', 2.78);
        put('M', 4.74);
        put('N', 5.05);
        put('O', 10.73);
        put('P', 2.52);
        put('Q', 1.20);
        put('R', 6.53);
        put('S', 7.81);
        put('T', 5.05);
        put('U', 4.63);
        put('V', 1.67);
        put('W', 0.01);
        put('X', 0.21);
        put('Y', 0.02);
        put('Z', 0.47);
    }};

    public static void testeAnalisarFrequencia(String textoCifrado) {
        textoCifrado = textoCifrado.toUpperCase();
        Map<Character, Integer> frequencia = new HashMap<>();

        // Remover espaços, vírgulas, pontos, quebras de linha e normalizar para maiúsculas
        String texto = textoCifrado.replaceAll("[\\s,.]", "").toUpperCase();

        // Contar a frequência de cada caractere
        for (char c : texto.toCharArray()) {
            frequencia.put(c, frequencia.getOrDefault(c, 0) + 1);
        }

        // Total de caracteres para calcular a frequência relativa
        int totalCaracteres = texto.length();
        Map<Character, Character> mapaSubstituicao = new HashMap<>();
        Set<Character> letrasUsadas = new HashSet<>();

        // Mapear as frequências do texto para as letras da língua portuguesa
        for (Map.Entry<Character, Integer> entry : frequencia.entrySet()) {
            char caractereTexto = entry.getKey();
            double frequenciaRelativaTexto = (entry.getValue() * 100.0) / totalCaracteres;

            char letraCorrespondente = ' ';
            double menorDiferenca = Double.MAX_VALUE;

            // Encontrar a letra correspondente
            for (Map.Entry<Character, Double> freqPortugues : frequenciasPortugues.entrySet()) {
                double diferenca = Math.abs(frequenciaRelativaTexto - freqPortugues.getValue());

                // Verifica se a letra já foi mapeada
                if (letrasUsadas.contains(freqPortugues.getKey())) {
                    continue; // Pula se a letra já foi usada
                }

                if (diferenca < menorDiferenca) {
                    menorDiferenca = diferenca;
                    letraCorrespondente = freqPortugues.getKey();
                }
            }

            // Adiciona a letra correspondente ao mapa de substituição
            if (letraCorrespondente != ' ') {
                mapaSubstituicao.put(caractereTexto, letraCorrespondente);
                letrasUsadas.add(letraCorrespondente); // Marcar a letra como utilizada
            }

            System.out.printf("Caractere do texto: %c - Frequência: %.2f%% -> Letra mais próxima do português: %c (%.2f%%)%n",
                    caractereTexto, frequenciaRelativaTexto, letraCorrespondente, frequenciasPortugues.get(letraCorrespondente));
        }

        // Construir a mensagem decifrada
        StringBuilder mensagemDecifrada = new StringBuilder();
        for (char caractere : textoCifrado.toCharArray()) {
            if (mapaSubstituicao.containsKey(caractere)) {
                mensagemDecifrada.append(mapaSubstituicao.get(caractere));
            } else {
                mensagemDecifrada.append(caractere); // Se o caractere não está no mapa, adicioná-lo como está
            }
        }

        System.out.println(mensagemDecifrada.toString());
    }

    public static void main(String[] args) {
        String textoCifrado = "g5Bt5 t54yvtz3v4A5 wrG t53 7Bv r9 6v995r9 9v 9z4Ar3\n" +
                "58xB2y59r9. dBzA5 t54yvtz3v4A5, 7Bv 9v 9z4Ar3\n" +
                "yB3z2uv9. Vy r99z3 7Bv r9 v96zxr9 9v3 x8r59 v8xBv3\n" +
                "uv9uv4y59r3v4Av r trsvtr 6r8r 5 tvB, v47Br4A5 r9\n" +
                "tyvzr9 r9 srzEr3 6r8r r Av88r, 9Br 3rv.\n" +
                "cv54r8u5 Ur mz4tz.";
        testeAnalisarFrequencia(textoCifrado);
    }
}
