package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	
	private IO io;
	
	public ComandoFine(IO io) {
		this.io=io;
	}

	@Override
	public void esegui(Partita partita) {
		 io.mostraMessaggio("Grazie di aver giocato!"); 
		 partita.setFinita();
	}

	@Override
	public void setParametro(String parametro) {
		
	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public String getNome() {
		return "fine";
	}
}
