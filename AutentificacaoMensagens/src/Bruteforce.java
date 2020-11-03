/*Program coded by The Alchemist
* This is a MD5 bruteforce script
* DEQUE HELPED ME WITH THE BRUTEFORCING CODE
* Works upto words that are as long as the user desires it to be
* Supports numbers,characters(both uppercase and lowercase)
* and special characters normally used in passwords
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import java.util.*;
import java.security.*;
public class Bruteforce
{  
    static String answer="";
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException
    {
    	
    	criarUser();
    	
    	long t0 = System.currentTimeMillis();
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Master\\Desktop\\Vino\\usuarios.txt"), "UTF-8"));
		String line;
		
		while ((line = br.readLine()) != null) 
		{
			System.out.println("Analisando esta linha: " + line);
			System.out.println();

			
			String[] vetor = line.split(";");
			
			String user = vetor[0];
			
			String senha = vetor[1];

			
			 Scanner in=new Scanner(System.in);
		        char ar[]={ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
		                's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
		                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3',
		                '4', '5', '6', '7', '8', '9','`','~','!','@','#','$','%','^','&','*','(',')','-','_','=','+',
		                '|','{','}','[',']',';',':',',','<','.','>','/','?'};
		        
		        String enc;
		        System.out.println("Por favor somente insira códigos hash.");
		        enc = senha;
		        
		        
		        //HERE, 20 denotes the maximum wordlength 20
		        final int MAX_WORDLENGTH = 20;//YOU JUST NEED TO CHANGE THIS TO MODIFY THE MAXIMUM WORDLENGTH
		        for(int wordlength = 1; wordlength <= MAX_WORDLENGTH; wordlength++)
		        {
		            if(generate(wordlength,ar, enc))
		            {
		                System.out.println("Resposta encontrada! A senha do user " +  user + " é " + answer);
		                break;
		            }
		            else
		            {
		                System.out.println("Não é uma palavra de "+wordlength+" caracteres");
		            }
		        }
			
			
		}
		br.close();
    	
		long t1 = System.currentTimeMillis();
		
		System.out.println(t1);
		System.out.println(t0);
		
		long tempoExec = t1 - t0;
		
		System.out.println("Tempo de execução do código com 4 usuários: " + tempoExec );
    	
    	
    }
    

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
		
		System.out.println("Digite seu nome: ");
		String nome1 = scan.nextLine();
		
		System.out.println("Digite sua senha: ");
		String senha1 = scan.nextLine();
		
		
		String senhaHash1 = transformaEmHash(senha1);
		
		
		CriaArquivo(nome1 + ";" + senhaHash1 + "\n" , "C:\\\\Users\\\\Master\\\\Desktop\\\\Vino\\\\usuarios.txt");
		
		for (int i = 0; i < 4; i++) {
						
			System.out.println("Digite seu nome: ");
			String nome2 = scan.nextLine();
			
			System.out.println("Digite sua senha: ");
			String senha2 = scan.nextLine();
			
			String senhaHash2 = transformaEmHash(senha2);

			
			escreveArquivo(nome2 + ";" + senhaHash2 + "\n" , "C:\\Users\\Master\\Desktop\\Vino\\usuarios.txt");
			
			
		}
		
		System.out.println("Os usuarios foram cadastrados em: " + "C:\\Users\\Master\\Desktop\\Vino\\usuarios.txt");
		System.out.println();
		
		
		
	}
	
	public static void lerArquivo() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(local), "UTF-8"));
		String line;
		while ((line = br.readLine()) != null) 
		{
			System.out.println(line);
		}
		br.close();
	}
	
	
	
	
	
	
    
    private static boolean generate(int wordlength, char[] alphabet,String enc)
    {
        final long MAX_WORDS = (long) Math.pow(alphabet.length, wordlength);
        final int RADIX = alphabet.length;
        for (long i = 0; i < MAX_WORDS; i++)
        {
            int[] indices = convertToRadix(RADIX, i, wordlength);
            char[] word = new char[wordlength];
            for (int k = 0; k < wordlength; k++)
            {
                word[k] = alphabet[indices[k]];
            }
            String ss=new String(word);
            if(compareit(encrypt(ss),enc))
            {
                answer=ss;
                return true;
            }
        }
        return false;
    }
    private static int[] convertToRadix(int radix, long number, int wordlength)
    {
        int[] indices = new int[wordlength];
        for (int i = wordlength - 1; i >= 0; i--)
        {
            if (number > 0)
            {
                int rest = (int) (number % radix);
                number /= radix;
                indices[i] = rest;
            }
            else
            {
                indices[i] = 0;
            }

        }
        return indices;
    }
    public static String encrypt(String str)
    {
            byte[] defaultBytes = str.getBytes();
            try
            {
                MessageDigest algorithm = MessageDigest.getInstance("MD5");
                algorithm.reset();
                algorithm.update(defaultBytes);
                byte messageDigest[] = algorithm.digest();
                StringBuffer hexString = new StringBuffer();
                for (int i = 0; i < messageDigest.length; i++)
                {
                    hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
                }
                str = hexString + "";
            } catch(NoSuchAlgorithmException e)
              {
                  e.printStackTrace();
              }
            return str;
    }
    public static boolean compareit(String s2, String s1)
    {
        String a=s1;
        if(s1.contains(s2))
            return true;
        else
        {
            /*Java often misses out some zeroes while encrypting text, so here
             * I'm removing zeroes one by one from the original string and then
             * performing the check again*/
            while(a.indexOf('0')!=-1)
            {
                a=a.substring(0,a.indexOf('0'))+a.substring(a.indexOf('0')+1,a.length());
                if(a.contains(s2))
                    return true;
            }
        }
        return false;
    }
}
