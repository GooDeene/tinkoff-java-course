package edu.hw1;

public class Task4 {
    public static String fixString(String input) {
        char[] inputChars = input.toCharArray();
        for (int i = 0; i < inputChars.length; i += 2) {
            if (i + 1 < inputChars.length) {
                var temp = inputChars[i + 1];
                inputChars[i + 1] = inputChars[i];
                inputChars[i] = temp;
            }
        }
        return new String(inputChars);
    }
}
