import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Calculate {

    public static void main(String[] args) throws Exception {
        // Announcing the scanner
        final Scanner sc = new Scanner(System.in);

        // Declaring all the variables that we are going to use
        String operation;
        String num1;
        String num2;
        boolean is_arabic_or_roman;


        while (true) {
            System.out.print("Enter the correct expression: ");
            // 10 + 10 or IV - I
            String input_data = sc.nextLine();
            List<String> input_data_list = Arrays.asList(input_data.split(" "));

            operation = input_data_list.get(1);
            num1 = input_data_list.get(0);
            num2 = input_data_list.get(2);
            is_arabic_or_roman = is_arabic_or_roman(num1, num2);
            check_input_number(num1, num2, is_arabic_or_roman);




            switch (operation) {
                case "+":
                    System.out.println(addition(num1, num2, is_arabic_or_roman));
                    break;
                case "-":
                    System.out.println(subtraction(num1, num2, is_arabic_or_roman));
                    break;
                case "*":
                    System.out.println(multiplication(num1, num2, is_arabic_or_roman));
                    break;
                case "/":
                    System.out.println(division(num1, num2, is_arabic_or_roman));
                    break;
                default:
                    throw new Exception("invalid expression");
            }
        }

    }




    public static void check_input_number(String num1, String num2, boolean is_arabic_or_roman) throws Exception {
        int number1;
        int number2;

        if (is_arabic_or_roman) {
            number1 = Integer.parseInt(num1);
            number2 = Integer.parseInt(num2);
        } else {
            number1 = convert_roman_to_arabic(num1);
            number2 = convert_roman_to_arabic(num2);
        }

        if (number1 < 1 || number1 > 10) {
//            throw new Exception("impossible input number: " + number1);
            throw new Exception(is_arabic_or_roman
                    ? "impossible input number: " + number1
                    : "impossible input number: " + num1);
        }

        if (number2 < 1 || number2 > 10) {
//            throw new Exception("impossible input number: " + number2);
            throw new Exception(is_arabic_or_roman
                    ? "impossible input number: " + number2
                    : "impossible input number: " + num2);
        }
    }

    // Convert Roman numerals to Arabic
    public static String convert_arabic_to_roman(int label) {
        for (RomanNumeral e : RomanNumeral.values()) {
            if (e.getValue() == label) {
                return e.toString();
            }
        }
        return null;
    }

    //What are the numbers at the entrance
    static boolean is_arabic_or_roman(String num1, String num2) throws Exception {
        // both numbers are Arabic
        if (num1.matches("[-+]?\\d+") && num2.matches("[-+]?\\d+")) {
            return true;
            // both numbers are Roman
        } else if (!num1.matches("[-+]?\\d+") && !num2.matches("[-+]?\\d+")) {
            return false;
            // One number is Arabic the other is Roman
        } else {
            throw new Exception("It is impossible to process an expression of Roman and Arabic numerals");
        }
    }

    // Convert Arabic numerals to Roman
    static int convert_roman_to_arabic(String input_roman) {
        return RomanNumeral.valueOf(input_roman).getValue();
    }

    static int is_available_result(int arabic_number) throws Exception {
        if (arabic_number < 0) {
            throw new Exception("The result of calculations of Roman numbers cannot be negative");
        }

        return arabic_number;
    }

    static String addition(String num1, String num2, boolean is_arabic) {
        return is_arabic
                ? String.valueOf(Integer.parseInt(num1) + Integer.parseInt(num2))
                : convert_arabic_to_roman (convert_roman_to_arabic(num1) + convert_roman_to_arabic(num2));
    }

    static String subtraction(String num1, String num2, boolean is_arabic) throws Exception {
        return is_arabic
                ? String.valueOf(Integer.parseInt(num1) - Integer.parseInt(num2))
                : convert_arabic_to_roman (is_available_result(convert_roman_to_arabic(num1) - convert_roman_to_arabic(num2)));
    }

    static String multiplication(String num1, String num2, boolean is_arabic) {
        return is_arabic
                ? String.valueOf(Integer.parseInt(num1) * Integer.parseInt(num2))
                : convert_arabic_to_roman(convert_roman_to_arabic(num1) * convert_roman_to_arabic(num2));
    }
    static String division(String num1, String num2, boolean is_arabic) {
        return is_arabic
                ? String.valueOf(Integer.parseInt(num1) / Integer.parseInt(num2))
                : convert_arabic_to_roman(convert_roman_to_arabic(num1) / convert_roman_to_arabic(num2));
    }
}