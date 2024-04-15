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

    private void changeDisplay() {
        //Fromat the display String based on the barebones string
        Preparation.formatDisplay();
        //Check whether the string might need to be substringed to onl fit certain parts at a time on screen.
        if (Preparation.getDisplay().length() > 14) {
            //There can only be 14 characters on screen at a time
            //If there's more than 14 find how many substrings of 14 characters fit in the string and set that to the max scroll amount.
            ape.setMax(Preparation.getDisplay().length() - 14);
            //Make the length of the scrolls thumb the size of the bar / the amount of 14 character substrings in the string
            ape.setVisibleAmount(ape.getMax() / (Preparation.getDisplay().length() - 13));
            ape.setVisible(true);
        } else {
            ape.setMax(0);
            //If the scrolll bar is uneeded then don;t show it
            ape.setVisible(false);
        }
        if (ape.getValue() > ape.getMax()) {
            //If the value of where the scroll thumb is is greater than the max it can be. Set it to the max.
            ape.setValue(ape.getMax());
        }
        //Susbtring the value of where the thumb is on the scroll bar to 14 after or the length of the string depending on which is smaller.
        monkey.setText(Preparation.getDisplay().substring((int) ape.getValue(), Math.min((int) ape.getValue() + 14, Preparation.getDisplay().length())));
    }

    @FXML
    private void addZero(ActionEvent e) {
        //Add this character to barebones
        Preparation.addToBareBones('0');
        //Update the display for the new bare bones equation
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
        changeDisplay();
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
    private void addNegative(ActionEvent e) {
        Preparation.addToBareBones('-');
        changeDisplay();
    }

    @FXML
    private void delete(ActionEvent e) {
        //Remove a character from bare boens equation
        Preparation.removeFromBareBones();
        changeDisplay();
    }

    @FXML
    private void clear(ActionEvent e) {
        //Reset the variables to their original values
        Preparation.reset();
        changeDisplay();
    }

    @FXML
    private void rightButton(ActionEvent e) {
        //Move the cursor right
        Preparation.moveCursorRight();
        changeDisplay();
    }

    @FXML
    private void leftButton(ActionEvent e) {
        //Move the cursor left
        Preparation.moveCursorLeft();
        changeDisplay();
    }

    @FXML
    private void equalsButton(ActionEvent e) {
        //Call the method of finding what the given equation is equal to
        Preparation.equals();
        if (Preparation.getDisplay().length() > 14) {
            ape.setMax(Preparation.getDisplay().length() - 14);
            ape.setVisibleAmount(ape.getMax() / (Preparation.getDisplay().length() - 13));
            ape.setVisible(true);
        } else {
            ape.setMax(0);
            ape.setVisible(false);
        }
        if (ape.getValue() > ape.getMax()) {
            ape.setValue(ape.getMax());
        }
        monkey.setText(Preparation.getDisplay().substring((int) ape.getValue(), Math.min((int) ape.getValue() + 14, Preparation.getDisplay().length())));
    }

    @FXML
    private void end(ActionEvent e) {
        //End the program
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set the scroll bar to a minimum of 0 and a maximum of 0. The scrollbar shouldn't be visible yet.
        ape.setMin(0);
        ape.setMax(0);
        ape.setVisible(false);
        ape.setVisibleAmount(1);

        //Listen for when the scrollbar is interacted with.
        ape.valueProperty().addListener((obs, oldVal, newVal) -> {
            //When it's interacted with, change the susbtring the user sees depending on where the thumb is on the scroll bar.
            //We don't have to check to make sure the size of the String is correct since it wouldn't change from interactign with the scroll bar.
            monkey.setText(Preparation.getDisplay().substring((int) ape.getValue(), Math.min((int) ape.getValue() + 14, Preparation.getDisplay().length())));
        });
    }

}
