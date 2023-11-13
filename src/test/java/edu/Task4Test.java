package edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Task4Test {
    @ParameterizedTest
    @ValueSource(strings = {"pas!s", "pass*", "*", "!pasfpafpmafoi*", "stillCorrect~", "Я РУССКИЙ!"})
    public void correctWorksWihtCorrectPasswords(String pass) {
        Assertions.assertTrue(Task4.validatePassword(pass));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "pass", "PASS124", ">??><", "Я ИДУ ДО КОНЦА"})
    public void correctWorksWihtInCorrectPasswords(String pass) {
        Assertions.assertFalse(Task4.validatePassword(pass));
    }

    @Test
    public void correctWorksWithNull() {
        Assertions.assertFalse(Task4.validatePassword(null));
    }
}
