package edu.hw1;

import java.util.ArrayList;
import java.util.Objects;

class Task5 {
    private Task5() {
    }

    private static final int TEN = 10;

    private static boolean isIntegerPalindrome(ArrayList<Integer> inputDigitsArray) {
        int len = inputDigitsArray.size();
        int midInd = inputDigitsArray.size() / 2;
        var firstPart = inputDigitsArray.subList(0, midInd);
        var secondPart = inputDigitsArray.subList(len % 2 == 0 ? midInd : midInd + 1, len);

        for (int i = 0; i < midInd; i++) {
            if (!Objects.equals(firstPart.get(i), secondPart.get(midInd - 1 - i))) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Integer> convertIntegerToDigitsArrayList(Integer input) {
        var inputAsString = input.toString();
        var result = new ArrayList<Integer>();
        for (int i = 0; i < inputAsString.length(); i++) {
            result.add(inputAsString.charAt(i) - '0');
        }
        return result;
    }

    public static boolean isPalindromeDescendant(Integer input) {
        ArrayList<Integer> mainArray = convertIntegerToDigitsArrayList(input);
        while (mainArray.size() > 1) {
            if (isIntegerPalindrome(mainArray)) {
                return true;
            }
            ArrayList<Integer> newInt = new ArrayList<>();

            for (var i = 1; i < mainArray.size(); i += 2) {
                var sumInt = mainArray.get(i - 1) + mainArray.get(i);
                if (sumInt >= TEN) {
                    newInt.add(sumInt / TEN);
                    newInt.add(sumInt % TEN);
                } else {
                    newInt.add(sumInt);
                }
            }
            if (mainArray.size() % 2 != 0) {
                newInt.add(mainArray.get(mainArray.size() - 1));
            }

            mainArray = newInt;
        }
        return false;
    }
}
