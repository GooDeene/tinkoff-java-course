package edu.hw3;

public class Task1 {
    private Task1() {
    }

    public static String atbash(String input) {
        if (input == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (char letter : input.toCharArray()) {
            if (Character.isLetter(letter)) {
                if (Character.isLowerCase(letter)) {
                    result.append((char) ('z' - letter + 'a'));
                } else {
                    result.append((char) ('Z' - letter + 'A'));
                }
            } else {
                result.append(letter);
            }
        }
        return result.toString();
    }
}
