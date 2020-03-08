package it.polito.tdp.indovinanumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private final int NMAX=100; //final= lo uso se il numero è sempre uguale durante tutte le partite
	private final int TMAX=8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco=false;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnNuova;

    @FXML
    private TextField txtRimasti;

    @FXML
    private HBox layoutTentativo;

    @FXML
    private TextField txtTentativi;

    @FXML
    private Button btnProva;

    @FXML
    void doNuova(ActionEvent event) { //evento associato al click della nuova partita
    	//gestione dell'inizio di una nuova partita - Logica del gioco
    	this.segreto=(int)(Math.random()*NMAX)+1;
    	this.tentativiFatti=0;
    	this.inGioco=true;
    	
    	//gestione dell'interfaccia-->abilito hbox per inserimento del nuovo tentativo
    	layoutTentativo.setDisable(false);
    	txtRisultato.clear();
    	txtRimasti.setText(Integer.toString(TMAX));

    }

    @FXML
    void doTentativo(ActionEvent event) {
    	//leggere l'input dell'utente-->VA SEMPRE CONTROLLATO
    	String ts=txtTentativi.getText();
    	int tentativo;
    	try {
    		tentativo= Integer.parseInt(ts);
    	}catch(NumberFormatException e) {
    		txtRisultato.appendText("Devi inserire un numero! \n");
    		return ;
    	}
    	tentativiFatti++;
    	
    	if(tentativo==segreto) {
    		//ho indovinato
    		txtRisultato.appendText("HAI VINTO! Hai utilizzato " + this.tentativiFatti +" tentativi");
    		layoutTentativo.setDisable(true);
    		inGioco=false;
    		return;
    	}
    	
    	//devo controllare di non aver esaurito i tentativi
    	if(tentativiFatti==TMAX) {
    		//ho esaurito i tentativi-->ho perso
    		txtRisultato.appendText("HAI PERSO! Il numero segreto era: "+ this.segreto);
    		layoutTentativo.setDisable(true);
    		inGioco=false;
    		return;
    	}
    	
    	//se non ho perso, sono in un caso intermedio
    	//informare l'utente se il tentativo è troppo alto o troppo basso
    	if(tentativo<this.segreto) {
    		txtRisultato.appendText("Tentativo troppo basso \n");
    	}
    	else {
    		txtRisultato.appendText("Tentativo troppo alto \n");
    	}
    		txtRimasti.setText(Integer.toString(TMAX-tentativiFatti));
    	
    }

    @FXML
    void initialize() {
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert layoutTentativo != null : "fx:id=\"layoutTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
