package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author weig4542
 */
public class Preparation {

    private static String bareBones = "";
    private static String display = "";
    private static String equation = "";

    private static int cursor = 0;
    private static double answer = 0;

    //private methods
    //These methods check what category certain characters fall into
    private static boolean specialChar(char character) {
        //S, C, and T are sin cos and tan
        //s, c, and t are the inverse
        //l is log
        //- means it's negative
        if (character == 'S' || character == 'C' || character == 'T' || character == '(' || character == 's' || character == 'c' || character == 't' || character == 'l' || character == '-' || character == '—') {
            return true;
        } else {
            return false;
        }
    }

    private static boolean varChar(char character) {
        if (character == 'π' || character == 'A') {
            return true;
        } else {
            return false;
        }
    }

    private static boolean digits(char character) {
        if ((int) character > 47 && (int) character < 58) {
            return true;
        } else {
            return false;
        }
    }

    //Format the equation to be checked for errors later and calculated in the Calculation class
    private static void formatEquation() {
        equation = "";
        boolean negError = false;
        for (int i = 0; i < bareBones.length(); i++) {
            //All operations are surroudned by spaces
            if (bareBones.charAt(i) == '+' || bareBones.charAt(i) == '–' || bareBones.charAt(i) == '*' || bareBones.charAt(i) == '/' || bareBones.charAt(i) == '^' || bareBones.charAt(i) == '√') {
                equation += " " + bareBones.charAt(i) + " ";
                continue;
            }

            //If it's a variable like PI or previous answer
            if (varChar(bareBones.charAt(i)) == true) {
                //If a digit or variable proceeds it, add a multiplication sign
                if (i > 0 && (digits(bareBones.charAt(i - 1)) == true || varChar(bareBones.charAt(i - 1)) == true)) {
                    equation += " * ";
                }
                //If a digit follows add a multiplication sign. Dont check for variables since if there is one, it will add a multiplication sign already
                equation += bareBones.charAt(i);
                if (i < bareBones.length() - 1 && digits(bareBones.charAt(i + 1)) == true) {
                    equation += " * ";
                }
                continue;
            }

            //A special character must be follow by another special character or soemthign with a numerical value like tan, log, -, or (
            if (specialChar(bareBones.charAt(i)) == true) {
                if (i > 0 && (digits(bareBones.charAt(i - 1)) == true || varChar(bareBones.charAt(i - 1)) == true || bareBones.charAt(i - 1) == ')')) {
                    if (bareBones.charAt(i) == '-' || bareBones.charAt(i) == '—') {
                        negError = true;
                    }
                    if (bareBones.charAt(i) == '-') {
                        //All negative symbols should be em dashes to indicate it hasn't been in a bracket yet. If it's not an me dash, make it one.
                        equation += " * " + '—';
                        continue;
                    }
                    equation += " * " + bareBones.charAt(i);
                    continue;
                }
                if (bareBones.charAt(i) == '-') {
                    equation += '—';
                    continue;
                }
            }

            if (bareBones.charAt(i) == ')') {
                //If something with a value is after ) then add a multiplication sign
                if (i < bareBones.length() - 1 && (digits(bareBones.charAt(i + 1)) == true || varChar(bareBones.charAt(i + 1)) == true)) {
                    equation += bareBones.charAt(i) + " * ";
                    continue;
                }
            }

            equation += bareBones.charAt(i);
        }

        //If bare bones is empty then just make it 0
        if (bareBones == "") {
            equation = "0";
        }
        //If there's an error involving negatives then add this badd boy to the end of the equation
        if (negError == true) {
            equation += 'X';
        }
    }

    //Check if the equation is valid
    private static boolean validEquation() {
        int brackets = 0;
        int digits = 0;
        int dots = 0;

        //If this symbol is at the end of the equation, that means the formatting found an error with negatives
        if (equation.charAt(equation.length() - 1) == 'X') {
            display = "Error 0: Negtive symbol after a value.";
            return false;
        }

        //If a decimal is at the beginning or end then you've done something wrong
        if (equation.charAt(0) == '.' || equation.charAt(equation.length() - 1) == '.') {
            display = "Error 1: Decimal at beginning or end of equation";
            return false;
        }
        //If there's a space at the beginning or end then there's an operation problem
        if (equation.charAt(0) == ' ' || equation.charAt(equation.length() - 1) == ' ') {
            display = "Error 2: Operation at beginning or end of equation";
            return false;
        }

        for (int i = 0; i < equation.length(); i++) {
            //If there's two spaces in arow then there's two operations in a row
            if (equation.charAt(i) == ' ') {
                if (equation.charAt(i + 1) == ' ') {
                    display = "Error 3: Missing number between operations";
                    return false;
                }
                //To check if there are more than one decimals in one number. Reset the dots integer when it encounters a space(meaning it's a new operation).
                dots = 0;
            }

            //If you find a negative sign than another one after, that's bad
            if (equation.charAt(i) == '-' || equation.charAt(i) == '—') {
                if (i + 1 == equation.length() || equation.charAt(i + 1) == '-' || equation.charAt(i + 1) == '—') {
                    display = "Error 4: More than one - before a number or you only have -";
                    return false;
                }
            }

            //There's a dot
            if (equation.charAt(i) == '.') {
                //If there's no number before or after, that's bad
                if (digits(equation.charAt(i + 1)) == false || digits(equation.charAt(i - 1)) == false) {
                    display = "Error 5: No number before or after decimal";
                    return false;
                }
                //Add to dots integer
                dots++;
            }

            //Special characters
            if (specialChar(equation.charAt(i)) == true) {
                //Check if a proper thing is before or after these guys
                //I'm really tired
                if (i < equation.length() - 1 && (equation.charAt(i + 1) == ' ' || equation.charAt(i + 1) == ')')) {
                    display = "Error 6: No number, (, or trig func after (, trig func, or -";
                    return false;
                }
                if (i == equation.length() - 1) {
                    display = "Error 6: No number, (, or trig func after (, trig func, or -";
                    return false;
                }
            }

            //Keep track of how many brackets there be
            if (equation.charAt(i) == '(') {
                brackets++;
            }

            //Keep track of ) and check if an improper thing is before it
            if (equation.charAt(i) == ')') {
                if (i > 0 && equation.charAt(i - 1) == ' ') {
                    display = "Error 7: Operation before )";
                    return false;
                }
                brackets--;
            }

            //If the brackets int is ever greater than 0 then ) came before a (
            if (brackets < 0) {
                display = "Error 8: ) is missing proceeding (";
                return false;
            }

            //If the dots integer is greater 1 than that means it encoutnered two dots before encountering another operation so there's more than one decimal in the same number
            if (dots > 1) {
                display = "Error 9: Two or more decimals in one number";
                return false;
            }

            //Digits and variables count as digits
            if (digits(equation.charAt(i)) == true || varChar(equation.charAt(i)) == true) {
                digits++;
            }
        }

        //If the brackets intger isn' 0, there's an uneven amount of brackets which is bad
        if (brackets != 0) {
            display = "Error 10: ( is missing following )";
            return false;
        }

        //no numbers
        if (digits == 0) {
            display = "Error 11: No numbers";
            return false;
        }

        return true;
    }

    //End of Private Methods
    //Public methods
    //Calculation needs this to know what the last answer was when it encounters the A symbol
    public static double getAnswer() {
        return answer;
    }

    //These methods need to be called by the controller to change and read it's global variables.
    public static void moveCursorLeft() {
        //Check if cursor can move left and move it if it can
        if (cursor > 0) {
            cursor--;
        }
    }

    public static void moveCursorRight() {
        //Check if the cursor can move right and move it if it can
        if (cursor < bareBones.length()) {
            cursor++;
        }
    }

    //Add a character to the barebones equation at the cusor placement
    public static void addToBareBones(char character) {
        bareBones = bareBones.substring(0, cursor) + character + bareBones.substring(cursor, bareBones.length());
        cursor++;
    }

    //Remove a character from the barebones equation at the cusor placement
    public static void removeFromBareBones() {
        if (bareBones.length() > 0 && cursor > 0) {
            bareBones = bareBones.substring(0, cursor - 1) + bareBones.substring(cursor, bareBones.length());
            cursor--;
        }
        if (bareBones.length() == 0) {
            bareBones = "";
        }
    }

    public static void reset() {
        //All the variables at the start
        bareBones = "";
        equation = "";
        display = "";
        cursor = 0;
        answer = 0;
    }

    //Formatting the display string for users
    public static void formatDisplay() {
        display = "";
        for (int i = 0; i < bareBones.length(); i++) {
            //Place the cursor at the index
            if (i == cursor) {
                display += '|';
            }

            //Operations have spaces around them
            if (bareBones.charAt(i) == '+' || bareBones.charAt(i) == '–') {
                display += " " + bareBones.charAt(i) + " ";
                continue;
            }
            if (bareBones.charAt(i) == '/') {
                display += " ÷ ";
                continue;
            }
            if (bareBones.charAt(i) == '*') {
                display += " x ";
                continue;
            }
            //Trig funcs do not
            if (bareBones.charAt(i) == 'S') {
                display += "sin";
                continue;
            }
            if (bareBones.charAt(i) == 'C') {
                display += "cos";
                continue;
            }
            if (bareBones.charAt(i) == 'T') {
                display += "tan";
                continue;
            }
            if (bareBones.charAt(i) == 's') {
                display += "asin";
                continue;
            }
            if (bareBones.charAt(i) == 'c') {
                display += "acos";
                continue;
            }
            if (bareBones.charAt(i) == 't') {
                display += "atan";
                continue;
            }
            if (bareBones.charAt(i) == 'l') {
                display += "log";
                continue;
            }
            //Neither do negative symbols
            if (bareBones.charAt(i) == '-') {
                display += "-";
                continue;
            }
            if (bareBones.charAt(i) == '—') {
                display += "-";
                continue;
            }
            //Nor variables
            if (bareBones.charAt(i) == 'A') {
                display += "ANS";
                continue;
            }
            display += bareBones.charAt(i);
        }
        //Place the cusor at the last place if it's at the length of bare bones
        if (cursor == bareBones.length()) {
            display += '|';
        }
    }

    public static String getDisplay() {
        return display;
    }

    //Called by controller to find value
    public static void equals() {
        //Format equation
        formatEquation();

        //Check if equation makes sense
        if (validEquation() == false) {
            return;
        }

        //Calculate the equation
        equation = Calculation.calculate(equation);

        //Add the answer to the answer variable
        answer = Calculation.toDouble(equation, 0, equation.length());

        //Answer should now have a number that makes sense so use it as the string
        equation = String.valueOf(answer);

        //Round if it's not a special case like Infinity, -Infinity, or NaN
        if (!equation.equals("Infinity") && !equation.equals("-Infinity") && !equation.equals("NaN")) {
            //15 decimal places is the amount double can hold accurately. I decided to use the 15th and 14th decimal place as guard digits so I'm rounding to 13 decimal places.
            BigDecimal rounded = new BigDecimal(equation).setScale(13, RoundingMode.HALF_UP);
            equation = String.valueOf(rounded);
        }

        //Tell the user if their answer is a special case like these, otherwise just give the equation as the answer in display
        if (equation.equals("Infinity") || equation.equals("NaN") || equation.equals("-Infinity")) {
            if (equation.equals("Infinity")) {
                display = "Defined by Computer as Infinity";
            }
            if (equation.equals("-Infinity")) {
                display = "Defined by Computer as -Infinity";
            }
            if (equation.equals("NaN")) {
                display = "Not a Number";
            }
        } else {
            display = "= " + equation;
        }
        //Barebones is made of only the answer variable
        bareBones = "A";
        //Equation is emptied
        equation = "";
        //Cursor is the length of barebones, which is now always 1 because it's now always just A
        cursor = bareBones.length();
    }

    //End of public methods
}
