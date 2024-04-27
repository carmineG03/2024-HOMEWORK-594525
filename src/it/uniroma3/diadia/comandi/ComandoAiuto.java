package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {

	private IO io;
	static final private String[] elencoComandi = { "vai", "fine", "prendi", "posa", "guarda" };

	public ComandoAiuto(IO io) {
		this.io = io;
	}

	@Override
	public void esegui(Partita partita) {
		/**
		 * Stampa informazioni di aiuto.
		 */
		io.mostraMessaggio("Scrivi uno dei seguenti comandi: ");
		for (int i = 0; i < elencoComandi.length; i++)
			io.mostraMessaggio(elencoComandi[i] + " ");
		io.mostraMessaggio("");

	}

	@Override
	public void setParametro(String parametro) {

	}


	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public String getNome() {
		return "aiuto";
	}
}
