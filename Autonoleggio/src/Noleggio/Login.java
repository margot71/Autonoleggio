package Noleggio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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
				if (userInput.equals(userRead) && passwordRead.equals(pswInput))
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
		utente = userRead + "," + pswInput + ",Cliente";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
			writer.write(utente);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Utente inserito correttamente");
	}
}
