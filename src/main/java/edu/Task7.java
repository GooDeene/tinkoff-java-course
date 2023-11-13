package edu;

// Условие из задач 7 и 8 "для строк из алфавита {0, 1}" воспринимаю как то,
// то на вход методов уже подаются строки ТОЛЬКО из 0 и 1, потому отдельных проверок на это
// не реализовываю.

// P.S.: Пожелание - чётче и подробнее описывать задания в ДЗ, приходится глубоко
// зарываться в чат чтобы найти ответы на свои вопросы в формулировках заданий...

public class Task7 {
    private Task7() {}

    // содержит не менее 3 символов, причем третий символ равен 0
    public static boolean isFirstConditionValid(String binaryString) {
        Task8.checkNull(binaryString);
        return binaryString.matches("[01]{2}0[01]*");
    }

    // начинается и заканчивается одним и тем же символом
    public static boolean isSecondConditionValid(String binaryString) {
        Task8.checkNull(binaryString);
        return binaryString.matches("0.*0$|^1.*1|0|1");
    }

    // длина не менее 1 и не более 3
    public static boolean isThirdConditionValid(String binaryString) {
        Task8.checkNull(binaryString);
        return binaryString.matches("[01]{1,3}");
    }
}
