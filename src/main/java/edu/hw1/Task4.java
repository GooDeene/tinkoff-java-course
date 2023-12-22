package edu.hw1;

class Task4 {
    private Task4() {
    }

    public static String fixString(String input) {
        char[] inputChars = input.toCharArray();
        for (int i = 0; i < inputChars.length - 1; i += 2) {
            var temp = inputChars[i + 1];
            inputChars[i + 1] = inputChars[i];
            inputChars[i] = temp;
        }
        return new String(inputChars);
    }
}
