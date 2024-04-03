public class Calculations {
    static String bareBones = "(5)(7–-1)^l2";
    static String display = "";
    static String equation = "";
    static int cursor = 0;

    public static void moveCursorLeft(){
        if (cursor > 0){
            cursor--;
        }
    }

    public static void moveCursorRight(){
        if (cursor < bareBones.length()){
            cursor++;
        }
    }

    public static void addToBareBones(int index, char character){
        bareBones = bareBones.substring(0, index) + character + bareBones.substring(index, bareBones.length());
        cursor++;
    }

    public static void removeFromBareBones(int index){
        if (bareBones.length() > 0 && cursor > 0){
            bareBones = bareBones.substring(0, index - 1) + bareBones.substring(index, bareBones.length());
            cursor--;
        }
        if (bareBones.length() == 0){
            bareBones = "";
        }
    }

    public static String getDisplay(){
        return display;
    }

    public static void formatDisplay(){
        display = "";
        for (int i = 0; i < bareBones.length(); i++){
            if (i == cursor){
                display += '|';
            }

            if (bareBones.charAt(i) == '+' || bareBones.charAt(i) == '–' || bareBones.charAt(i) == '*' || bareBones.charAt(i) == '/'){
                display += " " + bareBones.charAt(i) + " ";
                continue;
            }
            if (bareBones.charAt(i) == 'S'){
                display += "sin";
                continue;
            }
            if (bareBones.charAt(i) == 'C'){
                display += "cos";
                continue;
            }
            if (bareBones.charAt(i) == 'T'){
                display += "tan";
                continue;
            }
            if (bareBones.charAt(i) == 's'){
                display += "asin";
                continue;
            }
            if (bareBones.charAt(i) == 'c'){
                display += "acos";
                continue;
            }
            if (bareBones.charAt(i) == 't'){
                display += "atan";
                continue;
            }
            if (bareBones.charAt(i) == 'l'){
                display += "log";
                continue;
            }
            if (bareBones.charAt(i) == '-'){
                display += "-";
                continue;
            }
            display += bareBones.charAt(i);
        }
        if (cursor == bareBones.length()){
            display += '|';
        }
    }

    private static void formatEquation(){
        equation = "";
        for (int i = 0; i < bareBones.length(); i++){
            if (bareBones.charAt(i) == '+' || bareBones.charAt(i) == '–' || bareBones.charAt(i) == '*' || bareBones.charAt(i) == '/' || bareBones.charAt(i) == '^' || bareBones.charAt(i) == '√'){
                equation += " " + bareBones.charAt(i) + " ";
                continue;
            }

            if (bareBones.charAt(i) == 'π'){
                if (i > 0 && (digits(bareBones.charAt(i - 1)) == true || bareBones.charAt(i - 1) == 'π')){
                    equation += " * " + bareBones.charAt(i);
                    continue;
                }
                if (i < bareBones.length() - 1 && (digits(bareBones.charAt(i + 1)) == true || bareBones.charAt(i + 1) == 'π')){
                    equation += bareBones.charAt(i) + " * ";
                    continue;
                }
            }

            if (specialChar(bareBones.charAt(i))){
                if (i > 0 && (digits(bareBones.charAt(i - 1)) == true || bareBones.charAt(i - 1) == 'π' || bareBones.charAt(i - 1) == ')') ){
                    equation += " * " + bareBones.charAt(i);
                    continue;
                }
            }

            if (bareBones.charAt(i) == ')'){
                if (i < bareBones.length() - 1 && (digits(bareBones.charAt(i + 1)) == true || bareBones.charAt(i + 1) == 'π')){
                    equation += bareBones.charAt(i) + " * ";
                    continue;
                }
            }

            equation += bareBones.charAt(i);
        }
        if (bareBones == ""){
            equation = "0";
        }
    }

    private static boolean specialChar(char character){
        //S, C, and T are sin cos and tan
        //s, c, and t are the inverse
        //l is log
        //n means it's negative
        if (character == 'S' || character == 'C' || character == 'T' || character == '(' || character == 's' || character == 'c' || character == 't' || character == 'l' || character == 'n'){
            return true;
        } else {
            return false;
        }
    }

    private static boolean digits(char character){
        if ((int) character > 47 && (int) character < 58){
            return true;
        } else {
            return false;
        }
    }

    private static boolean validEquation(){
        int brackets = 0;
        int digits = 0;
        int dots = 0;

        if (equation.charAt(0) == '.' || equation.charAt(equation.length() - 1) == '.'){
            display = "Error 1: Decimal at beginning or end of equation";
            return false;
        }
        if (equation.charAt(0) == ' ' || equation.charAt(equation.length() - 1) == ' '){
            display = "Error 2: Operation at beginning or end of equation";
            return false;
        }
        for (int i = 0; i < equation.length(); i++){
            if (equation.charAt(i) == ' '){
                if (equation.charAt(i + 1) == ' '){
                    display = "Error 3: Missing number between operations";
                    return false;
                }
                dots = 0;
            }

            if (equation.charAt(i) == '.'){
                if (digits(equation.charAt(i + 1)) == false || digits(equation.charAt(i - 1)) == false){
                    display = "Error 4: No number before or after decimal";
                    return false;
                }
                dots++;
            }

            if (specialChar(equation.charAt(i)) == true){
                if (i < equation.length() - 1 && (equation.charAt(i + 1) == ' ' || equation.charAt(i + 1) == ')')){
                    display = "Error 5: No number, (, or trig func after ( or trig func";
                    return false;
                }
                if (i == equation.length() - 1){
                    display = "Error 5: No number, (, or trig func after ( or trig func";
                    return false;
                }
            }

            if (equation.charAt(i) == '('){
                brackets++;
            }
            
            if (equation.charAt(i) == ')'){
                if (i > 0 && equation.charAt(i - 1) == ' '){
                    display = "Error 6: Operation before )";
                    return false;
                }
                brackets--;
            }

            if (brackets < 0){
                display = "Error 7: ) is missing proceeding (";
                return false;
            }

            if (dots > 1){
                display = "Error 8: Two or more decimals in one number";
                return false;
            }

            if (digits(equation.charAt(i)) == true){
                digits++;
            }
        }

        if (brackets != 0){
            display = "Error 9: ( is missing following )";
            return false;
        }

        if (digits == 0){
            display = "Error 10: No numbers";
            return false;
        }

        return true;
    }

    private static int calculate(){
        return 7;
    }

    public static void reset(){
        bareBones = "";
        equation = "";
        display = "";
    }

    public static void equals() {
        formatEquation();
        if (validEquation() == false){
            System.out.println(display);
            reset();
            return;
        }
        display = "= " + equation;
    }

    public static void main(String[] args) {
        /*
        System.out.println("hello, world");
        formatDisplay();
        System.out.println(bareBones);
        System.out.println(display);
        addToBareBones(cursor, '4');
        formatDisplay();
        System.out.println(bareBones);
        System.out.println(display);
        equals();
        System.out.println(display);
        */
        formatDisplay();
        System.out.println(bareBones);
        System.out.println(display);
        equals();
        System.out.println(display);
    }
}