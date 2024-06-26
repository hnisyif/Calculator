package calculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollBar;
import javafx.scene.text.Font;

/**
 *
 * @author hussb3964
 * @author weig4542
 */
public class FXMLDocumentController implements Initializable {

    //There can only be 18 characters on screen at a time
    private final int MAXIMUM_TEXT_LENGTH = 18;

    @FXML
    private TextField monkey;
    @FXML
    private ScrollBar ape;
    @FXML
    private Label lemur;

    private static int drg = 1;

    public static int getDRG() {
        return drg;
    }

    private void changeDisplay() {
        //Fromat the display String based on the barebones string
        Preparation.formatDisplay();
        //Check whether the string might need to be substringed to onl fit certain parts at a time on screen.
        if (Preparation.getDisplay().length() > MAXIMUM_TEXT_LENGTH) {
            //If there's more than the max length find how many substrings of max length characters fit in the string and set that to the max scroll amount.
            ape.setMax(Preparation.getDisplay().length() - MAXIMUM_TEXT_LENGTH);
            //Make the length of the scrolls thumb the size of the bar / the amount of 14 character substrings in the string
            ape.setVisibleAmount(ape.getMax() / (Preparation.getDisplay().length() - (MAXIMUM_TEXT_LENGTH - 1)));
            ape.setVisible(true);
        } else {
            ape.setMax(0);
            //If the scrolll bar is uneeded then don't show it
            ape.setVisible(false);
        }
        if (ape.getValue() > ape.getMax()) {
            //If the value of where the scroll thumb is is greater than the max it can be. Set it to the max.
            ape.setValue(ape.getMax());
        }
        //Susbtring the value of where the thumb is on the scroll bar to 14 after or the length of the string depending on which is smaller.
        monkey.setText(Preparation.getDisplay().substring((int) ape.getValue(), Math.min((int) ape.getValue() + MAXIMUM_TEXT_LENGTH, Preparation.getDisplay().length())));
    }

    public void addScience() {
        //Add this character to barebones
        Preparation.addToBareBones('*');
        Preparation.addToBareBones('1');
        Preparation.addToBareBones('0');
        Preparation.addToBareBones('^');
        //Update the display for the new bare bones equation
        changeDisplay();
    }

    public void addZero(ActionEvent e) {
        //Add this character to barebones
        Preparation.addToBareBones('0');
        //Update the display for the new bare bones equation
        changeDisplay();
    }

    public void addOne(ActionEvent e) {
        Preparation.addToBareBones('1');
        changeDisplay();
    }

    public void addTwo(ActionEvent e) {
        Preparation.addToBareBones('2');
        changeDisplay();
    }

    public void addThree(ActionEvent e) {
        Preparation.addToBareBones('3');
        changeDisplay();
    }

    public void addFour(ActionEvent e) {
        Preparation.addToBareBones('4');
        changeDisplay();
    }

    public void addFive(ActionEvent e) {
        Preparation.addToBareBones('5');
        changeDisplay();
    }

    public void addSix(ActionEvent e) {
        Preparation.addToBareBones('6');
        changeDisplay();
    }

    public void addSeven(ActionEvent e) {
        Preparation.addToBareBones('7');
        changeDisplay();
    }

    public void addEight(ActionEvent e) {
        Preparation.addToBareBones('8');
        changeDisplay();
    }

    public void addNine(ActionEvent e) {
        Preparation.addToBareBones('9');
        changeDisplay();
    }

    public void addPlus(ActionEvent e) {
        Preparation.addToBareBones('+');
        changeDisplay();
    }

    public void addMinus(ActionEvent e) {
        Preparation.addToBareBones('–');
        changeDisplay();
    }

    public void addMultiply(ActionEvent e) {
        Preparation.addToBareBones('*');
        changeDisplay();
    }

    @FXML
    public void addDivide(ActionEvent e) {
        Preparation.addToBareBones('/');
        changeDisplay();
    }

    public void addBracket(ActionEvent e) {
        Preparation.addToBareBones('(');
        changeDisplay();
    }

    public void addEndBracket(ActionEvent e) {
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

    public void addPow(ActionEvent e) {
        Preparation.addToBareBones('^');
        changeDisplay();
    }

    @FXML
    private void addRoot(ActionEvent e) {
        Preparation.addToBareBones('√');
        changeDisplay();
    }

    public void addDecimal(ActionEvent e) {
        Preparation.addToBareBones('.');
        changeDisplay();
    }

    @FXML
    private void addNegative(ActionEvent e) {
        Preparation.addToBareBones('-');
        changeDisplay();
    }

    public void delete(ActionEvent e) {
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

    public void rightButton(ActionEvent e) {
        //Move the cursor right
        Preparation.moveCursorRight();
        changeDisplay();
    }

    public void leftButton(ActionEvent e) {
        //Move the cursor left
        Preparation.moveCursorLeft();
        changeDisplay();
    }

    public void equalsButton(ActionEvent e) {
        //Call the method of finding what the given equation is equal to
        Preparation.equals();
        if (Preparation.getDisplay().length() > MAXIMUM_TEXT_LENGTH) {
            ape.setMax(Preparation.getDisplay().length() - MAXIMUM_TEXT_LENGTH);
            ape.setVisibleAmount(ape.getMax() / (Preparation.getDisplay().length() - (MAXIMUM_TEXT_LENGTH - 1)));
            ape.setVisible(true);
        } else {
            ape.setMax(0);
            ape.setVisible(false);
        }
        if (ape.getValue() > ape.getMax()) {
            ape.setValue(ape.getMax());
        }
        monkey.setText(Preparation.getDisplay().substring((int) ape.getValue(), Math.min((int) ape.getValue() + MAXIMUM_TEXT_LENGTH, Preparation.getDisplay().length())));
    }

    @FXML
    private void drg(ActionEvent e) {
        //drg is degrees, radians, gradians
        //Switch to the next type
        switch (drg) {
            case 0:
                drg = 1;
                lemur.setText("Radians");
                break;
            case 1:
                drg = 2;
                lemur.setText("Gradians");
                break;
            case 2:
                drg = 0;
                lemur.setText("Degrees");
                break;
            default:
        }
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
            monkey.setText(Preparation.getDisplay().substring((int) ape.getValue(), Math.min((int) ape.getValue() + MAXIMUM_TEXT_LENGTH, Preparation.getDisplay().length())));
        });
    }

}
