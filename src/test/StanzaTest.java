package test;
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;



public class StanzaTest {
	Stanza s1 = new Stanza("s1");
	Stanza s2 = new Stanza("s2");
	Attrezzo m = new Attrezzo("martello",42);
	
	

	@Test
	public void testGetStanzaAdiacente() {
		s1.getStanzaAdiacente("nord");
	}
	
	@Test
	public void testImpostaStanzaAdiacente() {
		s1.impostaStanzaAdiacente("nord", s2);
		assertEquals(s2,s1.getStanzaAdiacente("nord"));
	}
	@Test
	public void testAddAttrezzo() {
		assertTrue(s1.addAttrezzo(m));
	}

}
