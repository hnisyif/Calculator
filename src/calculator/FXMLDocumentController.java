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
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addOne(ActionEvent e) {
        Preparation.addToBareBones('1');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addTwo(ActionEvent e) {
        Preparation.addToBareBones('2');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addThree(ActionEvent e) {
        Preparation.addToBareBones('3');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addFour(ActionEvent e) {
        Preparation.addToBareBones('4');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addFive(ActionEvent e) {
        Preparation.addToBareBones('5');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addSix(ActionEvent e) {
        Preparation.addToBareBones('6');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addSeven(ActionEvent e) {
        Preparation.addToBareBones('7');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addEight(ActionEvent e) {
        Preparation.addToBareBones('8');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addNine(ActionEvent e) {
        Preparation.addToBareBones('9');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addPlus(ActionEvent e) {
        Preparation.addToBareBones('+');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addMinus(ActionEvent e) {
        Preparation.addToBareBones('–');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addMultiply(ActionEvent e) {
        Preparation.addToBareBones('*');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addDivide(ActionEvent e) {
        Preparation.addToBareBones('/');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addBracket(ActionEvent e) {
        Preparation.addToBareBones('(');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addEndBracket(ActionEvent e) {
        Preparation.addToBareBones(')');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addSin(ActionEvent e) {
        Preparation.addToBareBones('S');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
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
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addASin(ActionEvent e) {
        Preparation.addToBareBones('s');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addACos(ActionEvent e) {
        Preparation.addToBareBones('c');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addATan(ActionEvent e) {
        Preparation.addToBareBones('t');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addPi(ActionEvent e) {
        Preparation.addToBareBones('π');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addLog(ActionEvent e) {
        Preparation.addToBareBones('l');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addPow(ActionEvent e) {
        Preparation.addToBareBones('^');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addRoot(ActionEvent e) {
        Preparation.addToBareBones('√');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void addDecimal(ActionEvent e) {
        Preparation.addToBareBones('.');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void delete(ActionEvent e) {
        Preparation.removeFromBareBones();
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void clear(ActionEvent e) {
        Preparation.reset();
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void rightButton(ActionEvent e) {
        Preparation.moveCursorRight();
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void leftButton(ActionEvent e) {
        Preparation.moveCursorLeft();
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void equalsButton(ActionEvent e) {
        Preparation.equals();
        monkey.setText(Preparation.getDisplay());
    }
    
    @FXML
    private void end(ActionEvent e) {
        System.exit(0);
    }
    
    @FXML
    private void addNegative(ActionEvent e) {
        Preparation.addToBareBones('-');
        Preparation.formatDisplay();
        monkey.setText(Preparation.getDisplay());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
