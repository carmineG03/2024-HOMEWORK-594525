package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {
	
	private IO io;
	private String nomeAttrezzo;
	
	public ComandoPrendi(IO io, String direzione) {
		this.io = io;
		this.nomeAttrezzo = direzione;
	}

	/**
	 * esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita) {
			
		if(this.nomeAttrezzo == null) {
			io.mostraMessaggio("Che attrezzo vuoi prendere dalla stanza?");
			this.nomeAttrezzo = this.io.leggiRiga();
		}
			
		if(partita.getStanzaCorrente().hasAttrezzo(this.nomeAttrezzo)) {
			if(partita.getGiocatore().getBorsa().getPeso() + partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo).getPeso() <= partita.getGiocatore().getBorsa().getPesoMax()) {
				Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(this.nomeAttrezzo);
			
			
				partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere);
				partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
				
				io.mostraMessaggio(this.nomeAttrezzo + " preso!");
			}else
				io.mostraMessaggio("Impossibile prendere l'attrezzo, è troppo pesante!");
		}else
			io.mostraMessaggio("L'attrezzo non è presente nella stanza");		
	}

	

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public String getNome() {
		return "prendi";
	}


}
