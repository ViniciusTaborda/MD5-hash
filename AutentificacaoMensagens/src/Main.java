import java.security.MessageDigest;
import java.util.Scanner;

public class Main {
    public static int hashSimples(String palavra) {
        int hash = 0;
        for (int i=0; i<palavra.length(); i++) {
            char letra = palavra.charAt(i);
            int ascii = (int) letra; //convert the first character
            hash += ascii;
            hash = hash % 101;
        }

        return hash;
    }

    // Função hash utilizado pela função String.hashCode
    public static int hashDoJava(String value) {
        int h = 0;
        if (value.length() > 0) {
            String val = value;

            for (int i = 0; i < value.length(); i++) {
                h = 31 * h + val.charAt(i);
            }
        }
        return h;
    }

    // MD5
    public static String md5(String value) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("md5");
        md5.reset();
        byte[] hashMD5 = md5.digest(value.getBytes());
        return bytesToHexadecimalString(hashMD5);
    }

    // MD5 recursivo com 10000 iterações
    public static String md5Porreta(String senha) throws Exception {
        int iteracoes = 10000;
        String hash = md5(senha);
        for (int i=0; i<iteracoes; i++) {
            hash = md5(hash);
        }
        return hash;
    }

    // Converte um array de bytes em uma string hexadecimal
    public static String bytesToHexadecimalString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
    	
    	long t1 = System.currentTimeMillis();

        // Ler uma senha
        System.out.println("Crie uma senha: ");
        Scanner sc = new Scanner(System.in);
        String senha = sc.nextLine();

        // Armazenar essa senha
        String senhaGuardada = md5(senha);
        System.out.println("SENHA GUARDADA: " + senhaGuardada);

        // Validar a senha
        System.out.println("Entrar com a senha: ");
        String senhaDigitada = sc.nextLine();

        if (senhaGuardada.equals(md5(senhaDigitada))) {
            System.out.println("LOGADO!!!");
        } else {
            System.out.println("SENHA INVALIDA!!!");
        }

        char letras[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        
        forDaGalera:
        for (int a=0; a<letras.length; a++) {
            for (int b=0; b<letras.length; b++) {
                for (int c=0; c<letras.length; c++) {
                    for (int d=0; d<letras.length; d++) {
                        String palpiteSenha = letras[a]+""+letras[b]+""+letras[c]+""+letras[d];
                        if (senhaGuardada.equals(md5(palpiteSenha))) {
                            System.out.println("SENHA DESCOBERTA: " + palpiteSenha);
                            long t0 = System.currentTimeMillis();
                            
                            long tempoExec = t0 - t1;
                            
                            System.out.println("Tempo de execu��o do c�digo: " + tempoExec);
                            
                            break forDaGalera; // Faz um break no for da base
                            
                        }
                    }
                }
            }
        }
    }
}