/**
 * Sample Skeleton for 'IndoNumero.fxml' Controller Class
 */

package it.polito.tdp.indonumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndoNumeroController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNuova"
    private Button btnNuova; // Value injected by FXMLLoader

    @FXML // fx:id="txtCurrent"
    private TextField txtCurrent; // Value injected by FXMLLoader

    @FXML // fx:id="txtMax"
    private TextField txtMax; // Value injected by FXMLLoader

    @FXML // fx:id="boxDiGioco"
    private HBox boxDiGioco; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativo"
    private TextField txtTentativo; // Value injected by FXMLLoader
    
    @FXML // fx:id="btnAbbandona"
    private Button btnAbbandona; // Value injected by FXMLLoader

    @FXML // fx:id="txtLog"
    private TextArea txtLog; // Value injected by FXMLLoader
    
    @FXML
    void handleAbbandona(ActionEvent event) {
    	btnNuova.setDisable(false);
    	boxDiGioco.setDisable(true);
    	txtCurrent.clear();
    	txtMax.clear();
    	txtLog.clear();
    	txtLog.appendText("Ciao! Indovina un numero da 1 a 100");
    }
    
    @FXML
    //quando clicco sul pulsante handleNuova si scatenano tutte 
    //le azioni. dovro inizializzare le variabili al click di "Nuova Partita"
    void handleNuova(ActionEvent event) {

    	model.newGame();
    	
    	//ora devo:
    	//disabilitare il button Nuova Partita
    	//abilitare la hBox
    	//aggiungere tentativi 1/7
    	
    	btnNuova.setDisable(true);
    	boxDiGioco.setDisable(false);
    	txtCurrent.setText(String.format("%d", model.getTentativi()));
    	txtMax.setText(String.format("%d", model.getTMAX()));
    	txtLog.clear();
    	txtLog.setText(String.format("indovina numero da %d e %d\n", 1, model.getNMAX()));
    }

    @FXML
    void handleProva(ActionEvent event) {
    	
    	String numS = txtTentativo.getText();
    	int num = Integer.parseInt(numS);
    	

    	if(numS.isEmpty()) {
    		txtLog.appendText("Devi inserire un numero! \n");
    		txtTentativo.clear();
    		return;
    	}
    		
    	try{
    		if(!model.valoreValido(num)) { //la visibilta del metodo ValoreValido è stata tolta
        		txtLog.appendText("Numero non riconosciuto! Inserisci un numero compreso tra 0 e 100\n ");
        		txtTentativo.clear();
        		return;
        		}
    		
    		int risultato = model.tentativo(num);//valore inserito
        	txtCurrent.setText(String.format("%d", model.getTentativi()));

    		
    		if(risultato < 0) {
    			txtLog.appendText("Il numero inserito ("+ num + ") è minore. Ritenta!\n");
            	txtTentativo.clear();
        		}
    		else if( risultato == 0) {
            	txtLog.appendText("Numero indovinato. Hai vinto!\n");
            	txtTentativo.clear();
            	// dobbiamo disabilitare l'area di gioco (seconda hBox)
            	//e cominciare una nuova partita.
            	btnNuova.setDisable(false);
                boxDiGioco.setDisable(true);
            	}
            else if(risultato > 0) {
            		txtLog.appendText("Il numero inserito (" + num + ") è maggiore. Ritenta!\n");            		
            		txtTentativo.clear();
            		}
    		
    		
    		
    		if(!model.isInGame()) {
    			if(risultato != model.getNumSegreto())
    				txtLog.appendText("Hai perso! Il numero da indovinare era " + model.getNumSegreto() + "\n");
    			txtTentativo.clear();
            	btnNuova.setDisable(false);
            	boxDiGioco.setDisable(true);	
    		}
            
        	
    	}catch(NumberFormatException e) {
    		txtLog.appendText("Devi inserire un numero! \n");
    		txtTentativo.clear();
    		
    	}
    		
    	
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtCurrent != null : "fx:id=\"txtCurrent\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert boxDiGioco != null : "fx:id=\"boxDiGioco\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtLog != null : "fx:id=\"txtLog\" was not injected: check your FXML file 'IndoNumero.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}

	
}
