package calculator;

/**
 *
 * @author weig4542
 */
public class Calculation {

    //DRG modifiers to convert radians to degrees, gradians, or jsut radians
    private static double dRGMod() {
        //Get whether it's degrees, radians, or gradians
        switch (FXMLDocumentController.getDRG()) {
            case 0:
                return 180d / Math.PI;
            case 1:
                return 1d;
            case 2:
                return 200d / Math.PI;
            default:
        }
        return 1.0;
    }

    //Returns a string with some middle part added or replacing another part. I thought it would look nicer than having substrings everywhere.
    private static String replace(String original, int a, String middle, int b) {
        //Creating a new string where from the original. Another string is inserted into the original string between a and b.
        return original.substring(0, a) + middle + original.substring(b, original.length());
    }

    //Returns part of the equation into a double
    public static double toDouble(String equation, int a, int b) {
        equation = equation.substring(a, b);
        int negative = 0;
        while (equation.length() > 0) {
            if (equation.charAt(0) == '-' || equation.charAt(0) == '—') {
                equation = equation.substring(1);
                negative++;
            } else {
                break;
            }
        }
        //Special case for pi
        if (equation.equals("π")) {
            return Math.PI * Math.pow(-1, negative);
        }
        if (equation.equals("A")) {
            return Preparation.getAnswer() * Math.pow(-1, negative);
        }
        //Special case for Infinity
        if (equation.charAt(0) == 'I') {
            return 1d / 0d * Math.pow(-1, negative);
        }
        //Special case for NaN
        if (equation.charAt(0) == 'N') {
            return 0d / 0d;
        }

        return Double.parseDouble(equation) * Math.pow(-1, negative);
    }

    //Check if a character is some number
    private static boolean digits(char character) {
        //Turns the character into it's ASCII code and checks if it's between 47 and 58 since those are where the number codes are
        if ((int) character > 47 && (int) character < 58) {
            return true;
        } else {
            return false;
        }
    }

    //Finds at which index the number in the equation ends when given the index it starts at
    private static int lastIndex(String equation, int index) {
        //Special case for Infinity and Nan
        if (equation.charAt(index) == 'I') {
            return index + 8;
        }
        if (equation.charAt(index) == 'N') {
            return index + 3;
        }
        //Startng at the first index, check every index after to see if it's part of a number. Stop when this isn't the case or if the equation ended.
        for (index = index; index < equation.length(); index++) {
            if (digits(equation.charAt(index)) == false && equation.charAt(index) != '-' && equation.charAt(index) != '—' && equation.charAt(index) != '.' && equation.charAt(index) != 'π' && equation.charAt(index) != 'A' && equation.charAt(index) != 'E') {
                break;
            }
        }
        return index;
    }

    //Finds at which index the number in the equation starts when given the index it ends at
    private static int firstIndex(String equation, int index) {
        //Special case for Infinity and Nan
        if (equation.charAt(index) == 'y') {
            return index - 7;
        }
        if (equation.charAt(index) == 'N') {
            return index - 2;
        }
        //Startng at the last index, check every index before to see if it's part of a number. Stop when this isn't the case or if the equation ended.
        for (index = index; index > -1; index--) {
            if (digits(equation.charAt(index)) == false && equation.charAt(index) != '-' && equation.charAt(index) != '—' && equation.charAt(index) != '.' && equation.charAt(index) != 'π' && equation.charAt(index) != 'A' && equation.charAt(index) != 'E') {
                break;
            }
        }
        return index + 1;
    }

    //The calculate method. Called to calculate a given equation.
    public static String calculate(String equation) {
        /*
        Brackets
        Exponents
        Division Multiplication
        Addition Subtraction
         */

        //Start with brackets. Use recursion to calculate the inside of brackets first
        //Brackets
        for (int i = 0; i < equation.length(); i++) {
            //If a bracket is encountered
            if (equation.charAt(i) == '(') {
                int brackets = 0;
                //Keep track of the brackets in the rest of the equation
                for (int e = i; e < equation.length(); e++) {
                    if (equation.charAt(e) == '(') {
                        brackets++;
                    }
                    if (equation.charAt(e) == ')') {
                        brackets--;
                    }
                    //if this value is 0, this i is the index of the ) corresponding to the previsouly found (
                    if (brackets == 0) {
                        //Calculate the equation inside of these brackets and put it into the larger equation where the brackets were originally
                        equation = replace(equation, i, calculate(equation.substring(i + 1, e)), e + 1);
                        break;
                    }
                }
                i--;
            }
        }

        //Each level of priority has it's own for loop in the order of priority. Each loop has a swtich statement to check if the character in string is one of the operation
        //Every operation in the same switch statement has the same priority. The earlier for loops are done earlier than later for loops for order of operations.
        //Exponents
        for (int i = equation.length() - 1; i > -1; i--) {
            //Initialize integers outside of switch statement then give a value inside of the switch statement.
            //a is the first index of the number prior to an operation while b is the last index of the number after an operation
            int a;
            int b;
            switch (equation.charAt(i)) {
                case '^':
                    //Call firstIndex method with the index of the last index of the prior number
                    a = firstIndex(equation, i - 2);
                    //Call lastIndex method with the index of the first index of the later number
                    b = lastIndex(equation, i + 2);
                    //— is the version of the negative symbol that hasn't been in a bracket. ToDouble turns — into - because it makes the double number negative which, when turned back into a string becomes -.
                    //— can only exist if it wasn't in a bracket. If — is the first index in the previous number then toDoudblwith a + 1 isntead of just a as the first index. Then multiply the result by -1
                    if (equation.charAt(a) == '—') {
                        equation = replace(equation, a, String.valueOf(Math.pow(toDouble(equation, a + 1, i - 1), toDouble(equation, i + 2, b)) * -1), b);
                    } else {
                        //Take the prior number to the power of the second number and insert it into the string where the original operation was
                        equation = replace(equation, a, String.valueOf(Math.pow(toDouble(equation, a, i - 1), toDouble(equation, i + 2, b))), b);
                    }
                    i = a + 1;
                    break;
                case '√':
                    a = firstIndex(equation, i - 2);
                    b = lastIndex(equation, i + 2);
                    //Does the same — thing as ^
                    if (equation.charAt(a) == '—') {
                        equation = replace(equation, a, String.valueOf(Math.pow(toDouble(equation, i + 2, b), 1d / toDouble(equation, a + 1, i - 1)) * -1), b);
                    } else {
                        //No root Math method so isntead take the second number to the power of the inverse of the first number
                        equation = replace(equation, a, String.valueOf(Math.pow(toDouble(equation, i + 2, b), 1d / toDouble(equation, a, i - 1))), b);
                    }
                    i = a + 1;
                    break;
                case 'S':
                    b = lastIndex(equation, i + 1);
                    //If the user is in degrees or gradians, convert to radians so the computer can do the math
                    equation = replace(equation, i, String.valueOf(Math.sin(toDouble(equation, i + 1, b) / dRGMod())), b);
                    i++;
                    break;
                case 'C':
                    b = lastIndex(equation, i + 1);
                    equation = replace(equation, i, String.valueOf(Math.cos(toDouble(equation, i + 1, b) / dRGMod())), b);
                    i++;
                    break;
                case 'T':
                    b = lastIndex(equation, i + 1);
                    //Special case for Tan(PI/2) = Undefined since Math.tan doesn't do this by default, likely due to precision problems
                    if (Math.tan(toDouble(equation, i + 1, b)) - Math.tan(Math.PI / 2d) == 0) {
                        equation = replace(equation, i, "Infinity", b);
                    } else {
                        equation = replace(equation, i, String.valueOf(Math.tan(toDouble(equation, i + 1, b) / dRGMod())), b);
                    }
                    i++;
                    break;
                case 's':
                    b = lastIndex(equation, i + 1);
                    //If the user is in degrees or gradians, convert the radians result into degrees or radians
                    equation = replace(equation, i, String.valueOf(Math.asin(toDouble(equation, i + 1, b)) * dRGMod()), b);
                    i++;
                    break;
                case 'c':
                    b = lastIndex(equation, i + 1);
                    equation = replace(equation, i, String.valueOf(Math.acos(toDouble(equation, i + 1, b)) * dRGMod()), b);
                    i++;
                    break;
                case 't':
                    //Special case to check if the t it's found is actually atan or if it's part of Infinity
                    if (equation.charAt(i + 1) != 'y') {
                        b = lastIndex(equation, i + 1);
                        equation = replace(equation, i, String.valueOf(Math.atan(toDouble(equation, i + 1, b)) * dRGMod()), b);
                        i++;
                    }
                    break;
                case 'l':
                    b = lastIndex(equation, i + 1);
                    //The user only gets log base 10
                    equation = replace(equation, i, String.valueOf(Math.log10(toDouble(equation, i + 1, b))), b);
                    i++;
                    break;
                default:
            }
        }

        //Division Multiplication
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

        //Addition Subtraction
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
        //If the equation starts with — theput it through the toDouble machine
        if (equation.charAt(0) != '—') {
            return equation;
        } else {
            return String.valueOf(toDouble(equation, 0, equation.length()));
        }
    }
}
