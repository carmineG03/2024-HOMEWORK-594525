package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Test;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;


public class FabbricaDiComandiFisarmonicaTest {

	IO io = new IOConsole();
	FabbricaDiComandiFisarmonica fabbrica = new FabbricaDiComandiFisarmonica(io);
	
	
	
	ComandoVai comandoVai = new ComandoVai(io, "sud");
	@Test
	public void costruisciComandoVaiTest() {
		assertEquals(comandoVai.getNome(),fabbrica.costruisciComando("vai sud").getNome());
		assertEquals(comandoVai.getParametro(),fabbrica.costruisciComando("vai sud").getParametro());
	}
	
	
	ComandoPrendi comandoPrendi = new ComandoPrendi (io, "osso");
	@Test
	public void costruisciComandoPrendiTest() {
		assertEquals(comandoPrendi.getNome(),fabbrica.costruisciComando("prendi osso").getNome());
		assertEquals(comandoPrendi.getParametro(),fabbrica.costruisciComando("prendi osso").getParametro());
	}
	
	
	ComandoPosa comandoPosa = new ComandoPosa (io, "osso");
	@Test
	public void costruisciComandoPosaTest() {
		assertEquals(comandoPosa.getNome(),fabbrica.costruisciComando("posa osso").getNome());
		assertEquals(comandoPosa.getParametro(),fabbrica.costruisciComando("posa osso").getParametro());
	}
	
	
	
	ComandoAiuto comandoAiuto = new ComandoAiuto (io);
	@Test
	public void costruisciComandoAiutoTest() {
		assertEquals(comandoAiuto.getNome(),fabbrica.costruisciComando("aiuto").getNome());
		assertEquals(comandoAiuto.getParametro(),fabbrica.costruisciComando("aiuto").getParametro());
	}
	
	
	
	ComandoFine comandoFine = new ComandoFine(io);
	@Test
	public void costruisciComandoFineTest() {
		assertEquals(comandoFine.getNome(),fabbrica.costruisciComando("fine").getNome());
		assertEquals(comandoFine.getParametro(),fabbrica.costruisciComando("fine").getParametro());
	}
	
	
	ComandoGuarda comandoGuarda = new ComandoGuarda(io);
	@Test
	public void costruisciComandoGuardaTest() {
		assertEquals(comandoGuarda.getNome(),fabbrica.costruisciComando("guarda").getNome());
		assertEquals(comandoGuarda.getParametro(),fabbrica.costruisciComando("guarda").getParametro());
	}
	

	ComandoNonValido comandoNonValido = new ComandoNonValido(io);
	@Test
	public void costruisciComandoNonValidoTest() {
		assertEquals(comandoNonValido.getNome(),fabbrica.costruisciComando("stringa casuale").getNome());
		assertEquals(comandoNonValido.getParametro(),fabbrica.costruisciComando("stringa casuale").getParametro());
	}
}
