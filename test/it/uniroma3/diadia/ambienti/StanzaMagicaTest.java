package it.uniroma3.diadia.ambienti;



import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	
	StanzaMagica stanza = new StanzaMagica("stanza");
	Attrezzo attrezzo = new Attrezzo("osso",1);
	
	@Test
	void modificaAttrezzoTest_Disattivata() {
		stanza.addAttrezzo(attrezzo);
		assertEquals(attrezzo,stanza.getAttrezzi()[0]);
	}
	
	@Test
	void modificaAttrezzoTest_Attivata() {
		for(int i = 0; i < 3; i++) {
			stanza.addAttrezzo(attrezzo);
			stanza.removeAttrezzo(attrezzo);
		}
		
		stanza.addAttrezzo(attrezzo);
		stanza.modificaAttrezzo(attrezzo);
		
		assertEquals(attrezzo.getNome(),stanza.getAttrezzi()[0].getNome());
	}
}
