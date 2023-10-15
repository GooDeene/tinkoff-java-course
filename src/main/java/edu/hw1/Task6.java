package edu.hw1;

import java.util.Arrays;
import java.util.Collections;

public class Task6 {
    public static Integer convertDigitsArrayToInteger(Integer[] inputArray) {
        int result = 0;
        for (var i = 0; i < inputArray.length; i++) {
            result += inputArray[i] * ((int) Math.pow(10, inputArray.length - i - 1));
        }
        return result;
    }

    private static boolean isCaprakerConditionsMet(Integer input) {
        if (input >= 10000 || input <= 1000) {
            return false;
        }
        Integer[] digits = Task5.convertIntegerToDigitsArray(input);
        for (Integer digit : digits) {
            if (!digit.equals(digits[0])) {
                return true;
            }
        }
        return false;
    }

    public static int countK(Integer input) {
        if (!isCaprakerConditionsMet(input)) {
            return Integer.MAX_VALUE;
        } else if (input.equals(6174)) {
            return 0;
        }

        Integer[] inputInt = Task5.convertIntegerToDigitsArray(input);
        Integer[] increaseDigits = inputInt.clone();
        Integer[] decreaseDigits = inputInt.clone();
        Arrays.sort(increaseDigits);
        Arrays.sort(decreaseDigits);
        Collections.reverse(Arrays.asList(decreaseDigits));
        int difference = convertDigitsArrayToInteger(decreaseDigits) - convertDigitsArrayToInteger(increaseDigits);

        // Если в полученном числе меньше 4 разрядов можем домножить его на нужную степень 10
        // т.к. нет разницы добавлять нули в конце или в начале числа, потому что
        // мы всё равно будем переставлять цифры
        if (Task2.countDigits(difference) < 4) {
            var lambda = 4 - Task2.countDigits(difference);
            difference *= 10 * lambda;
        }
        return 1 + countK(difference);
    }
}
