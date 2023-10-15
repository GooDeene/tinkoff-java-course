package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class Task6Test {
    @ParameterizedTest
    @CsvSource({
        "3524, 3",
        "6621, 5",
        "6554, 4",
        "1234, 3",
        "6656, 5",
        "6174, 0",
        "6147, 1"
    })
    public void correctWorksWithCorrectData(int input, int correctResult) {
        Assertions.assertEquals(correctResult, Task6.countK(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 1000, 1111, 12345, 999, 6666})
    public void correctWorksWithIncorrectData(int input) {
        Assertions.assertEquals(Integer.MAX_VALUE, Task6.countK(input));
    }
}
