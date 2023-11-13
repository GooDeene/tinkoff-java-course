package edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Task8Test {
    @ParameterizedTest
    @ValueSource(strings = {"1", "0", "111", "000", "101", "11010101001"})
    public void cond1CorrectWorksWithCorrectData(String str) {
        Assertions.assertTrue(Task8.isCondition1Valid(str));
    }

    @ParameterizedTest
    @ValueSource(strings = {"11", "00", "", "1010", "101010101010", "0000"})
    public void cond1CorrectWorksWithInCorrectData(String string) {
        Assertions.assertFalse(Task8.isCondition1Valid(string));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "011", "000", "0101010", "10", "1011", "100000"})
    public void cond2CorrectWorksWithCorrectData(String str) {
        Assertions.assertTrue(Task8.isCondition2Valid(str));
    }

    @ParameterizedTest
    @ValueSource(strings = {"01", "0110", "", "1010000", "0000"})
    public void cond2CorrectWorksWithInCorrectData(String string) {
        Assertions.assertFalse(Task8.isCondition2Valid(string));
    }

    @ParameterizedTest
    @ValueSource(strings = {"000", "01100", "011110001111100", "", "101010"})
    public void cond3CorrectWorksWithCorrectData(String str) {
        Assertions.assertTrue(Task8.isCondition3Valid(str));
    }

    @ParameterizedTest
    @ValueSource(strings = {"01", "0110", "0", "1010000", "0000000000"})
    public void cond3CorrectWorksWithInCorrectData(String string) {
        Assertions.assertFalse(Task8.isCondition3Valid(string));
    }

    @ParameterizedTest
    @ValueSource(strings = {"10", "1", "1010101010", "11111111", ""})
    public void cond4CorrectWorksWithCorrectData(String str) {
        Assertions.assertTrue(Task8.isCondition4Valid(str));
    }

    @ParameterizedTest
    @ValueSource(strings = {"01", "0110", "100", "1010000", "0000000000"})
    public void cond4CorrectWorksWithInCorrectData(String string) {
        Assertions.assertFalse(Task8.isCondition4Valid(string));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "10", "1", "1111", "11111111","10010101010100101010", "1110", "0111"})
    public void cond5CorrectWorksWithCorrectData(String str) {
        Assertions.assertTrue(Task8.isCondition5Valid(str));
    }

    @ParameterizedTest
    @ValueSource(strings = {"11", "111"})
    public void cond5CorrectWorksWithInCorrectData(String string) {
        Assertions.assertFalse(Task8.isCondition5Valid(string));
    }
}
