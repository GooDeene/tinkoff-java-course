package edu.hw1;

class Task2 {
    @SuppressWarnings("MagicNumber")
    public static int countDigits(Integer inputInteger) {
        if (inputInteger == 0) {
            return 1;
        } else if (inputInteger < 0) {
            return (int) Math.log10(-inputInteger) + 1;
        }

        return (int) Math.log10(inputInteger) + 1;
    }
}
