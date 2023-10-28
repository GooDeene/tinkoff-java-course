package edu.hw3;

import java.util.LinkedHashMap;

public class Task4 {
    private Task4() {
    }

    @SuppressWarnings("MagicNumber")
    // Без этого декоратора чекстайл ругается на 1000, 900, ..., 1.
    // Не вижу смысла отдельно выносить их в константы, если они и так задают константный LinkedHashMap

    private static final LinkedHashMap<Integer, String> ARABIC_TO_ROMAN_RANKS = new LinkedHashMap<>() {{
        put(1000, "M");
        put(900, "CM");
        put(500, "D");
        put(400, "CD");
        put(100, "C");
        put(90, "XC");
        put(50, "L");
        put(40, "XL");
        put(10, "X");
        put(9, "IX");
        put(5, "V");
        put(4, "IV");
        put(1, "I");
    }};
    private static final int MIN_ROMAN_INT = 1;
    private static final int MAX_ROMAN_INT = 3999;

    public static String convertToRoman(int input) {
        if (input < MIN_ROMAN_INT || input > MAX_ROMAN_INT) {
            throw new NumberFormatException("Число не может быть записано римскими цифрами!");
        }
        int arabicInt = input;
        StringBuilder result = new StringBuilder();
        for (int rank : ARABIC_TO_ROMAN_RANKS.keySet()) {
            int countCurrentRankToAppend = arabicInt / rank;
            arabicInt %= rank;
            while (countCurrentRankToAppend > 0) {
                result.append(ARABIC_TO_ROMAN_RANKS.get(rank));
                countCurrentRankToAppend--;
            }
        }

        return result.toString();
    }
}
