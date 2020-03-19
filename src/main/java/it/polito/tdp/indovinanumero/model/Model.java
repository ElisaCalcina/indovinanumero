package it.polito.tdp.indovinanumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	private final int NMAX=100; //final= lo uso se il numero è sempre uguale durante tutte le partite
	private final int TMAX=8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco;
	
	private Set<Integer> tentativi; //-->ogni volta che inizio una nuova partita creo un nuovo contenitore che contiene la storia della partita
	
	public Model() {
		this.inGioco=false;
		this.tentativiFatti=0;
	}
	
	//metodo richiamato dal controllore
	public void nuovaPartita() {
		//gestione dell'inizio di una nuova partita- logica del gioco
		this.segreto=(int)(Math.random()*NMAX)+1;
    	this.tentativiFatti=0;
    	this.inGioco=true;
    	this.tentativi= new HashSet<Integer>();
	}
	
	public int tentativo(int tentativo) { //se utente ha indovinato =0; se troppo alto=1; se troppo basso=-1;
		//controllo se la partita è in corso
		if(!inGioco) {
			throw new IllegalStateException("La partita è già terminata\n");
		}
		
		//controllo l'input-->tentativo fatto dall'utente è un numero valido? 
		//1) poichè riceve intero il controllo è fatto nel controller
		
		//2) controllo che il tentativo sta nell'intervallo
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire un numero che non hai ancora utilizzato tra 1 e "+NMAX+ "\n");
		}
		//il tentativo è valido--> possiamo "provarlo"
		tentativiFatti++;
		this.tentativi.add(tentativo);
		
		if(this.tentativiFatti==TMAX) {
			this.inGioco=false;
		}
		
		if(tentativo==this.segreto) {
			this.inGioco=false;
			return 0;
		}
		
		if(tentativo<this.segreto) {
			return -1;
		} else {
			return 1;
		}
	}

	private boolean tentativoValido(int tentativo) {
		if(tentativo<1 || tentativo>NMAX) {
			return false;
		} else {
			if(this.tentativi.contains(tentativo)) {
				return false;
			}
			return true;
		}
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	
}
