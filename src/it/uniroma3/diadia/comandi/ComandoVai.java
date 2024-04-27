package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {
	private String direzione;
	private IO io;

	public ComandoVai(IO io, String direzione) {
		this.direzione = direzione;
		this.io = io;
	}

	@Override
	public void esegui(Partita partita) {

		

		if (this.direzione == null) {
			this.io.mostraMessaggio("Dove vuoi andare?");
			this.direzione = io.leggiRiga();
		}

		if (partita.getStanzaCorrente().getStanzaAdiacente(this.direzione) != null)
			partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzaAdiacente(this.direzione));
		else
			this.io.mostraMessaggio("Direzione inesistente!");

	

		if (partita.getStanzaCorrente() != null)
			io.mostraMessaggio(partita.getStanzaCorrente().getNome());

		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);

	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

	@Override
	public String getParametro() {
		return this.direzione;
	}

	@Override
	public String getNome() {
		return "vai";
	}
}