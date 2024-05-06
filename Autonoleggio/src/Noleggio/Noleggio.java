package Noleggio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/* Modifiche da fare in Noleggio:
 * in selezionaBatMobile: 1) per mezzo selezionato, non fa nulla??? Non c'è nulla da aggiornare
 *                                       
 */
public class Noleggio {
	private ArrayList<Auto> autoDisponibili;
	private ArrayList<BatMobile> listaBatmobili;

	// costruttore che definisce una nuova array contenente l'oggetto Auto, che deriva dalla classe Auto
	Noleggio() {
		this.autoDisponibili = new ArrayList<>();
		this.listaBatmobili = new ArrayList<>();
	}

	public void aggiungiBatMobile(BatMobile batMobile) throws FileNotFoundException, IOException {
		System.out.println("Aggiunta di Batmobile in corso:");
		listaBatmobili = getBatmobileFile("src/listaBatmobili.txt");
		listaBatmobili.add(batMobile);
		aggiungiBatmobileFile(batMobile);
		System.out.println("Hai aggiunto" + batMobile + "alla lista delle BatMobili.");
	}

	public void rimuoviBatMobile(int IDbatMobile) throws FileNotFoundException, IOException {
		int mezziDis = 0;
		System.out.println("Rimozione Batmezzo in corso:");
		listaBatmobili = getBatmobileFile("src/listaBatmobili.txt");
        if (!listaBatmobili.isEmpty()) {
			for (BatMobile bat : listaBatmobili) {
				if (bat.getIdAuto()==IDbatMobile){
					System.out.println(bat);
					listaBatmobili.remove(bat);
					riscriveBatmobileFile("src/listaBatmobili.txt");
					System.out.println("rimossa auto " + bat.getIdAuto());
					mezziDis ++;
					break;
				} 
			}
			if (mezziDis == 0) {
				System.out.println("Non ci sono mezzi da rimuovere per l'id inserito");
			}
        } else {
        	System.out.println("Non ci sono mezzi da rimuovere"); //Array vuoto
		}
	}

	public void ricercaBatMobile(String nome) throws FileNotFoundException, IOException {
		int mezziDis = 0;
		System.out.println("Ricerca Batmobili in corso:");
		listaBatmobili = getBatmobileFile("src/listaBatmobili.txt");
        if (!listaBatmobili.isEmpty()) {
			if (nome.equals("tutte")) {
				for (BatMobile bat : listaBatmobili) {
					System.out.println(bat);
					mezziDis ++;
				}
			} else {
				for (BatMobile bat : listaBatmobili) {
					if (bat.getNome().equals(nome)) {
						System.out.println(bat);
						mezziDis ++;
					}
				}
			}
			if (mezziDis == 0) {
				System.out.println("Non ci sono auto disponibili per il nome inserito");
			}
        } else {
        	System.out.println("Non ci sono auto disponibili"); //Array vuoto
        }
	}

	public void selezionaBatMobile(int idAuto) throws FileNotFoundException, IOException {
		int mezziDis = 0;
		System.out.println("Selezione di Batmobile per ID in corso:");
		listaBatmobili = getBatmobileFile("src/listaBatmobili.txt");
        if (!listaBatmobili.isEmpty()) {
			for (BatMobile bat : listaBatmobili) {
				if (bat.getIdAuto() == idAuto) {
					System.out.println(bat);
					mezziDis ++;
				}
			}
			if (mezziDis == 0) {
				System.out.println("Non ci sono mezzi disponibili per l'id inserito");
	        }
	    } else {
	    	System.out.println("Non ci sono mezzi disponibili"); //Array vuoto
	    }
	}

	public void aggiungiAuto(Auto auto) throws FileNotFoundException, IOException {
		autoDisponibili = getAutoFile("src/listaAuto.txt");
		autoDisponibili.add(auto);
		aggiungiAutoFile(auto);
		System.out.println("Hai aggiunto" + auto + "alla lista delle auto.");
	}

	public void rimuoviAuto(int idAuto) throws FileNotFoundException, IOException {
		int autoDis = 0;
		System.out.println("Rimozione Auto in corso:");
		autoDisponibili = getAutoFile("src/listaAuto.txt");
        if (!autoDisponibili.isEmpty()) {			
			for (Auto auto : autoDisponibili) {
				if (auto.getIdAuto() == idAuto){
					System.out.println(auto);
					autoDisponibili.remove(auto);
					riscriveAutoFile("src/listaAuto.txt");
					System.out.println("rimossa auto " + auto.getIdAuto());
					autoDis ++;
					break;
				}
			}
			if (autoDis == 0) {
				System.out.println("Non ci sono auto da rimuovere per l'id inserito");
			}
        } else {
        	System.out.println("Non ci sono auto da rimuovere"); //Array vuoto
		}
	}

	public void ricercaAuto(String nome) throws FileNotFoundException, IOException {
		int autoDis = 0;
		System.out.println("Ricerca Auto per nome in corso:");
		autoDisponibili = getAutoFile("src/listaAuto.txt");
        if (!autoDisponibili.isEmpty()) {
			for (Auto auto : autoDisponibili) {
				if (auto.getNome().contains(nome)) {
					System.out.println(auto);
					autoDis ++;
				}
			}
			if (autoDis == 0) {
				System.out.println("Non ci sono auto disponibili per il nome inserito");
			}
        } else {
        	System.out.println("Non ci sono auto disponibili"); //Array vuoto
        }
	}

	public void ricercaAuto(double prezzo) throws FileNotFoundException, IOException {
		int autoDis = 0;
		System.out.println("Ricerca Auto per prezzo in corso:");
		autoDisponibili = getAutoFile("src/listaAuto.txt");
        if (!autoDisponibili.isEmpty()) {
			for (Auto auto : autoDisponibili) {
				if (auto.getPrezzo() <= prezzo) {
					System.out.println(auto);
					autoDis ++;
				}
			}
			if (autoDis == 0) {
				System.out.println("Non ci sono auto disponibili per la cifra inserita");
			}
        } else {
        	System.out.println("Non ci sono auto disponibili"); //Array vuoto
        }
	}

	public void noleggioAuto(int idAuto) throws FileNotFoundException, IOException {
		int autoDis = 0;
		System.out.println("Selezione di Auto per ID in corso:");
		autoDisponibili = getAutoFile("src/listaAuto.txt");
        if (!autoDisponibili.isEmpty()) {
			for (Auto auto : autoDisponibili) {
				if (auto.getIdAuto() == idAuto) {
					auto.setStatoNoleggio(true);
					auto.setDataNoleggio(LocalDate.now());
					riscriveAutoFile("src/listaAuto.txt");
					System.out.println(auto);
					autoDis ++;
				}
			}
			if (autoDis == 0) {
				System.out.println("Non ci sono auto disponibili per l'id inserito");
			}
        } else {
        	System.out.println("Non ci sono auto disponibili"); //Array vuoto
        }
	}
	
	public void riconsegnaAuto(int idAuto) throws FileNotFoundException, IOException {
		int autoDis = 0;
		System.out.println("Selezione di Auto per ID in corso:");
		autoDisponibili = getAutoFile("src/listaAuto.txt");
        if (!autoDisponibili.isEmpty()) {
			for (Auto auto : autoDisponibili) {
				if (auto.getIdAuto() == idAuto) {
					auto.setStatoNoleggio(false);
					auto.setDataNoleggio(null);
					riscriveAutoFile("src/listaAuto.txt");
					System.out.println(auto);
					autoDis ++;
				}
			}
			if (autoDis == 0) {
				System.out.println("Non ci sono auto disponibili per l'id inserito");
			}
        } else {
        	System.out.println("Non ci sono auto disponibili"); //Array vuoto
        }
	}
	public ArrayList<Auto> getAutoFile(String path) throws FileNotFoundException, IOException {
		String filePath = path;
		File file = new File(filePath);
		if (!file.exists())
			file.createNewFile();
		String line;
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			while ((line = reader.readLine()) != null) {
				String[] parti = line.split(",");
				LocalDate localDate;
				if (parti[4].trim().equals("null")) {
					localDate = null;
				} else {
					localDate = LocalDate.parse(parti[4].trim());
				}
				Auto a = new Auto(Integer.parseInt(parti[0].trim()), parti[1].trim(),
						Double.parseDouble(parti[2].trim()), Boolean.parseBoolean(parti[3].trim()), localDate);
				autoDisponibili.add(a);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return autoDisponibili;
	}

	public void aggiungiAutoFile(Auto auto) throws FileNotFoundException, IOException {
		String filePath = "src/listaAuto.txt";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
			writer.write(auto.toStringPrint());
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void riscriveAutoFile(String path){
		//Crea e riempie il file con l'array
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
			for (Auto auto : autoDisponibili){
				writer.write(auto.toStringPrint());
				writer.newLine();
			}
			writer.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<BatMobile> getBatmobileFile(String path) throws FileNotFoundException, IOException {
		String filePath = path;
		File file = new File(filePath);
		if (!file.exists())
			file.createNewFile();
		String line;
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			while ((line = reader.readLine()) != null) {
				String[] parti = line.split(",");
				BatMobile b = new BatMobile(Integer.parseInt(parti[0].trim()), parti[1].trim());
				listaBatmobili.add(b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaBatmobili;
	}
	
	public void aggiungiBatmobileFile(BatMobile batmobile) throws FileNotFoundException, IOException {
		String filePath = "src/listaBatmobili.txt";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
			writer.write(batmobile.toStringPrint());
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void riscriveBatmobileFile(String path){
		//Crea e riempie il file con l'array
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
			for (BatMobile bat : listaBatmobili){
				writer.write(bat.toStringPrint());
				writer.newLine();
			}
			writer.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}
