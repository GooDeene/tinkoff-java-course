package edu.hw3;

public class Task1 {
    private Task1() {
    }

    public static String atbash(String input) {
        if (input == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (Character letter : input.toCharArray()) {
            if (letter.toString().matches("[a-zA-Z]")) {
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
