package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

class StanzaBloccataTest {

	StanzaBloccata stanzaBloccata = new StanzaBloccata("stanza", "est", "osso");
	Stanza stanzaAdiacente = new Stanza("stanzaAdiacente");
	Attrezzo attrezzoKey = new Attrezzo("osso",1);
	IO io = new IOConsole();
	
	
	@Test
    public void testGetDescrizione_SenzaAttrezzo() {
		assertFalse(this.stanzaBloccata.getDescrizione(io));
    }
    
    @Test
    public void testGetDescrizione_ConAttrezzo() {
    	stanzaBloccata.addAttrezzo(attrezzoKey);
		assertTrue(this.stanzaBloccata.getDescrizione(io));
    }
    
    @Test
    public void testGetSanzaAdiacente_SenzaAttrezzo() {
    	assertEquals(this.stanzaBloccata,this.stanzaBloccata.getStanzaAdiacente("est"));
    }
    
    @Test
    public void testGetSanzaAdiacente_ConAttrezzo() {
    	stanzaBloccata.impostaStanzaAdiacente("est", stanzaAdiacente);
    	stanzaBloccata.addAttrezzo(attrezzoKey);
    	assertEquals(this.stanzaAdiacente,this.stanzaBloccata.getStanzaAdiacente("est"));
    }
}
