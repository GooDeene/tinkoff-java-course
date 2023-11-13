package edu;

// Всё ещё воспринимаю формулировку "для строк из алфавита {0, 1}" как то, что строки
// заранее подготовлены и состоят только из 0 и 1...

public class Task8 {
    private Task8() {

    }

    // нечетной длины
    public static boolean isCondition1Valid(String str) {
        checkNull(str);
        return str.matches("^.(..)*$");
    }

    //начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
    public static boolean isCondition2Valid(String str) {
        checkNull(str);
        return str.matches("^0(..)*$|^1.(..)*$");
    }

    //количество 0 кратно 3
    public static boolean isCondition3Valid(String str) {
        checkNull(str);
        return str.matches("(1*01*01*01*)*");
    }

    //каждый нечетный символ равен 1
    public static boolean isCondition4Valid(String str) {
        checkNull(str);
        return str.matches("(1.)*1?");
    }

    //любая строка, кроме 11 или 111
    public static boolean isCondition5Valid(String str) {
        checkNull(str);
        return str.matches("(?!^11$)(?!^111$).*");
    }

    public static void checkNull(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Переданная для валидации строка не может быть Null`ом!");
        }
    }
}
