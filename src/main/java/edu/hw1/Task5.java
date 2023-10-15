package edu.hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Task5 {
    private static boolean isIntegerPalindrome(Integer[] inputDigitsArray) {
        var copyToReverse = inputDigitsArray.clone();
        Collections.reverse(Arrays.asList(copyToReverse));
        return Arrays.equals(copyToReverse, inputDigitsArray);
    }

    public static Integer[] convertIntegerToDigitsArray(Integer input) {
        var inputAsString = input.toString();
        var result = new Integer[inputAsString.length()];
        for (int i = 0; i < inputAsString.length(); i++) {
            result[i] = inputAsString.charAt(i) - '0';
        }
        return result;
    }

    public static boolean isPalindromeDescendant(Integer input) {
        Integer[] mainArray = convertIntegerToDigitsArray(input);
        while (mainArray.length > 1) {
            if (isIntegerPalindrome(mainArray)) {
                return true;
            }
            ArrayList<Integer> newInt = new ArrayList<>();

            for (var i = 1; i < mainArray.length; i += 2) {
                var sumInt = mainArray[i - 1] + mainArray[i];
                if (sumInt >= 10) {
                    newInt.add(sumInt / 10);
                    newInt.add(sumInt - 10 * (sumInt / 10));
                } else {
                    newInt.add(sumInt);
                }
            }
            if (mainArray.length % 2 != 0) {
                newInt.add(mainArray[mainArray.length - 1]);
            }

            mainArray = new Integer[newInt.size()];
            newInt.toArray(mainArray);
        }
        return false;
    }
}
