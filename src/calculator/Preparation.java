/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author weig4542
 */
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Preparation {

    private static String bareBones = "";
    private static String display = "";
    private static String equation = "";

    private static int cursor = 0;

    public static void moveCursorLeft() {
        if (cursor > 0) {
            cursor--;
        }
    }

    public static void moveCursorRight() {
        if (cursor < bareBones.length()) {
            cursor++;
        }
    }

    public static void addToBareBones(char character) {
        bareBones = bareBones.substring(0, cursor) + character + bareBones.substring(cursor, bareBones.length());
        cursor++;
    }

    public static void removeFromBareBones() {
        if (bareBones.length() > 0 && cursor > 0) {
            bareBones = bareBones.substring(0, cursor - 1) + bareBones.substring(cursor, bareBones.length());
            cursor--;
        }
        if (bareBones.length() == 0) {
            bareBones = "";
        }
    }

    public static String getDisplay() {
        return display;
    }

    public static void formatDisplay() {
        display = "";
        for (int i = 0; i < bareBones.length(); i++) {
            if (i == cursor) {
                display += '|';
            }

            if (bareBones.charAt(i) == '+' || bareBones.charAt(i) == '–') {
                display += " " + bareBones.charAt(i) + " ";
                continue;
            }
            if (bareBones.charAt(i) == '/'){
                display += " ÷ ";
                continue;
            }
            if (bareBones.charAt(i) == '*'){
                display += " x ";
                continue;
            }
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
            if (bareBones.charAt(i) == '-') {
                display += "-";
                continue;
            }
            display += bareBones.charAt(i);
        }
        if (cursor == bareBones.length()) {
            display += '|';
        }
    }

    private static void formatEquation() {
        equation = "";
        boolean negError = false;
        for (int i = 0; i < bareBones.length(); i++) {
            if (bareBones.charAt(i) == '+' || bareBones.charAt(i) == '–' || bareBones.charAt(i) == '*' || bareBones.charAt(i) == '/' || bareBones.charAt(i) == '^' || bareBones.charAt(i) == '√') {
                equation += " " + bareBones.charAt(i) + " ";
                continue;
            }

            if (bareBones.charAt(i) == 'π') {
                if (i > 0 && (digits(bareBones.charAt(i - 1)) == true || bareBones.charAt(i - 1) == 'π')) {
                    equation += " * " + bareBones.charAt(i);
                    continue;
                }
                if (i < bareBones.length() - 1 && (digits(bareBones.charAt(i + 1)) == true || bareBones.charAt(i + 1) == 'π')) {
                    equation += bareBones.charAt(i) + " * ";
                    continue;
                }
            }

            if (specialChar(bareBones.charAt(i))) {
                if (i > 0 && (digits(bareBones.charAt(i - 1)) == true || bareBones.charAt(i - 1) == 'π' || bareBones.charAt(i - 1) == ')')) {
                    if (bareBones.charAt(i) == '-') {
                        negError = true;
                    }
                    equation += " * " + bareBones.charAt(i);
                    continue;
                }
            }

            if (bareBones.charAt(i) == ')') {
                if (i < bareBones.length() - 1 && (digits(bareBones.charAt(i + 1)) == true || bareBones.charAt(i + 1) == 'π')) {
                    equation += bareBones.charAt(i) + " * ";
                    continue;
                }
            }

            equation += bareBones.charAt(i);
        }
        if (bareBones == "") {
            equation = "0";
        }
        if (negError == true) {
            equation += 'E';
        }
    }

    private static boolean specialChar(char character) {
        //S, C, and T are sin cos and tan
        //s, c, and t are the inverse
        //l is log
        //- means it's negative
        if (character == 'S' || character == 'C' || character == 'T' || character == '(' || character == 's' || character == 'c' || character == 't' || character == 'l' || character == '-') {
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

    private static boolean validEquation() {
        int brackets = 0;
        int digits = 0;
        int dots = 0;

        if (equation.charAt(equation.length() - 1) == 'E') {
            display = "Error 0: Negtive symbol after a value.";
            return false;
        }

        if (equation.charAt(0) == '.' || equation.charAt(equation.length() - 1) == '.') {
            display = "Error 1: Decimal at beginning or end of equation";
            return false;
        }
        if (equation.charAt(0) == ' ' || equation.charAt(equation.length() - 1) == ' ') {
            display = "Error 2: Operation at beginning or end of equation";
            return false;
        }
        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == ' ') {
                if (equation.charAt(i + 1) == ' ') {
                    display = "Error 3: Missing number between operations";
                    return false;
                }
                dots = 0;
            }

            if (equation.charAt(i) == '-') {
                if (equation.charAt(i + 1) == '-') {
                    display = "Error 4: More than one - before a number";
                    return false;
                }
            }

            if (equation.charAt(i) == '.') {
                if (digits(equation.charAt(i + 1)) == false || digits(equation.charAt(i - 1)) == false) {
                    display = "Error 5: No number before or after decimal";
                    return false;
                }
                dots++;
            }

            if (specialChar(equation.charAt(i)) == true) {
                if (i < equation.length() - 1 && (equation.charAt(i + 1) == ' ' || equation.charAt(i + 1) == ')')) {
                    display = "Error 6: No number, (, or trig func after (, trig func, or -";
                    return false;
                }
                if (i == equation.length() - 1) {
                    display = "Error 6: No number, (, or trig func after (, trig func, or -";
                    return false;
                }
            }

            if (equation.charAt(i) == '(') {
                brackets++;
            }

            if (equation.charAt(i) == ')') {
                if (i > 0 && equation.charAt(i - 1) == ' ') {
                    display = "Error 7: Operation before )";
                    return false;
                }
                brackets--;
            }

            if (brackets < 0) {
                display = "Error 8: ) is missing proceeding (";
                return false;
            }

            if (dots > 1) {
                display = "Error 9: Two or more decimals in one number";
                return false;
            }

            if (digits(equation.charAt(i)) == true || equation.charAt(i) == 'π') {
                digits++;
            }
        }

        if (brackets != 0) {
            display = "Error 10: ( is missing following )";
            return false;
        }

        if (digits == 0) {
            display = "Error 11: No numbers";
            return false;
        }

        return true;
    }

    public static void reset() {
        bareBones = "";
        equation = "";
        display = "";
        cursor = 0;
    }

    public static void equals() {
        formatEquation();
        
        if (validEquation() == false) {
            return;
        }
        
        equation = Calculation.calculate(equation);
        
        if (equation.charAt(0) == 'π'){
            equation = String.valueOf(Math.PI);
        }
        
        equation = String.valueOf(Double.parseDouble(equation));
        
        if (!equation.equals("Infinity") && !equation.equals("NaN")) {
            BigDecimal rounded = new BigDecimal(equation).setScale(14, RoundingMode.HALF_UP);
            equation = String.valueOf(rounded);
        }

        if (equation.equals("Infinity") || equation.equals("NaN")) {
            if (equation.equals("Infinity")) {
                display = "Defined by Computer as Infinity";
            }
            if (equation.equals("NaN")) {
                display = "Not a Number";
            }
            bareBones = "";
        } else {
            display = "= " + equation;
            for (int i = 0; i < equation.length(); i++) {
                if (equation.charAt(i) == 'E') {
                    equation = '(' + equation.substring(0, i) + "*10^" + equation.substring(i + 1, equation.length()) + ')';
                    break;
                }
            }
            bareBones = equation;
        }
        equation = "";
        cursor = bareBones.length();
    }
}