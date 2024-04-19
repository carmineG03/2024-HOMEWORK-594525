package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoGuarda implements Comando {

	private IO io;

	public ComandoGuarda(IO io) {
		this.io = io;
	}

	@Override
	public void esegui(Partita partita) {

		partita.getStanzaCorrente().getDescrizione(io);
		partita.getStanzaCorrente().getDescrizioneAttrezzi(io);
		partita.getGiocatore().getBorsa().getDescrizione(io);
		io.mostraMessaggio("\n" + partita.getGiocatore().getCfu() + "CFU disponibili.");

	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public String getNome() {
		return "guarda";
	}

}
