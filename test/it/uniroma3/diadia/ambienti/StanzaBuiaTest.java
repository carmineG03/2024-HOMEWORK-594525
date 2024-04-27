package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {

	StanzaBuia stanzaBuia = new StanzaBuia("aulaN11", "key");
	Attrezzo AttrezzoKey = new Attrezzo("key", 1);
	IO io = new IOConsole();

	@Test
	public void testGetDescrizione_SenzaAttrezzo() {
		assertFalse(this.stanzaBuia.getDescrizione(io));
	}

	@Test
	public void testGetDescrizione_ConAttrezzo() {
		stanzaBuia.addAttrezzo(AttrezzoKey);
		assertTrue(this.stanzaBuia.getDescrizione(io));
	}

}
