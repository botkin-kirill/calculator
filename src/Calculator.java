import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    static int[] romanValues = {90, 40, 30, 20, 9, 4, 3, 2, 100, 50, 10, 5, 1};

    static String[] romanSymbols = {"XC", "XL", "XXX", "XX", "IX", "IV", "III", "II", "C", "L", "X", "V", "I"};


    static void calculate(String input) {
        String[] data = input.split(" ");
        try {
            String left_operator = data[0];
            String operand = data[1];
            if (!isOperandCorrect(operand)) {
                throw new IllegalArgumentException("invalid operand");
            }
            String right_operator = data[2];
            int system_type = choose_number_system(left_operator, right_operator);
            if (system_type == 1) {
                left_operator = String.valueOf(convertRomanToDecimal(left_operator));
                right_operator = String.valueOf(convertRomanToDecimal(right_operator));
            }
            int leftOperatorInt = Integer.parseInt(left_operator);
            int rightOperatorInt = Integer.parseInt(right_operator);
            if (leftOperatorInt < 1 || leftOperatorInt > 10
                    || rightOperatorInt < 1 || rightOperatorInt > 10) {
                throw new IllegalArgumentException("input less than 1 or greater than 10");
            }
            char operandChar = operand.charAt(0);
            int result;
            switch (operandChar) {
                case '+':
                    result = leftOperatorInt + rightOperatorInt;
                    break;
                case '-':
                    result = leftOperatorInt - rightOperatorInt;
                    break;
                case '*':
                    result = leftOperatorInt * rightOperatorInt;
                    break;
                case '/':
                    result = leftOperatorInt / rightOperatorInt;
                    break;
                default:
                    throw new IllegalArgumentException("invalid operand");
            }
            if (system_type == 1) {
                //todo            вернуть децимал ту роман
                System.out.println(convertDecimalToRoman(result));
                return;
            }
            System.out.println(result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ошибка ввода");
        }
    }


    private static int choose_number_system(String left, String right) throws Exception {
        if (isDecimalNumeral(left) && isDecimalNumeral(right)) {
            return 0;
        } else if (isRomanNumeral(left) && isRomanNumeral(right)) {
            return 1;
        } else throw new IllegalArgumentException("invalid input");
    }

    private static int convertRomanToDecimal(String input) {
        int decimalValue = 0;
        for (int i = 0; i < romanSymbols.length; i++) {
            while (input.contains(romanSymbols[i])) {
                input = input.replaceFirst(romanSymbols[i], "");
                decimalValue += romanValues[i];
            }
        }
        return decimalValue;
    }

    private static String convertDecimalToRoman(int number) {
        ArrayList<Integer> romanValues_list = new ArrayList<>(Arrays.asList(90, 40, 30, 20, 9, 4, 3, 2, 100, 50, 10, 5, 1));
        ArrayList<Integer> list = new ArrayList<Integer>();
        StringBuilder sb = new StringBuilder();
        while (number != 0) {
            list.add(number % 10);
            number = number / 10;
        }
        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++) {
            int element = list.get(i);
            if (element != 0) {
                if (element>5 && element<9){
                    int subelement = (int) ((5 * Math.pow(10, (list.size() - i - 1))));
                    sb.append(romanSymbols[romanValues_list.indexOf(subelement)]);
                    element-=5;
                }
                element = (int) (element * Math.pow(10, (list.size() - i - 1)));
                int symbol_index = romanValues_list.indexOf(element);
                sb.append(romanSymbols[symbol_index]);
            }
        }
        return sb.toString();
    }


    private static boolean isOperandCorrect(String input) {
        try {
            char[] legitOperands = {'+', '-', '*', '/'};
            char operand = input.charAt(0);
            for (char legitOperand : legitOperands) {
                if (legitOperand == operand) {
                    return true;
                }
            }
            throw new IllegalArgumentException("invalid operand");
        } catch (Exception e) {
            return false;
        }
    }


    private static boolean isRomanNumeral(String input) {
        String pattern = "^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(input);
        return m.matches();
    }

    private static boolean isDecimalNumeral(String input) {
        try {
            int num = Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
