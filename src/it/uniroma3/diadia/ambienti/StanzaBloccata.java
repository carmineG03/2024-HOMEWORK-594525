package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza {
	private String direzioneBloccata;
	private String attrezzoChiave;

	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoChiave) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoChiave = attrezzoChiave;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if (!super.hasAttrezzo(attrezzoChiave) && direzioneBloccata.equals(direzione))
			return this;
		else
			return super.getStanzaAdiacente(direzione);

	}

	@Override
	public boolean getDescrizione(IO io) {

		io.mostraMessaggio("\nSei nella stanza: " + getNome());
		if (!super.hasAttrezzo(attrezzoChiave)) {
			io.mostraMessaggio("\nUscite: ");
			for (int i = 0; i < super.getNumeroStanzeAdiacenti(); i++) {
				if (super.getDirezione(i) != direzioneBloccata)
					io.mostraMessaggio(super.getDirezione(i));
				else
					io.mostraMessaggio("La direzione " + this.direzioneBloccata + " è bloccata.");
			}
			super.getDescrizioneAttrezzi(io);
			return false;
		} else {
			super.getDescrizione(io);
			super.getDescrizioneAttrezzi(io);
		}
		
		return true;
	}

}