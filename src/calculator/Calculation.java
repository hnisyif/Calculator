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
public class Calculation {

    private static String replace(String original, int a, String middle, int b) {
        return original.substring(0, a) + middle + original.substring(b, original.length());
    }

    private static double toDouble(String equation, int a, int b) {
        if (equation.substring(a, b).equals("π")) {
            return Math.PI;
        }
        if (equation.charAt(a) == 'I') {
            return 1d / 0d;
        }
        if (equation.charAt(a) == 'N') {
            return 0d / 0d;
        }

        return Double.parseDouble(equation.substring(a, b));
    }

    private static boolean digits(char character) {
        if ((int) character > 47 && (int) character < 58) {
            return true;
        } else {
            return false;
        }
    }

    private static int lastIndex(String equation, int index) {
        if (equation.charAt(index) == 'I') {
            return index + 8;
        }
        if (equation.charAt(index) == 'N') {
            return index + 3;
        }
        for (index = index; index < equation.length(); index++) {
            if (digits(equation.charAt(index)) == false && equation.charAt(index) != '-' && equation.charAt(index) != '.' && equation.charAt(index) != 'π' && equation.charAt(index) != 'E') {
                break;
            }
        }
        return index;
    }

    private static int firstIndex(String equation, int index) {
        if (equation.charAt(index) == 'y') {
            return index - 7;
        }
        if (equation.charAt(index) == 'N') {
            return index - 2;
        }
        for (index = index; index > -1; index--) {
            if (digits(equation.charAt(index)) == false && equation.charAt(index) != '-' && equation.charAt(index) != '.' && equation.charAt(index) != 'π' && equation.charAt(index) != 'E') {
                break;
            }
        }
        return index + 1;
    }

    public static String calculate(String equation) {
        //B
        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == '(') {
                int brackets = 0;
                for (int e = i; e < equation.length(); e++) {
                    if (equation.charAt(e) == '(') {
                        brackets++;
                    }
                    if (equation.charAt(e) == ')') {
                        brackets--;
                    }
                    if (brackets == 0) {
                        equation = replace(equation, i, calculate(equation.substring(i + 1, e)), e + 1);
                        break;
                    }
                }
                i--;
            }
        }

        //E
        for (int i = equation.length() - 1; i > -1; i--) {
            int a;
            int b;
            switch (equation.charAt(i)) {
                case '^':
                    a = firstIndex(equation, i - 2);
                    b = lastIndex(equation, i + 2);
                    equation = replace(equation, a, String.valueOf(Math.pow(toDouble(equation, a, i - 1), toDouble(equation, i + 2, b))), b);
                    i = a + 1;
                    break;
                case '√':
                    a = firstIndex(equation, i - 2);
                    b = lastIndex(equation, i + 2);
                    equation = replace(equation, a, String.valueOf(Math.pow(toDouble(equation, i + 2, b), 1d / toDouble(equation, a, i - 1))), b);
                    i = a + 1;
                    break;
                case 'S':
                    b = lastIndex(equation, i + 1);
                    equation = replace(equation, i, String.valueOf(Math.sin(toDouble(equation, i + 1, b))), b);
                    i++;
                    break;
                case 'C':
                    b = lastIndex(equation, i + 1);
                    equation = replace(equation, i, String.valueOf(Math.cos(toDouble(equation, i + 1, b))), b);
                    i++;
                    break;
                case 'T':
                    b = lastIndex(equation, i + 1);
                    if (Math.tan(toDouble(equation, i + 1, b)) - Math.tan(Math.PI / 2d) == 0){
                        equation = replace(equation, i, "Infinity", b);
                    } else {
                        equation = replace(equation, i, String.valueOf(Math.tan(toDouble(equation, i + 1, b))), b);
                    }
                    i++;
                    break;
                case 's':
                    b = lastIndex(equation, i + 1);
                    equation = replace(equation, i, String.valueOf(Math.asin(toDouble(equation, i + 1, b))), b);
                    i++;
                    break;
                case 'c':
                    b = lastIndex(equation, i + 1);
                    equation = replace(equation, i, String.valueOf(Math.acos(toDouble(equation, i + 1, b))), b);
                    i++;
                    break;
                case 't':
                    if (equation.charAt(i + 1) != 'y'){
                        b = lastIndex(equation, i + 1);
                        equation = replace(equation, i, String.valueOf(Math.atan(toDouble(equation, i + 1, b))), b);
                        i++;
                    }
                    break;
                case 'l':
                    b = lastIndex(equation, i + 1);
                    equation = replace(equation, i, String.valueOf(Math.log10(toDouble(equation, i + 1, b))), b);
                    i++;
                    break;
                default:
            }
        }

        //DM
        for (int i = 0; i < equation.length(); i++) {
            int a;
            int b;
            switch (equation.charAt(i)) {
                case '/':
                    a = firstIndex(equation, i - 2);
                    b = lastIndex(equation, i + 2);
                    equation = replace(equation, a, String.valueOf(toDouble(equation, a, i - 1) / toDouble(equation, i + 2, b)), b);
                    i = a - 1;
                    break;
                case '*':
                    a = firstIndex(equation, i - 2);
                    b = lastIndex(equation, i + 2);
                    equation = replace(equation, a, String.valueOf(toDouble(equation, a, i - 1) * toDouble(equation, i + 2, b)), b);
                    i = a - 1;
                    break;
                default:
            }
        }

        //AS
        for (int i = 0; i < equation.length(); i++) {
            int a;
            int b;
            switch (equation.charAt(i)) {
                case '+':
                    a = firstIndex(equation, i - 2);
                    b = lastIndex(equation, i + 2);
                    equation = replace(equation, a, String.valueOf(toDouble(equation, a, i - 1) + toDouble(equation, i + 2, b)), b);
                    i = a - 1;
                    break;
                case '–':
                    a = firstIndex(equation, i - 2);
                    b = lastIndex(equation, i + 2);
                    equation = replace(equation, a, String.valueOf(toDouble(equation, a, i - 1) - toDouble(equation, i + 2, b)), b);
                    i = a - 1;
                    break;
                default:
            }
        }
        return equation;
    }
}
