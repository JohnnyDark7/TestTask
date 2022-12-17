import java.util.Scanner;
import java.util.regex.Pattern;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        System.out.println(Main.calc(input));
    }
    public static String calc(String input) {
        String[] data = input.split(" ");
        if (data.length != 3) {
            throw new RuntimeException("Wrong input data");
        }
        if (Pattern.matches("[!-/:-BE-HJKN-UWY-~]*", data[0])) {
            throw new RuntimeException("Wrong input data");
        }
        if (Pattern.matches("[!-/:-BE-HJKN-UWY-~]*", data[2])) {
            throw new RuntimeException("Wrong input data");
        }
        if (Pattern.matches("[0-9]*", data[0]) && Pattern.matches("[IVXLCDM]*", data[0])) {
            throw new RuntimeException("Wrong input data");
        }
        if (Pattern.matches("[0-9]*", data[2]) && Pattern.matches("[IVXLCDM]*", data[2])) {
            throw new RuntimeException("Wrong input data");
        }
        if (Pattern.matches("[0-9]*", data[0]) && Pattern.matches("[IVXLCDM]*", data[2])) {
            throw new RuntimeException("Wrong input data");
        }
        if (Pattern.matches("[0-9]*", data[2]) && Pattern.matches("[IVXLCDM]*", data[0])) {
            throw new RuntimeException("Wrong input data");
        }
        int result;
        String output;
        int firstNumber;
        int secondNumber;
        boolean isRoman = false;
        if (Pattern.matches("[0-9]*", data[0]) && Pattern.matches("[0-9]*", data[2])) {
            firstNumber = Integer.parseInt(data[0]);
            secondNumber = Integer.parseInt(data[2]);
        } else {
            firstNumber = Transfer.toArabic(data[0]);
            secondNumber = Transfer.toArabic(data[2]);
            isRoman = true;
        }
        result = switch (data[1]) {
            case "+" -> firstNumber + secondNumber;
            case "-" -> firstNumber - secondNumber;
            case "*" -> firstNumber * secondNumber;
            case "/" -> Math.floorDiv(firstNumber, secondNumber);
            default -> throw new RuntimeException("Wrong input data");
        };
        if (isRoman) {
            if (result<=0)
                throw new RuntimeException("Wrong output data");
            output = Transfer.toRoman(result);
        } else {
            output = String.valueOf(result);
        }
        return output;
    }
}

class Transfer {
    static int toArabic(String input) {
        int result = 0;
        int[] numberCount = new int[13];
        int[] numbers = {4, 9, 40, 90, 400, 900, 1, 5, 10, 50, 100, 500, 1000};
        String[] numberCheck = {"IV", "IX", "XL", "XC", "CD", "CM", "I", "V", "X", "L", "C", "D", "M"};
        for (int i = 0; i < numberCheck.length; i++) {
            while (input.contains(numberCheck[i])) {
                input = input.replaceFirst(numberCheck[i], "");
                numberCount[i]++;
            }
            result = result + numberCount[i] * numbers[i];
        }
        return result;
    }

    static String toRoman(int inputInt) {
        String result = "I".repeat(inputInt);
        String[] sequenceToReplace = {"IIIII", "VV", "XXXXX", "LL", "CCCCC", "DD", "DCCCC", "CCCC", "LXXXX", "XXXX", "VIIII", "IIII"};
        String[] numberReplacement = {"V", "X", "L", "C", "D", "M", "CM", "CD", "XC", "XL", "IX", "IV"};
        for (int i = 0; i < numberReplacement.length; i++) {
            while (result.contains(sequenceToReplace[i])) {
                result = result.replace(sequenceToReplace[i], numberReplacement[i]);
            }
        }
        return result;
    }
}
