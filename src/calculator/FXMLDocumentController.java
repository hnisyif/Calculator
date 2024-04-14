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
import javafx.scene.control.ScrollBar;

/**
 *
 * @author weig4542
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField monkey;
    @FXML
    private ScrollBar ape;
    
    private void changeDisplay(){
        Preparation.formatDisplay();
        if (Preparation.getDisplay().length() > 14){
            ape.setMax(Preparation.getDisplay().length() - 14);
            ape.setVisible(true);
        } else {
            ape.setMax(0);
            ape.setVisible(false);
        }
        if (ape.getValue() > ape.getMax()){
            ape.setValue(ape.getMax());
        }
        monkey.setText(Preparation.getDisplay().substring((int) ape.getValue()));
    }
    
    @FXML
    private void addZero(ActionEvent e) {
        Preparation.addToBareBones('0');
        changeDisplay();
    }
    
    @FXML
    private void addOne(ActionEvent e) {
        Preparation.addToBareBones('1');
        changeDisplay();
    }
    
    @FXML
    private void addTwo(ActionEvent e) {
        Preparation.addToBareBones('2');
        changeDisplay();
    }
    
    @FXML
    private void addThree(ActionEvent e) {
        Preparation.addToBareBones('3');
        changeDisplay();
    }
    
    @FXML
    private void addFour(ActionEvent e) {
        Preparation.addToBareBones('4');
        changeDisplay();
    }
    
    @FXML
    private void addFive(ActionEvent e) {
        Preparation.addToBareBones('5');
        changeDisplay();
    }
    
    @FXML
    private void addSix(ActionEvent e) {
        Preparation.addToBareBones('6');
        changeDisplay();
    }
    
    @FXML
    private void addSeven(ActionEvent e) {
        Preparation.addToBareBones('7');
        changeDisplay();
    }
    
    @FXML
    private void addEight(ActionEvent e) {
        Preparation.addToBareBones('8');
        changeDisplay();
    }
    
    @FXML
    private void addNine(ActionEvent e) {
        Preparation.addToBareBones('9');
        changeDisplay();
    }
    
    @FXML
    private void addPlus(ActionEvent e) {
        Preparation.addToBareBones('+');
        changeDisplay();
    }
    
    @FXML
    private void addMinus(ActionEvent e) {
        Preparation.addToBareBones('–');
        changeDisplay();
    }
    
    @FXML
    private void addMultiply(ActionEvent e) {
        Preparation.addToBareBones('*');
        changeDisplay();
    }
    
    @FXML
    private void addDivide(ActionEvent e) {
        Preparation.addToBareBones('/');
        changeDisplay();
    }
    
    @FXML
    private void addBracket(ActionEvent e) {
        Preparation.addToBareBones('(');
        changeDisplay();
    }
    
    @FXML
    private void addEndBracket(ActionEvent e) {
        Preparation.addToBareBones(')');
        changeDisplay();
    }
    
    @FXML
    private void addSin(ActionEvent e) {
        Preparation.addToBareBones('S');
        changeDisplay();
    }
    
    @FXML
    private void addCos(ActionEvent e) {
        Preparation.addToBareBones('C');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addTan(ActionEvent e) {
        Preparation.addToBareBones('T');
        changeDisplay();
    }
    
    @FXML
    private void addASin(ActionEvent e) {
        Preparation.addToBareBones('s');
        changeDisplay();
    }
    
    @FXML
    private void addACos(ActionEvent e) {
        Preparation.addToBareBones('c');
        changeDisplay();
    }
    
    @FXML
    private void addATan(ActionEvent e) {
        Preparation.addToBareBones('t');
        changeDisplay();
    }
    
    @FXML
    private void addPi(ActionEvent e) {
        Preparation.addToBareBones('π');
        changeDisplay();
    }
    
    @FXML
    private void addLog(ActionEvent e) {
        Preparation.addToBareBones('l');
        changeDisplay();
    }
    
    @FXML
    private void addPow(ActionEvent e) {
        Preparation.addToBareBones('^');
        changeDisplay();
    }
    
    @FXML
    private void addRoot(ActionEvent e) {
        Preparation.addToBareBones('√');
        changeDisplay();
    }
    
    @FXML
    private void addDecimal(ActionEvent e) {
        Preparation.addToBareBones('.');
        changeDisplay();
    }
    
    @FXML
    private void delete(ActionEvent e) {
        Preparation.removeFromBareBones();
        changeDisplay();
    }
    
    @FXML
    private void clear(ActionEvent e) {
        Preparation.reset();
        changeDisplay();
    }
    
    @FXML
    private void rightButton(ActionEvent e) {
        Preparation.moveCursorRight();
        changeDisplay();
    }
    
    @FXML
    private void leftButton(ActionEvent e) {
        Preparation.moveCursorLeft();
        changeDisplay();
    }
    
    @FXML
    private void equalsButton(ActionEvent e) {
        Preparation.equals();
        if (Preparation.getDisplay().length() > 14){
            ape.setMax(Preparation.getDisplay().length() - 14);
            ape.setVisible(true);
        } else {
            ape.setMax(0);
            ape.setVisible(false);
        }
        if (ape.getValue() > ape.getMax()){
            ape.setValue(ape.getMax());
        }
        monkey.setText(Preparation.getDisplay().substring((int) ape.getValue()));
    }
    
    @FXML
    private void end(ActionEvent e) {
        System.exit(0);
    }
    
    @FXML
    private void addNegative(ActionEvent e) {
        Preparation.addToBareBones('-');
        changeDisplay();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ape.setMin(0);
        ape.setMax(monkey.getText().length());
        ape.setVisible(false);
        ape.setVisibleAmount(1);
        
        ape.valueProperty().addListener((obs, oldVal, newVal)->{
            monkey.setText(Preparation.getDisplay().substring((int) ape.getValue()));
        });
    }    
    
}
