package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task7Test {
    @ParameterizedTest
    @CsvSource({
        "8, 1, 4",
        "45, 4, 54",
        "1, 1, 1",
        "1, 18, 1",
        "2, 7, 1",
        "46, 0, 46"
    })
    public void rotateRightCorrectWorksWithCorrectData(int n, int shift, int result) {
        Assertions.assertEquals(result, Task7.rotateRight(n, shift));
    }

    @ParameterizedTest
    @CsvSource({
        "0, 1",
        "-10, 2",
        "10, -2",
        "-2, -2"
    })
    public void rotateRigthCorrectWorcsWithIncorrectData(int n, int shift) {
        Assertions.assertEquals(-1, Task7.rotateRight(n, shift));
    }

    @ParameterizedTest
    @CsvSource({
        "16, 1, 1",
        "17, 2, 6",
        "1, 1, 1",
        "1, 18, 1",
        "2, 3, 1",
        "2, 2, 2",
        "46, 0, 46"
    })
    public void rotateLeftCorrectWorksWithCorrectData(int n, int shift, int result) {
        Assertions.assertEquals(result, Task7.rotateLeft(n, shift));
    }

    @ParameterizedTest
    @CsvSource({
        "0, 1",
        "-10, 2",
        "10, -2",
        "-2, -2"
    })
    public void rotateLeftCorrectWorcsWithIncorrectData(int n, int shift) {
        Assertions.assertEquals(-1, Task7.rotateLeft(n, shift));
    }
}
