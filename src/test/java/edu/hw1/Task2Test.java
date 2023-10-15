package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task2Test {
    @Test
    void correctWorksWithCorrectData() {
        int data = 123456789;
        Assertions.assertEquals(9, Task2.countDigits(data));
    }

    @Test
    void correctWorksWithSingleDigitNumbers() {
        int data = 1;
        Assertions.assertEquals(1, Task2.countDigits(data));
    }

    @Test
    void correctWorksWithZero() {
        int data = 0;
        Assertions.assertEquals(1, Task2.countDigits(data));
    }

    @Test
    void correctWorksWithNegativeNumbers() {
        int data = -12345;
        Assertions.assertEquals(5, Task2.countDigits(data));
    }
}
