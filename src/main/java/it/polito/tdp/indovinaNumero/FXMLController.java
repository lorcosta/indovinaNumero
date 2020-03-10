package it.polito.tdp.indovinaNumero;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private final int NMAX=100;
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
    void doNuova(ActionEvent event) {
    	//gestione dell'inizio di una nuova partita - Logica del gioco
    	this.segreto=(int)(Math.random()*NMAX)+1;
    	this.tentativiFatti=0;
    	this.inGioco=true;
    	//gestione dell'interfaccia
    	this.layoutTentativo.setDisable(false);
    	txtRisultato.clear();
    	txtRimasti.setText(""+TMAX);
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	//Leggere l'input dell'utente
    	String ts= txtTentativi.getText();
    	int tentativo;
    	try {
    		tentativo= Integer.parseInt(ts);
    	}catch (NumberFormatException nfe) {
    		nfe.printStackTrace();
    		txtRisultato.appendText("Valore inserito per la prova non valido\n");
    		return;
    	}
    	tentativiFatti++;
    	if(tentativo==segreto) {
    		//ho indovinato
    		txtRisultato.appendText("Hai Vinto! Hai utilizzato: "+this.tentativiFatti+" tentativi\n");
    		this.layoutTentativo.setDisable(true);
    		inGioco=false;
    		return;
    	}
    	else if(tentativiFatti==TMAX) {
    		//Ho esaurito i tentativi
    		txtRisultato.appendText("Hai Perso! Il numero segreto era: "+this.segreto+"\n");
    		this.layoutTentativo.setDisable(true);
    		inGioco=false;
    		return;
    	}
    	//informare l'utente se il tentativo è troppo alto o troppo basso
    	if(tentativo<this.segreto) {
    		txtRisultato.appendText("Tentativo troppo BASSO\n");
    	}else txtRisultato.appendText("Tentativo troppo ALTO\n");
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
