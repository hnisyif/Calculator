package calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;

/**
 *
 * @author weig4542
 * @author abrag1616
 */
public class Calculator extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Variables
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        FXMLDocumentController controller = loader.getController();
        Scene scene = new Scene(root);
        Image icon = new Image("Calculator.png");

        //Check if a key is pressed
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                //Check which key was pressed and act accordingly
                switch (event.getCode()) {
                    case DIGIT1:
                        controller.addOne(new ActionEvent());
                        break;
                    case DIGIT2:
                        controller.addTwo(new ActionEvent());
                        break;
                    case DIGIT3:
                        controller.addThree(new ActionEvent());
                        break;
                    case DIGIT4:
                        controller.addFour(new ActionEvent());
                        break;
                    case DIGIT5:
                        controller.addFive(new ActionEvent());
                        break;
                    case DIGIT6:
                        controller.addSix(new ActionEvent());
                        break;
                    case DIGIT7:
                        controller.addSeven(new ActionEvent());
                        break;
                    case DIGIT8:
                        controller.addEight(new ActionEvent());
                        break;
                    case DIGIT9:
                        controller.addNine(new ActionEvent());
                        break;
                    case DIGIT0:
                        controller.addZero(new ActionEvent());
                        break;
                    case PLUS:
                        controller.addPlus(new ActionEvent());
                        break;
                    case MINUS:
                        controller.addMinus(new ActionEvent());
                        break;
                    case ASTERISK:
                        controller.addMultiply(new ActionEvent());
                        break;
                    case X:
                        controller.addMultiply(new ActionEvent());
                        break;
                    case SLASH:
                        controller.addDivide(new ActionEvent());
                        break;
                    case EQUALS:
                        controller.equalsButton(new ActionEvent());
                        break;
                    case ENTER:
                        controller.equalsButton(new ActionEvent());
                        break;
                    case LEFT_PARENTHESIS:
                        controller.addBracket(new ActionEvent());
                        break;
                    case RIGHT_PARENTHESIS:
                        controller.addEndBracket(new ActionEvent());
                        break;
                    case LEFT:
                        controller.leftButton(new ActionEvent());
                        break;
                    case RIGHT:
                        controller.rightButton(new ActionEvent());
                        break;
                    case BACK_SPACE:
                        controller.delete(new ActionEvent());
                        break;
                    case CIRCUMFLEX:
                        controller.addPow(new ActionEvent());
                        break;
                    case PERIOD:
                        controller.addDecimal(new ActionEvent());
                        break;
                    default:
                }
            }
        });

        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        //Initialize
        stage.setTitle("Calculator");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
