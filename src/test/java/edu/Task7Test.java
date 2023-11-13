package edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Task7Test {
    @ParameterizedTest
    @ValueSource(strings = {"0000", "1100", "000", "110", "11011", "0001111", "00000"})
    public void firstCondWorksCorrectlyWithCorrectData(String str) {
        Assertions.assertTrue(Task7.isFirstConditionValid(str));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "00", "001", "111", "0010", "1", ""})
    public void firstCondWorksCorrectlyWithInCorrectData(String str) {
        Assertions.assertFalse(Task7.isFirstConditionValid(str));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0000", "1101", "000", "11", "1", "0011110", "0101010101010100", "0"})
    public void secondCondWorksCorrectlyWithCorrectData(String str) {
        Assertions.assertTrue(Task7.isSecondConditionValid(str));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0001", "0101", "001", "01", "", "1011110", "01010101010101"})
    public void secondCondWorksCorrectlyWithInCorrectData(String str) {
        Assertions.assertFalse(Task7.isSecondConditionValid(str));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "00", "11", "010", "101", "111", "000"})
    public void thirdCondWorksCorrectlyWithCorrectData(String str) {
        Assertions.assertTrue(Task7.isThirdConditionValid(str));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1011", "10101010", "11111111", "0000"})
    public void thirdCondWorksCorrectlyWithInCorrectData(String str) {
        Assertions.assertFalse(Task7.isThirdConditionValid(str));
    }
}

