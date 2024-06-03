package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {

	private static final String nonPuoiAndarci = "Non puoi andare qui, è bloccato!"; //Riguardo la stanza bloccata
	
	@Override
	public void esegui (Partita partita, IO io) {
	    Stanza stanzaCorrente = partita.getStanzaCorrente();
	    Stanza prossimaStanza = null;
	    String parametro = this.getParametro();

	    if (parametro == null) {
	        io.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
	        return;
	    }

	    try {
	        Direzione direzione = Direzione.valueOf(parametro);
	        prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
	    } catch (IllegalArgumentException e) {
	        io.mostraMessaggio("Direzione inesistente");
	        return;
	    }

	    if(prossimaStanza != null && stanzaCorrente.equals(prossimaStanza))
	        io.mostraMessaggio(nonPuoiAndarci);

	    if (prossimaStanza == null) {
	        io.mostraMessaggio("Direzione inesistente");
	        return;
	    }

	    partita.setStanzaCorrente(prossimaStanza);
	    io.mostraMessaggio(partita.getStanzaCorrente().getNome());
	    partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
	}

	
	public String getNonpuoiandarci() {
		return nonPuoiAndarci;
	}
}