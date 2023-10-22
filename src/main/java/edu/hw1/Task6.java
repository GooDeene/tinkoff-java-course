package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;

class Task6 {
    private Task6() {
    }

    private static final int TEN = 10;
    private static final int UPPER_BORDER = 9999;
    private static final int LOWER_BORDER = 1000;
    private static final int CAPRAKER_REFERENCE = 6174;
    private static final int MAX_COUNT_DIGITS = 4;
    private static final int SAME_DIGITS_DIVIDER = 1111;

    public static Integer convertDigitsArrayToInteger(ArrayList<Integer> inputArray) {
        int result = 0;
        int order = 1;
        for (var i = inputArray.size() - 1; i >= 0; i--) {
            result += inputArray.get(i) * order;
            order *= TEN;
        }
        return result;
    }

    private static boolean isCaprakerConditionsMet(Integer input) {
        if (input > UPPER_BORDER || input < LOWER_BORDER) {
            return false;
        }
        return input % SAME_DIGITS_DIVIDER != 0;
    }

    public static int countK(Integer input) {
        if (!isCaprakerConditionsMet(input)) {
            return Integer.MAX_VALUE;
        } else if (input.equals(CAPRAKER_REFERENCE)) {
            return 0;
        }

        ArrayList<Integer> inputInt = Task5.convertIntegerToDigitsArrayList(input);
        Collections.sort(inputInt);
        var increaseInt = convertDigitsArrayToInteger(inputInt);
        Collections.reverse(inputInt);
        var decreaseInt = convertDigitsArrayToInteger(inputInt);
        int difference = decreaseInt - increaseInt;

        // Если в полученном числе меньше 4 разрядов можем домножить его на нужную степень 10
        // т.к. нет разницы добавлять нули в конце или в начале числа, потому что
        // мы всё равно будем переставлять цифры
        if (Task2.countDigits(difference) < MAX_COUNT_DIGITS) {
            int lambda = MAX_COUNT_DIGITS - Task2.countDigits(difference);
            difference *= (int) Math.pow(TEN, lambda);
        }
        return 1 + countK(difference);
    }
}
