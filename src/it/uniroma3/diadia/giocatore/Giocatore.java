package it.uniroma3.diadia.giocatore;

public class Giocatore {
	
	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {
		borsa = new Borsa();
		cfu=20;
		
		
	}
	
	public void setCfu(int cfu) {
		this.cfu=cfu;
		
	}
	
	public int getCfu() {
		return this.cfu;
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}

}
