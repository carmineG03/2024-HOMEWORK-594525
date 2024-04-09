package test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;



public class BorsaTest {
	
	Borsa b = new Borsa();
	Attrezzo candela;
	Attrezzo sega;

	@Before
	public void setUp() {
		candela = new Attrezzo("candela",1);
		sega = new Attrezzo("sega",16);
	}
	
	
	@Test
	public void testAddAttrezzoMinoreDiDieci() {
		assertTrue(b.addAttrezzo(candela));
	}
	
	@Test
	public void testAddAttrezzoMaggioreDiDieci() {
		assertFalse(b.addAttrezzo(sega));
	}
	
	@Test
	public void testGetPeso() {
		b.addAttrezzo(candela);
		assertEquals(candela,b.getAttrezzo("candela"));
	}

}
