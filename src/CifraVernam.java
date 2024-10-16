import java.util.Scanner;

public class CifraVernam {

    // Método para cifrar ou decifrar um texto usando a cifra de Vernam
    public static String cifrarDecifrarVernam(String texto, String chave) {
        StringBuilder resultado = new StringBuilder();

        // Certifique-se de que o comprimento da chave seja igual ao comprimento do texto
        if (chave.length() != texto.length()) {
            throw new IllegalArgumentException("A chave deve ter o mesmo comprimento do texto.");
        }

        // Cifrar/decifrar usando XOR
        for (int i = 0; i < texto.length(); i++) {
            char letraTexto = texto.charAt(i);
            char letraChave = chave.charAt(i);

            // Aplicar XOR entre a letra do texto e a letra da chave
            char letraCifrada = (char) (letraTexto ^ letraChave);
            resultado.append(letraCifrada);
        }

        return resultado.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrada do texto encontrado pela análise de frequências
        System.out.print("Digite o texto aberto: ");
        String textoAberto = scanner.nextLine();

        // Entrada da chave de Vernam
        System.out.print("Digite a chave (deve ter o mesmo comprimento do texto): ");
        String chave = scanner.nextLine();

        // Cifrar o texto usando Vernam
        String textoCifrado = cifrarDecifrarVernam(textoAberto, chave);
        System.out.println("Texto cifrado: " + textoCifrado);

        // Decifrar o texto cifrado usando a mesma chave
        String textoDecifrado = cifrarDecifrarVernam(textoCifrado, chave);
        System.out.println("Texto decifrado: " + textoDecifrado);
    }
}
