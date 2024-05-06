package Noleggio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 

//LOGIN deve ritornare NULL se non � verificato (utente inesistente) oppure valore numerico 1,2,3 per la tipologia 
public class Login {
	Scanner scan=new Scanner(System.in);
	String filePath = "userPasswordFile.txt";
	public int inserisciUserPsw(String userInput, String pswInput) {
		
		int flag=0;
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
			String line;
			String userRead;
			String passwordRead;
			String typeRead=null;
			
			while( (line =reader.readLine()) != null) {
				userRead="";
				passwordRead=""; 
				typeRead="";

				String[] parti = line.split(",");
				if(parti.length==3) {
					  userRead=parti[0].trim(); 
					  passwordRead=parti[1].trim(); 
					  typeRead=parti[2].trim() ;
				}
				String pswHash = hashPassword(pswInput);
				if (userInput.equals(userRead) && passwordRead.equals(pswHash))
				{	
					if (typeRead.equals("Cliente"))
						flag=1;
					else if (typeRead.equals("Manager"))
						flag=2;
					else if (typeRead.equals("Batman"))
						flag=3;
				}
			}
			if (flag==0)
			{ 
				System.out.println("Username e/o Password inesistenti. 1) Registrarsi o 2) Riprovare");
				if (scan.nextLine().equals("1")) {
					RegistraUtente(userInput, pswInput);
					flag=1;
					return flag;
				} else {
					return 0;
				}
 		}
			else 
			{
				return flag;
			}
		}
		catch (IOException e) {
			System.out.println("Wrong password or user");
			return flag;
		}		
	}
	
	public void RegistraUtente(String userRead, String pswInput) throws FileNotFoundException, IOException {
		String filePath = "userPasswordFile.txt";
		File file = new File(filePath);
		if (!file.exists())
			file.createNewFile();
		String utente;
		String pswHash = hashPassword(pswInput);
		utente = userRead + "," + pswHash + ",Cliente";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
			writer.write(utente);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Utente inserito correttamente");
	}
	
    public static String hashPassword(String password) { 
        try { 
        	// Creiamo una MessageDigest instance per SHA-256 
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //System.out.println("istanza di SHA-256 creata");
            byte[] encodedhash = digest.digest(password.getBytes());
            //System.out.println("password hashata, array di byte generato");
 // Convertiamo l'array di byte in una stringa esadecimale
//	            for(byte b : encodedhash) {
//	                System.out.format("%02x", b);
//	            }
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length); 

		  for (int i = 0; i < encodedhash.length; i++) {
		      String hex = Integer.toHexString(0xff & encodedhash[i]); 
		      //System.out.format("Valore byte: %02x --> Stringa esadecimale: %s\n",encodedhash[i],hex);
		      if(hex.length() == 1) { 
		    	  hexString.append('0'); 
		      }
		      hexString.append(hex); 
		  }
		  //System.out.println("password hashata completa: "+hexString.toString());
		  return hexString.toString(); 
	      } catch (NoSuchAlgorithmException e) { 
	          throw new RuntimeException(e); 
          } 
    }
}

