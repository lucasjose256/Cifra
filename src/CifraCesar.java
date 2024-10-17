import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CifraCesar {
CifraCesar(){

    Scanner scanner = new Scanner(System.in);

    System.out.println("Digite o texto a ser cifrado: ");
    String texto = scanner.nextLine();

    System.out.println("Digite a chave de deslocamento: ");
    int chave = scanner.nextInt();

    String textoCifrado = cifrarTexto(texto, chave);
    System.out.println("Texto Cifrado: " + textoCifrado);
    salvarTextoEmArquivo(textoCifrado);
    AnaliseFrequencia.contarFrequencia(textoCifrado);

    String textoDecifrado = decifrarTexto(textoCifrado, chave);
    System.out.println("Texto Decifrado: " + textoDecifrado);

    scanner.close();
}
    public  String cifrarTexto(String texto, int chave) {

        StringBuilder textoCifrado = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char caractere = texto.charAt(i);

            if (Character.isUpperCase(caractere)) {
                char cifrado = (char) (((caractere - 'A' + chave) % 26) + 'A');
                textoCifrado.append(cifrado);
            }
            else if (Character.isLowerCase(caractere)) {
                char cifrado = (char) (((caractere - 'a' + chave) % 26) + 'a');
                textoCifrado.append(cifrado);
            }
            else {
                textoCifrado.append(caractere);
            }
        }
        return textoCifrado.toString();
    }

    public String decifrarTexto(String texto, int chave) {
        // Para decifrar, basta cifrar com a chave negativa
        return cifrarTexto(texto, 26 - (chave % 26));
    }


    public void salvarTextoEmArquivo(String textoCifrado) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("texto_cifrado.txt"))) {
            writer.write(textoCifrado);
            System.out.println("Texto cifrado salvo em 'texto_cifrado.txt'");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar o arquivo: " + e.getMessage());
        }
    }

}
