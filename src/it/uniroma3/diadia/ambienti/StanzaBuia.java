package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IO;

public class StanzaBuia extends Stanza {

	private String attrezzoChiave;

	public StanzaBuia(String nome, String attrezzoChiave) {
		super(nome);
		this.attrezzoChiave = attrezzoChiave;
	}

	public boolean getDescrizione(IO io) {

		
		if(!this.hasAttrezzo(attrezzoChiave)) {
			io.mostraMessaggio("Qui c'é buio pesto...");
			return false;
		}
		else
			super.getDescrizione(io);
			
		return true;
	}
}
