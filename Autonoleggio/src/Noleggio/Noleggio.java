package Noleggio;

import java.time.LocalDate;
import java.util.ArrayList;

public class Noleggio {
	private ArrayList<Auto> autoDisponibili;
	private ArrayList<BatMobile> listaBatmobili;

	// costruttore che definisce una nuova array contenente l'oggetto Auto, che
	// deriva dalla classe Auto
	Noleggio() {
		this.autoDisponibili = new ArrayList<>();
		this.listaBatmobili = new ArrayList<>();
	}

	public void aggiungiBatMobile(BatMobile batMobile) {
		System.out.println("Aggiunta di Batmobile in corso:");
		listaBatmobili.add(batMobile);
		System.out.println(batMobile);
	}

	public void rimuoviBatMobile(BatMobile batMobile) {
		listaBatmobili.remove(batMobile);
	}

	public void ricercaBatMobile(String nome) {
		System.out.println("Ricerca Batmobili in corso:");
		if (nome.equals("tutte")) {
			for (BatMobile bat : listaBatmobili) {
				System.out.println(bat);
			}
		} else {
			for (BatMobile bat : listaBatmobili) {
				if (bat.getNome().equals(nome)) {
					System.out.println(bat);
				}
			}
		}
	}

	public void selezionaBatMobile(int idAuto) {
		System.out.println("Selezione di Batmobile per ID in corso:");
		for (BatMobile bat : listaBatmobili) {
			if (bat.getIdAuto() == idAuto) {
				System.out.println(bat);
			}
		}

	}

	public void aggiungiAuto(Auto auto) {
		autoDisponibili.add(auto);
	}

	public void rimuoviAuto(Auto auto) {
		autoDisponibili.remove(auto);
	}

	public void ricercaAuto(String nome) {
		System.out.println("Ricerca Auto per nome in corso:");
		for (Auto auto : autoDisponibili) {
			if (auto.getNome().equals(nome)) {
				System.out.println(auto);
			}
		}
	}

	public void ricercaAuto(double prezzo) {
		System.out.println("Ricerca Auto per prezzo in corso:");
		for (Auto auto : autoDisponibili) {
			if (auto.getPrezzo() <= prezzo) {
				System.out.println(auto);
			}
		}
	}

	public void selezioneAuto(int idAuto) {
		System.out.println("Selezione di Auto per ID in corso:");
		for (Auto auto : autoDisponibili) {
			if (auto.getIdAuto() == idAuto) {
				auto.setStatoNoleggio(true);
				auto.setDataNoleggio(LocalDate.now());
				System.out.println(auto);
			}
		}
	}
}
