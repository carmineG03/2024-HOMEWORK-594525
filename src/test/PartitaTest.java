package test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {
	
	Partita p = new Partita();
	Stanza s = new Stanza("Stanza");

	@Test
	public void testGetStanzaVincente() {
		assertEquals("Biblioteca",p.getStanzaVincente().getNome());
	}
	
	@Test
	public void testSetStanzaCorrente() {
		p.setStanzaCorrente(s);
		assertEquals(s,p.getStanzaCorrente());
	}
	
	@Test
	public void testIsFinita() {
		assertFalse(p.isFinita());
	}

}