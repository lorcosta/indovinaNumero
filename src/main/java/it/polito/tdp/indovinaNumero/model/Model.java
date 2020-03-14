package it.polito.tdp.indovinaNumero.model;

import java.security.InvalidParameterException;
import java.util.*;

public class Model {
	private final int NMAX=100;
	private final int TMAX=8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco;
	
	private Set<Integer> tentativi;
	
	public Model() {
		this.inGioco=false;
		this.tentativiFatti=0;
	}
	
	public int getTentativiFatti() {
		return tentativiFatti;
	}
	
	public int getTMAX() {
		return TMAX;
	}

	public void nuovaPartita(){
		//gestione dell'inizio di una nuova partita - Logica del gioco
    	this.segreto=(int)(Math.random()*NMAX)+1;
    	this.tentativiFatti=0;
    	this.inGioco=true;
    	this.tentativi= new HashSet<Integer>();
	}
	
	public int tentativo(int tentativo){//se il tentativo è giusto ritorna zero, se alto ritorna 1 se basso ritorna -1
		//Controllo se la partita è in corso
		if(!inGioco) {
			throw new IllegalStateException("Non c'e' una partita in corso\n");
		}
		//controllo l'input
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire un numero che non hai ancora inserito tra 1 e "+NMAX+"\n");
		}
		//il tentativo è valido-->possiamo provarlo
		
		tentativiFatti++;
		this.tentativi.add(tentativo);//tiene traccia dei tentativi fatti
		if(this.tentativiFatti==TMAX) {
			this.inGioco=false;
			
		}
		
		if(tentativo==this.segreto) {
			this.inGioco=false;
			return 0;
		}
		if(tentativo<segreto) {
			return -1;
		}else {
			return 1;
		}
		
		
	}
	
	private boolean tentativoValido (int tentativo) {
		if(tentativo <1 || tentativo>NMAX || this.tentativi.contains(tentativo)) {
			return false;
		}
		return true;
	}
}
