package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoPrendiTest {

	IO io = new IOConsole();
	Partita partita = new Partita();
	Stanza stanza = new Stanza("iniziale");
	Attrezzo attrezzo = new Attrezzo("osso",1);
	Attrezzo attrezzoErrato = new Attrezzo("lampada",2);
	ComandoPrendi comando = new ComandoPrendi(io, attrezzo.getNome());
	ComandoPrendi comandoErrato = new ComandoPrendi(io, attrezzoErrato.getNome());
	Giocatore giocatore = new Giocatore();
	Borsa borsa = new Borsa();

	
	@Test
	public void eseguiTest() {
		partita.setStanzaCorrente(stanza);
		partita.setGiocatore(giocatore);
		giocatore.setBorsa(borsa);
		stanza.addAttrezzo(attrezzo);
		
		comando.esegui(partita);
		
		assertTrue(giocatore.getBorsa().hasAttrezzo(attrezzo.getNome()));
		assertFalse(stanza.hasAttrezzo(attrezzo.getNome()));
	}
	
	@Test
	public void attrezzoInesistenteTest() {
		partita.setStanzaCorrente(stanza);
		partita.setGiocatore(giocatore);
		giocatore.setBorsa(borsa);
		stanza.addAttrezzo(attrezzo);
		
		comandoErrato.esegui(partita);
		
		assertTrue(stanza.hasAttrezzo(attrezzo.getNome()));
		assertFalse(stanza.hasAttrezzo(attrezzoErrato.getNome()));
		assertFalse(giocatore.getBorsa().hasAttrezzo(attrezzoErrato.getNome()));
		
	}
}

