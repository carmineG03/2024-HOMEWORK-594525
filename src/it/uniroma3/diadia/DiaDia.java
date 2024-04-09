package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "vedi"};

	private Partita partita;
	private IOConsole io;

	public DiaDia(IOConsole console) {
		this.io = console;
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
				
		do		
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if(comandoDaEseguire.getNome().equals("prendi"))
			this.prendiAttrezzo(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posaAttrezzo(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("vedi"))
			this.vediBorsa();
		else
			io.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			io.mostraMessaggio("Hai vinto!");
			return true;
		} else if(this.partita.isFinita()) {
			io.mostraMessaggio("hai perso!");
			return true;
		}
		else
			return false;
	} 
	
	// implementazioni dei comandi dell'utente:
	
	/**
	 * Prende un attrezzo dalla stanza corrente e lo mette nella borsa.
	 * @param nomeAttrezzo il nome dell'attrezzo da Prendere
	 */
	
	private void prendiAttrezzo(String nomeAttrezzo) {
	    if (nomeAttrezzo != null) {
	        if (!nomeAttrezzo.isEmpty()) {
	            Attrezzo attrezzo = this.partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
	            if (attrezzo != null) {
	                if (this.partita.giocatore.getBorsa().addAttrezzo(attrezzo)) {
	                	 io.mostraMessaggio("Hai preso l'attrezzo: " + attrezzo.getNome());
	                } else {
	                    // Se la borsa è piena, rimetti l'attrezzo nella stanza corrente
	                    this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
	                    io.mostraMessaggio("Non puoi prendere l'attrezzo perché la borsa è piena.");
	                }
	            } else {
	                io.mostraMessaggio("L'attrezzo specificato non è presente nella stanza.");
	            }
	        } else {
	        	 io.mostraMessaggio("Il nome dell'attrezzo non può essere vuoto.");
	        }
	    } else {
	    	 io.mostraMessaggio("Il nome dell'attrezzo non può essere nullo.");
	    }
	}

	/**
	 * Deposita un attrezzo dalla borsa nella stanza corrente.
	 * @param nomeAttrezzo il nome dell'attrezzo da depositare
	 */
	private void posaAttrezzo(String nomeAttrezzo) {
	    if (!nomeAttrezzo.isEmpty()) {
	        Attrezzo attrezzo = this.partita.giocatore.getBorsa().removeAttrezzo(nomeAttrezzo);
	        if (attrezzo != null) {
	            if (this.partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
	            	 io.mostraMessaggio("Hai posato l'attrezzo: " + attrezzo.getNome());
	            } else {
	                // Se la stanza è piena, rimetti l'attrezzo nella borsa
	                this.partita.giocatore.getBorsa().addAttrezzo(attrezzo);
	                io.mostraMessaggio("Non puoi posare l'attrezzo perché la stanza è piena.");
	            }
	        } else {
	        	 io.mostraMessaggio("L'attrezzo specificato non è presente nella borsa.");
	        }
	    } else {
	    	 io.mostraMessaggio("Devi specificare il nome dell'attrezzo da posare.");
	    }
	}
	
	/**
	 * Permette all utente di vedere cosa c'è nella borsa.
	 */

	private void vediBorsa() {
	    Borsa borsaGiocatore = this.partita.giocatore.getBorsa();
	    if (borsaGiocatore.isEmpty()) {
	    	 io.mostraMessaggio("La tua borsa è vuota.");
	    } else {
	    	 io.mostraMessaggio("Oggetti nella tua borsa:");
	    	 io.mostraMessaggio(borsaGiocatore.toString());
	    }
	}

	

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			 io.mostraMessaggio(elencoComandi[i]+" ");
		 io.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null) {
			 io.mostraMessaggio("Dove vuoi andare ?");
			
			direzione= io.leggiRiga();
		}
		if (!partita.getStanzaCorrente().getBorsa().isEmpty()) {
			 io.mostraMessaggio("Questa è la borsa: ");
			 io.mostraMessaggio(partita.getStanzaCorrente().getBorsa().toString());
			 io.mostraMessaggio("Quale attrezzo vuoi eliminare?");
	        
	        String nomeAttrezzo = io.leggiRiga();
	        
	        // Rimuovi l'attrezzo dalla borsa se esiste
	        Attrezzo attrezzoRimosso = partita.getStanzaCorrente().getBorsa().removeAttrezzo(nomeAttrezzo);
	        if (attrezzoRimosso != null) {
	        	 io.mostraMessaggio("Hai rimosso l'attrezzo: " + attrezzoRimosso.getNome());
	        } else {
	        	 io.mostraMessaggio("L'attrezzo specificato non è presente nella borsa.");
	        }
	    }		
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			 io.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			
			partita.giocatore.setCfu(partita.giocatore.getCfu()-1);
			
		}
		 io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		 io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole console = new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();

		
	}
}