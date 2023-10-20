package edu.hw1;

class Task7 {
    private Task7() {
    }

    private static int rotateRigthInner(int n, int shift) {
        int digitsCount = (int) (Math.log(n) / Math.log(2)) + 1;
        int change = shift % digitsCount;
        int integer = n;
        int result = integer >>> change;
        for (int i = change; i > 0; i--) {
            result += (integer & 1) << (digitsCount - i);
            integer >>= 1;
        }
        return result;
    }

    private static boolean isInputDataValid(int n, int shift) {
        return n > 0 && shift >= 0;
    }

    public static int rotateRight(int n, int shift) {
        return isInputDataValid(n, shift) ? rotateRigthInner(n, shift) : -1;
    }

    public static int rotateLeft(int n, int shift) {
        if (isInputDataValid(n, shift)) {
            int countDigits = (int) (Math.log(n) / Math.log(2)) + 1;
            return rotateRigthInner(n, countDigits - shift % countDigits);
        } else {
            return -1;
        }
    }
}
