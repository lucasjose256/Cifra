import java.util.Scanner;

public class CifraVernam {

    public static String cifrarDecifrarVernam(String texto, String chave) {
        StringBuilder resultado = new StringBuilder();

        if (chave.length() != texto.length()) {
            throw new IllegalArgumentException("A chave deve ter o mesmo comprimento do texto.");
        }

        for (int i = 0; i < texto.length(); i++) {
            char letraTexto = texto.charAt(i);
            char letraChave = chave.charAt(i);

            char letraCifrada = (char) (letraTexto ^ letraChave);
            resultado.append(letraCifrada);
        }

        return resultado.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o texto aberto: ");
        String textoAberto = scanner.nextLine();

        System.out.print("Digite a chave (deve ter o mesmo comprimento do texto): ");
        String chave = scanner.nextLine();

        String textoCifrado = cifrarDecifrarVernam(textoAberto, chave);
        System.out.println("Texto cifrado: " + textoCifrado);

        String textoDecifrado = cifrarDecifrarVernam(textoCifrado, chave);
        System.out.println("Texto decifrado: " + textoDecifrado);
    }
}
