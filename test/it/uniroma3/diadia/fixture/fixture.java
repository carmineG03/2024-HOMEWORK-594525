package it.uniroma3.diadia.fixture;

import it.uniroma3.diadia.comandi.*;
import java.util.*;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class fixture {

	private IOSimulator ioS;
	private Labirinto labirinto;
	private DiaDia diaDia;
	private List<String> comandiDaIniettare;

	@BeforeEach
	void setUp() throws Exception {
		this.ioS = new IOSimulator();
		this.comandiDaIniettare = new ArrayList<>();
		comandiDaIniettare.add("aiuto");
		comandiDaIniettare.add("fine");
		comandiDaIniettare.add("guarda");
		comandiDaIniettare.add("posa");
		comandiDaIniettare.add("prendi");
		comandiDaIniettare.add("vai");

		this.labirinto = new Labirinto();
		this.labirinto.creaStanzeDefault();
		this.diaDia = new DiaDia(labirinto, ioS);

		comandiDaIniettare.add("vai nord");
		comandiDaIniettare.add("vai sud");
		comandiDaIniettare.add("vai ovest");
		comandiDaIniettare.add("vai est");
		comandiDaIniettare.add("vai NonEsiste");

		comandiDaIniettare.add("Non Esiste");

		comandiDaIniettare.add("posa lanterna");
		comandiDaIniettare.add("posa osso");
		comandiDaIniettare.add("posa NonEsiste");

		comandiDaIniettare.add("prendi lanterna");
		comandiDaIniettare.add("prendi osso");
		comandiDaIniettare.add("prendi NonEsiste");

		this.ioS.setComandiDaIniettare(comandiDaIniettare);
	}

	@Test
	void testFine() {
		ComandoFine comandoFine = new ComandoFine();
		this.ioS.setIndiceAttuale(1);
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(1), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(1), this.ioS.getComandoAppenaLetto());
		assertEquals(comandoFine.getMsgfine(), this.ioS.getComandiStampati().get(0));
	}

	@Test
	void testGuarda() {
		this.ioS.setIndiceAttuale(2);
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(2), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(2), this.ioS.getComandoAppenaLetto());
		assertEquals("Ti trovi in: " + this.diaDia.getPartita().getStanzaCorrente().getDescrizione() + "\n"
				+ this.diaDia.getPartita().getGiocatore(), this.ioS.getComandiStampati().get(0));
	}

	// ----------------------------- INIZIO DEI TEST VAI
	// -----------------------------

	@Test
	void testVai() {
		this.ioS.setIndiceAttuale(5);
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(5), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(5), this.ioS.getComandoAppenaLetto());
		assertEquals("Dove vuoi andare? Devi specificare una direzione", this.ioS.getComandiStampati().get(0));
	}

	@Test
	void testVaiNord() {
		this.ioS.setIndiceAttuale(6);
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(6), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(6), this.ioS.getComandoAppenaLetto());
		assertEquals(this.diaDia.getPartita().getStanzaCorrente().getNome(), this.ioS.getComandiStampati().get(0));
		assertEquals("Hai vinto!", this.ioS.getComandiStampati().get(1));
	}

	@Test
	void testVaiOgniDirezione() {
		this.ioS.setIndiceAttuale(9); // vai est -> Arrivo in n11
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(9), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(9), this.ioS.getComandoAppenaLetto());
		assertEquals(this.diaDia.getPartita().getStanzaCorrente().getNome(), this.ioS.getComandiStampati().get(0));

		this.ioS.setIndiceAttuale(8); // vai ovest -> Torno in Atrio
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(8), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(8), this.ioS.getComandoAppenaLetto());
		assertEquals(this.diaDia.getPartita().getStanzaCorrente().getNome(), this.ioS.getComandiStampati().get(1));

		this.ioS.setIndiceAttuale(8); // vai ovest -> Arrivo in Laboratorio
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(8), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(8), this.ioS.getComandoAppenaLetto());
		assertEquals(this.diaDia.getPartita().getStanzaCorrente().getNome(), this.ioS.getComandiStampati().get(2));

		this.ioS.setIndiceAttuale(9); // vai est -> Torno in Atrio
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(9), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(9), this.ioS.getComandoAppenaLetto());
		assertEquals(this.diaDia.getPartita().getStanzaCorrente().getNome(), this.ioS.getComandiStampati().get(3));

		this.ioS.setIndiceAttuale(7); // vai sud -> Arrivo in n10
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(7), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(7), this.ioS.getComandoAppenaLetto());
		assertEquals(this.diaDia.getPartita().getStanzaCorrente().getNome(), this.ioS.getComandiStampati().get(4));

		this.ioS.setIndiceAttuale(6); // vai nord -> Torno in Atrio
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(6), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(6), this.ioS.getComandoAppenaLetto());
		assertEquals(this.diaDia.getPartita().getStanzaCorrente().getNome(), this.ioS.getComandiStampati().get(5));

		this.ioS.setIndiceAttuale(6); // vai nord -> Vinco in Biblioteca
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(6), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(6), this.ioS.getComandoAppenaLetto());
		assertEquals(this.diaDia.getPartita().getStanzaCorrente().getNome(), this.ioS.getComandiStampati().get(6));
		assertEquals("Hai vinto!", this.ioS.getComandiStampati().get(7));

	}

	// ----------------------------- FINE DEI TEST VAI -----------------------------

	// ----------------------------- INIZIO DEI TEST PRENDI
	// -----------------------------
	@Test
	void testPrendi() {
		ComandoPrendi comandoPrendi = new ComandoPrendi();
		this.ioS.setIndiceAttuale(4); // Prendi
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(4), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(4), this.ioS.getComandoAppenaLetto());
		assertEquals(comandoPrendi.getMsgparametromancante(), this.ioS.getComandiStampati().get(0));

		this.ioS.setIndiceAttuale(17); // Prendi NonEsiste
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(17), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(17), this.ioS.getComandoAppenaLetto());
		assertEquals(comandoPrendi.getMsgnonpresente(), this.ioS.getComandiStampati().get(1));

		this.ioS.setIndiceAttuale(15); // Prendi Lanterna (che non c'è)
		assertFalse(this.diaDia.getPartita().getStanzaCorrente().hasAttrezzo("lanterna"));
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(15), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(15), this.ioS.getComandoAppenaLetto());
		assertEquals(comandoPrendi.getMsgnonpresente(), this.ioS.getComandiStampati().get(2));

		this.ioS.setIndiceAttuale(16); // Prendi Osso (che c'è)
		assertTrue(this.diaDia.getPartita().getStanzaCorrente().hasAttrezzo("osso"));
		Attrezzo osso = this.diaDia.getPartita().getStanzaCorrente().getAttrezzo("osso");
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(16), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(16), this.ioS.getComandoAppenaLetto());
		assertEquals("Hai preso l'attrezzo: " + osso.getNome() + ", di peso: " + osso.getPeso(),
				this.ioS.getComandiStampati().get(3));
	}

	// ----------------------------- FINE DEI TEST PRENDI
	// -----------------------------

	// ----------------------------- INIZIO DEI TEST POSA
	// -----------------------------

	@Test
	void testPosa() {
		ComandoPosa comandoPosa = new ComandoPosa();
		this.ioS.setIndiceAttuale(3); // posa
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(3), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(3), this.ioS.getComandoAppenaLetto());
		assertEquals(comandoPosa.getMsgnoparametro(), this.ioS.getComandiStampati().get(0));

		this.ioS.setIndiceAttuale(12); // posa lanterna (che non ho)
		assertFalse(this.diaDia.getPartita().getGiocatore().getBorsa().hasAttrezzo("lanterna"));
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(12), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(12), this.ioS.getComandoAppenaLetto());
		assertEquals(comandoPosa.getMsgnontrovato(), this.ioS.getComandiStampati().get(1));

		this.ioS.setIndiceAttuale(13); // posa osso (che non ho)
		assertFalse(this.diaDia.getPartita().getGiocatore().getBorsa().hasAttrezzo("osso"));
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(13), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(13), this.ioS.getComandoAppenaLetto());
		assertEquals(comandoPosa.getMsgnontrovato(), this.ioS.getComandiStampati().get(2));

		this.ioS.setIndiceAttuale(14); // posa NonEsiste (che non esiste)
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(14), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(14), this.ioS.getComandoAppenaLetto());
		assertEquals(comandoPosa.getMsgnontrovato(), this.ioS.getComandiStampati().get(3));

		this.ioS.setIndiceAttuale(16); // Prendi Osso (che c'è), per poi posarlo
		assertTrue(this.diaDia.getPartita().getStanzaCorrente().hasAttrezzo("osso"));
		Attrezzo osso = this.diaDia.getPartita().getStanzaCorrente().getAttrezzo("osso");
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(16), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(16), this.ioS.getComandoAppenaLetto());
		assertEquals("Hai preso l'attrezzo: " + osso.getNome() + ", di peso: " + osso.getPeso(),
				this.ioS.getComandiStampati().get(4));

		// Posa osso che ora ho

		this.ioS.setIndiceAttuale(13); // posa osso (che ho)
		assertTrue(this.diaDia.getPartita().getGiocatore().getBorsa().hasAttrezzo("osso"));
		assertFalse(this.diaDia.getPartita().getStanzaCorrente().hasAttrezzo("osso"));
		this.diaDia.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(13), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(13), this.ioS.getComandoAppenaLetto());
		assertEquals("Hai posato l'attrezzo: " + osso.getNome(), this.ioS.getComandiStampati().get(5));
		assertFalse(this.diaDia.getPartita().getGiocatore().getBorsa().hasAttrezzo("osso"));
		assertTrue(this.diaDia.getPartita().getStanzaCorrente().hasAttrezzo("osso"));

	}

	@Test
	void testStanzaBloccata() {
		Labirinto labirintoBloccato = new Labirinto.LabirintoBuilder().addStanzaVincente("arrivo")
				.addStanzaBloccata("partenza locked", Direzione.nord, "chiave").setComeStanzaIniziale("partenza locked")
				.addAdiacenza("partenza locked", "arrivo", Direzione.nord).getLabirinto();
		DiaDia diaDiaBloccato = new DiaDia(labirintoBloccato, this.ioS);
		// Partenza Locked ha bisogno di una chiave per andare a nord verso "arrivo"
		assertEquals("partenza locked", diaDiaBloccato.getPartita().getStanzaCorrente().getNome());
		assertEquals("partenza locked", labirintoBloccato.getStanzaIniziale().getNome());
		assertEquals("arrivo", labirintoBloccato.getStanzaVincente().getNome());

		// Ora vado a nord senza CHIAVE
		ComandoVai comandoVai = new ComandoVai();
		this.ioS.setIndiceAttuale(6);
		diaDiaBloccato.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(6), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(6), this.ioS.getComandoAppenaLetto());
		assertEquals(diaDiaBloccato.getPartita().getLabirinto().getStanzaVincente().getStanzaAdiacente(Direzione.sud)
				.getNome(), "partenza locked");
		assertEquals("partenza locked", diaDiaBloccato.getPartita().getStanzaCorrente().getNome());
		assertEquals(comandoVai.getNonpuoiandarci(), this.ioS.getComandiStampati().get(0));
		assertEquals(diaDiaBloccato.getPartita().getStanzaCorrente().getNome(), this.ioS.getComandiStampati().get(1));

		// Ora metto la chiave nella stanza e vado a nord
		diaDiaBloccato.getPartita().getStanzaCorrente().addAttrezzo(new Attrezzo("chiave", 1));
		diaDiaBloccato.processaIstruzione("vai nord"); // gli dico "vai nord"
		assertEquals(this.comandiDaIniettare.get(6), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(6), this.ioS.getComandoAppenaLetto());
		assertEquals("arrivo", diaDiaBloccato.getPartita().getStanzaCorrente().getNome());
		assertEquals(diaDiaBloccato.getPartita().getStanzaCorrente().getNome(), this.ioS.getComandiStampati().get(2));
		assertEquals("Hai vinto!", this.ioS.getComandiStampati().get(3));

	}

	@Test
	void testStanzaMagica() {
		Labirinto labirintoMagico = new Labirinto.LabirintoBuilder().addStanzaMagica("Magica", 1) // la stanza magica ha
																									// soglia magica 1
				.setComeStanzaIniziale("Magica").getLabirinto();
		DiaDia diaDiaMagico = new DiaDia(labirintoMagico, this.ioS);
		assertEquals("Magica", diaDiaMagico.getPartita().getStanzaCorrente().getNome());
		assertEquals("Magica", labirintoMagico.getStanzaIniziale().getNome());
		Attrezzo noInvert = new Attrezzo("NoInvert", 1);
		diaDiaMagico.getPartita().getGiocatore().getBorsa().addAttrezzo(noInvert);
		//
		diaDiaMagico.processaIstruzione("posa NoInvert");
		assertEquals("Hai posato l'attrezzo: " + noInvert.getNome(), this.ioS.getComandiStampati().get(0));
		//
		assertTrue(diaDiaMagico.getPartita().getStanzaCorrente().hasAttrezzo("NoInvert"));
		assertFalse(diaDiaMagico.getPartita().getGiocatore().getBorsa().hasAttrezzo("NoInvert"));
		// Ora ho superato la soglia magica e deve invertire
		Attrezzo stellaDiNeutroni = new Attrezzo("stellaDiNeutroni", 10);
		diaDiaMagico.getPartita().getGiocatore().getBorsa().addAttrezzo(stellaDiNeutroni);
		diaDiaMagico.processaIstruzione("posa stellaDiNeutroni");
		assertEquals("Hai posato l'attrezzo: " + stellaDiNeutroni.getNome(), this.ioS.getComandiStampati().get(1));
		assertFalse(diaDiaMagico.getPartita().getGiocatore().getBorsa().hasAttrezzo("stellaDiNeutroni"));
		assertFalse(diaDiaMagico.getPartita().getStanzaCorrente().hasAttrezzo("stellaDiNeutroni"));
		assertTrue(diaDiaMagico.getPartita().getStanzaCorrente().hasAttrezzo("inortueNiDallets"));
	}

	@Test
	void testStanzaBuia() {
		StanzaBuia buiaTest = new StanzaBuia("BuiaTest", "WaifuRGBpotente");
		Labirinto labirintoBuio = new Labirinto.LabirintoBuilder().addStanzaBuia("Buio", "WaifuRGB") // la stanza buia
																										// ha bisogno di
																										// una waifu rgb
																										// per essere
																										// illuminata
				.setComeStanzaIniziale("Buio").getLabirinto();
		DiaDia diaDiaBuio = new DiaDia(labirintoBuio, this.ioS);
		assertEquals("Buio", diaDiaBuio.getPartita().getStanzaCorrente().getNome());
		assertEquals("Buio", labirintoBuio.getStanzaIniziale().getNome());
		// Mi trovo ora in una stanza buia
		this.ioS.setIndiceAttuale(2);
		diaDiaBuio.processaIstruzione(ioS.leggiRiga());
		assertEquals(this.comandiDaIniettare.get(2), this.ioS.getComandoAppenaLetto());
		assertEquals(this.ioS.getComandiDaIniettare().get(2), this.ioS.getComandoAppenaLetto());
		assertEquals(buiaTest.getMsgbuio() + "\n" + diaDiaBuio.getPartita().getGiocatore(),
				this.ioS.getComandiStampati().get(0));

	}
	
	// ----------------------------- INIZIO SIMULAZIONI
	

	public static IOSimulator creaSimulazionePartitaEGiocaEasy(List<String> comandiDaLeggere) throws Exception {
		IOSimulator io = new IOSimulator();
		Labirinto labirinto = Labirinto.newBuilder("labirinto2.txt").getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		return io;
	}

	public static IOSimulator creaSimulazionePartitaEGiocaHard(List<String> comandiDaLeggere) throws Exception {
		IOSimulator io = new IOSimulator();
		Labirinto labirinto = Labirinto.newBuilder("labirinto4.txt").getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		return io;
	}

	public static Attrezzo creaAttrezzoEAggiugniAStanza(Stanza stanzaDaRiempire, String nomeAttrezzo, int peso) {
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		stanzaDaRiempire.addAttrezzo(attrezzo);
		return attrezzo;
	}
	
	// ----------------------------- FINE SIMULAZIONI

}
