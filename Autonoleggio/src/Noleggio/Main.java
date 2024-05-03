package Noleggio;

public class Main {
 
	public static void main(String[] args) {

		Noleggio noleggio = new Noleggio();
		//nel parametro bisogna aggiungere new, altrimenti sovrascrive ogni volta
		noleggio.aggiungiAuto(new Auto(001, "Fiat Punto", 40.99, false, null));
		noleggio.aggiungiAuto(new Auto(002, "Ford Fiesta", 45.29, false, null));
		noleggio.aggiungiAuto(new Auto(003, "AlfaRomeo Giulietta", 70.99, false, null));
		noleggio.ricercaAuto(52);
		noleggio.selezioneAuto(1);
		noleggio.aggiungiBatMobile( new BatMobile(007, "Batmobile num1"));
		noleggio.aggiungiBatMobile( new BatMobile(1, "Batmobile num2"));
		noleggio.aggiungiBatMobile( new BatMobile(2, "Batmoto"));
		noleggio.ricercaBatMobile("tutte");
		noleggio.selezionaBatMobile(7);
	}
}

