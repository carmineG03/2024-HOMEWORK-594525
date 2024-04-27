package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	
	private Stanza stanzaIniziale;
	private Stanza stanzaFinale;
	private Stanza stanzaCorrente;
	
	
	public Labirinto() {
		creaStanze();
	}
	
	public void creaStanze() {

			/* crea gli attrezzi */
	    	Attrezzo lanterna = new Attrezzo("lanterna",3);
			Attrezzo osso = new Attrezzo("osso",1);
	    	
			/* crea stanze del labirinto */
			Stanza atrio = new StanzaMagica("Atrio");
			Stanza aulaN11 = new StanzaBloccata("Aula N11","est","osso");
			Stanza aulaN10 = new Stanza("Aula N10");
			Stanza laboratorio = new StanzaBuia("Laboratorio Campus","lanterna");
			Stanza biblioteca = new Stanza("Biblioteca");
			
			/* collega le stanze */
			atrio.impostaStanzaAdiacente("nord", biblioteca);
			atrio.impostaStanzaAdiacente("ovest", aulaN11);
			atrio.impostaStanzaAdiacente("sud", aulaN10);
			atrio.impostaStanzaAdiacente("est", laboratorio);
			aulaN11.impostaStanzaAdiacente("nord", laboratorio);
			aulaN11.impostaStanzaAdiacente("est", atrio);
			aulaN11.impostaStanzaAdiacente("sud", aulaN10);
			aulaN10.impostaStanzaAdiacente("nord", atrio);
			aulaN10.impostaStanzaAdiacente("ovest", aulaN11);
			aulaN10.impostaStanzaAdiacente("est", laboratorio);
			laboratorio.impostaStanzaAdiacente("ovest", atrio);
			laboratorio.impostaStanzaAdiacente("est", aulaN11);
			biblioteca.impostaStanzaAdiacente("sud", atrio);

	        /* pone gli attrezzi nelle stanze */
			aulaN10.addAttrezzo(lanterna);
			atrio.addAttrezzo(osso);

			// il gioco comincia nell'atrio
	        stanzaIniziale = atrio;  
			stanzaFinale = biblioteca;
			stanzaCorrente = atrio;
	    }
	 
	 public Stanza getVincente() {
		 return stanzaFinale;
	 }
	 
	 public Stanza getIniziale() {
		 return stanzaIniziale;
	 }
	 
	 public void setStanzaCorrente(Stanza stanzaCorrente) {
			this.stanzaCorrente = stanzaCorrente;
		}

	public Stanza getStanzaCorrente() {
			return this.stanzaCorrente;
		}
		
	 
	 
	 
}
