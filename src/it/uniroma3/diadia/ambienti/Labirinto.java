package it.uniroma3.diadia.ambienti;

public class Labirinto{
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;


    
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}
	
    public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
	public void setStanzaIniziale(Stanza stanzaIniziale) {
	    this.stanzaIniziale = stanzaIniziale;
	}
	
}
