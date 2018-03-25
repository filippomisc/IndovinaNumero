package it.polito.tdp.indonumero;

import java.security.*;

public class Model {
	
	

	private int NMAX = 100;
	private int TMAX = 7; //tentativi massimi per poter indovinare il numero
	
	private int numSegreto; //numero da indovinare
	private int tentativi; //tentativi gia fatti
	
	private boolean inGame = false; //sono in gioco o no
									//impostato su false io comincio senza 
									//iniziare una nuova partita
	
	public Model() {
		this.inGame = false;
	}
	
	public int getNumSegreto() {
		return numSegreto;
	}

	public void setNumSegreto(int numSegreto) {
		this.numSegreto = numSegreto;
	}

	public void newGame() {
		this.numSegreto = (int)(Math.random()*NMAX)+1;
		this.tentativi = 0;
		this.inGame = true;
	}
		
	/**
	 * fai un tentativo per indovinare il numero segreto
	 * @param t è il numero di tentativi
	 * @return 0 se ho indovinato, -1 se il tentativo è minore del numero segreto,
	 * +1 se il tentativo è superiore al numero segreto
	 */
	public int tentativo(int t) {
		if(!inGame) {
			throw new IllegalStateException("Partita non attiva");
		}
		if(!valoreValido(t)) {
			throw new InvalidParameterException("tentativo fuori range");
		}
		
		
		this.tentativi++;
		if(this.tentativi == this.TMAX)
			this.inGame = false;//usciamo dal gioco se superiamo i tentativi
		
		
		if(t==this.numSegreto) {
			this.inGame = false;//usciamo dal gioco se abbiamo indovinato il numero segreto
			return 0;
		}
		if(t<this.numSegreto)
			return -1;
		return +1;
	}

	/**
	 * controlla se il tentativo fornito è consone 
	 * alle regole del gioco stabilite
	 * @param t valore del tenativo
	 * @return (@code true) tentativo valido
	 */
	public boolean valoreValido(int t) {
		return t>=0 || t>this.NMAX;
	}

	public boolean isInGame() {
		return inGame;
		}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}

	public int getTentativi() {
		return tentativi;
	}
	}
	