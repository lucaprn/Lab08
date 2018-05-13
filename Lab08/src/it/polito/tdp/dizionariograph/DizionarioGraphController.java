/**
 * Sample Skeleton for 'DizionarioGraph.fxml' Controller Class
 */

package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {
	
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtNumero"
    private TextField txtNumero; // Value injected by FXMLLoader

    @FXML // fx:id="txtParola"
    private TextField txtParola; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void OnGrafo(ActionEvent event) {
    	try {
    	this.model.createGraph(Integer.parseInt(this.txtNumero.getText()));
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    	}
    	txtResult.appendText("\nGrafo generato\n");
    }

    @FXML
    void OnMax(ActionEvent event) {
    	txtResult.appendText(model.gradoMax());
    }

    @FXML
    void OnReset(ActionEvent event) {
    	this.txtNumero.clear();
    	this.txtParola.clear();
    	this.txtResult.clear();
    }

    @FXML
    void OnVicini(ActionEvent event) {
    	
    	try {
    		String parola = this.txtParola.getText();
    		int numero = Integer.parseInt(this.txtNumero.getText());
    		if(parola.length()!=numero || !model.presente(parola)) {
    			txtResult.appendText("\nERRORE!!\n");
    		}else {
    			
    			for(String s : model.displayNeighbours(parola) ) {
    				txtResult.appendText(s+"\n");
    			}
    		}
    	}catch(NumberFormatException e ) {
    		e.printStackTrace();
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtNumero != null : "fx:id=\"txtNumero\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model=model;
    }
}
