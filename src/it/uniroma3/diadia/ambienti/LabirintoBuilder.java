package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {

	private Labirinto labirinto;
	private Stanza ultimaStanzaAggiunta;
	private Map<String, Stanza> nome2stanza;
	private Stanza stanzaIniziale; 

	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.nome2stanza = new HashMap<String, Stanza>();
	}
	
	public Map<String, Stanza> getNome2stanza() {
		return nome2stanza;
	}
	
	

	public Labirinto getLabirinto() {
		this.labirinto.setStanzaIniziale(this.stanzaIniziale); 
		return this.labirinto;
	}

	public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
		Stanza i = new Stanza(stanzaIniziale);
		this.labirinto.setStanzaCorrente(i);
		this.stanzaIniziale=i;
		this.UltimaStanzaAggiuntaEAggiorna(i);
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
		Stanza s = new Stanza(stanzaVincente);
		this.labirinto.setStanzaVincente(s);
		this.UltimaStanzaAggiuntaEAggiorna(s);
		return this;
	}

	public LabirintoBuilder addStanza(String stanza) {
		Stanza s = new Stanza(stanza);
		this.UltimaStanzaAggiuntaEAggiorna(s);
		return this;
	}	

	public LabirintoBuilder addAttrezzo(String attrezzo, int peso) {
		Attrezzo a = new Attrezzo(attrezzo, peso);
		if(this.ultimaStanzaAggiunta==null)
			return this;
		this.ultimaStanzaAggiunta.addAttrezzo(a);
		return this;
	}
	
	public Stanza getStanza(String nome) {
	    return this.nome2stanza.get(nome);
	}
	
	

	public LabirintoBuilder addAdiacenza(String stanzaCorrente, String stanzaAdiacente, String direzione) {
	    Stanza c = this.getStanza(stanzaCorrente);
	    Stanza a = this.getStanza(stanzaAdiacente);
	    c.impostaStanzaAdiacente(direzione, a);
	    return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nome) {
		Stanza stanza = new StanzaMagica(nome);
		this.UltimaStanzaAggiuntaEAggiorna(stanza);
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nome, int qualcheValore) {
	    Stanza stanza = new StanzaMagica(nome, qualcheValore);
	    this.UltimaStanzaAggiuntaEAggiorna(stanza);
	    return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nome, String attrezzoPerVedere) {
		Stanza stanza = new StanzaBuia(nome,attrezzoPerVedere);
		this.nome2stanza.put(nome, stanza);
		this.UltimaStanzaAggiuntaEAggiorna(stanza);
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String stanza,String direzionebloccata, String oggettochesblocca ) {
		Stanza s = new StanzaBloccata(stanza,direzionebloccata,oggettochesblocca);
		this.UltimaStanzaAggiuntaEAggiorna(s);
		return this;
	}
	
	public void UltimaStanzaAggiuntaEAggiorna(Stanza stanza) {
		this.ultimaStanzaAggiunta = stanza;
		this.nome2stanza.put(stanza.getNome(), stanza);
	}
	
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
	
	
	 public LabirintoBuilder creaLabirintoPredefinito() {
	        return this.addStanzaIniziale("Atrio")
	        		.addAttrezzo("osso", 1)
	        		.addAttrezzo("piedediporco", 2)
	        		.addStanzaVincente("Biblioteca")
	                .addStanzaBloccata("Aula N10", "ovest", "piedediporco")
	                .addAttrezzo("lanterna", 3)
	                .addStanzaMagica("Aula N11",3)
	                .addStanzaBuia("Laboratorio", "lanterna")
	                .addAdiacenza("Atrio", "Biblioteca", "nord")
	                .addAdiacenza("Atrio", "Aula N11", "est")
	                .addAdiacenza("Atrio", "Aula N10", "sud")
	                .addAdiacenza("Atrio", "Laboratorio", "ovest")
	                .addAdiacenza("Aula N11", "Laboratorio", "est")
	                .addAdiacenza("Aula N11", "Atrio", "ovest")
	                .addAdiacenza("Aula N10", "Atrio", "nord")
	                .addAdiacenza("Aula N10", "Aula N11", "est")
	                .addAdiacenza("Aula N10", "Laboratorio", "ovest")
	                .addAdiacenza("Laboratorio", "Atrio", "est")
	                .addAdiacenza("Laboratorio", "Aula N11", "ovest")
	                .addAdiacenza("Biblioteca", "Atrio", "sud");
	    }
	
}

