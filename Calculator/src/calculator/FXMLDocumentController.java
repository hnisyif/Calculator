/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *
 * @author weig4542
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField monkey;
    
    @FXML
    private void addZero(ActionEvent e) {
        Preparation.addToBareBones('0');
        System.out.println("Added 0");
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
