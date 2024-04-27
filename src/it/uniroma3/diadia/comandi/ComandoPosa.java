package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
	
	
	 private IO io;
	 private String nomeAttrezzo;
	 
	 public ComandoPosa(IO io, String direzione) {
		 this.io = io;
		 this.nomeAttrezzo = direzione;
	 }
	

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;

	}

	public void esegui(Partita partita) {
		   if (!nomeAttrezzo.isEmpty()) {
		        Attrezzo attrezzo = partita.giocatore.getBorsa().removeAttrezzo(nomeAttrezzo);
		        if (attrezzo != null) {
		            if (partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
		            	 io.mostraMessaggio("Hai posato l'attrezzo: " + attrezzo.getNome());
		            } else {
		                // Se la stanza è piena, rimetti l'attrezzo nella borsa
		                partita.giocatore.getBorsa().addAttrezzo(attrezzo);
		                io.mostraMessaggio("Non puoi posare l'attrezzo perché la stanza è piena.");
		            }
		        } else {
		        	 io.mostraMessaggio("L'attrezzo specificato non è presente nella borsa.");
		        }
		   }
		       else  {
		    	 io.mostraMessaggio("Devi specificare il nome dell'attrezzo da posare.");
		     }
	}
	
	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public String getNome() {
		return "posa";
	}
}


