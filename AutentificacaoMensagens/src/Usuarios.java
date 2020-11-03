import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


public class Usuarios {

	public static String local;
	
	
	public static String transformaEmHash(String texto) throws NoSuchAlgorithmException {
			
			String plaintext = texto;
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(plaintext.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			String hashtext = bigInt.toString(16);
			
			while(hashtext.length() < 32 ){
			  hashtext = "0"+hashtext;
			}
			return hashtext;
		}
	
	public static void CriaArquivo(String conteudo, String nomeArquivo) {
		try {
			Writer bw;
			bw = new BufferedWriter(new OutputStreamWriter(
				    new FileOutputStream(nomeArquivo), "UTF-8"));
			bw.write(conteudo);
			bw.close(); 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void escreveArquivo(String conteudo, String nomeArquivo)
	{
		try {
			Writer bw;
			bw = new BufferedWriter(new FileWriter(nomeArquivo, true));
			bw.append(conteudo);
			bw.close(); 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void criarUser() throws NoSuchAlgorithmException {
		
		System.out.println("Atenção! Nomes de usuario e senha devem possuir 4 caracteres.");
		System.out.println();
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Digite o local em que o arquivo deve ser criado: ");
		String local = scan.nextLine();
		
		Scanner scan1 = new Scanner(System.in);
		System.out.println("Digite seu nome: ");
		String nome1 = scan.nextLine();
		
		Scanner scan2 = new Scanner(System.in);
		System.out.println("Digite sua senha: ");
		String senha1 = scan.nextLine();
		
		
		
		String senhaHash1 = transformaEmHash(senha1);
		
		
		
		CriaArquivo(nome1 + "|" + senhaHash1 + "\n" , local);
		
		for (int i = 0; i < 4; i++) {
						
			Scanner scan3 = new Scanner(System.in);
			System.out.println("Digite seu nome: ");
			String nome2 = scan.nextLine();
			
			Scanner scan4 = new Scanner(System.in);
			System.out.println("Digite sua senha: ");
			String senha2 = scan.nextLine();
			
			String senhaHash2 = transformaEmHash(senha2);

			
			escreveArquivo(nome2 + "|" + senhaHash2 + "\n" , local);
			
			
		}
		
		System.out.println("Os usuarios foram cadastrados em: " + local);
		
		
		
	}
	
	public static void lerArquivo() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Master\\Desktop\\Vino\\usuarios.txt"), "UTF-8"));
		String line;
		while ((line = br.readLine()) != null) 
		{
			System.out.println(line);
		}
		br.close();
	}
	
	
	
	
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

		
		
		
		
		
	}

}
