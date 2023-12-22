package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Task5Test {
    @ParameterizedTest
    @ValueSource(ints = {11211230, 13001120, 23336014, 11, 222, 981115, 3333, 123312, 123, 44444})
    public void correctWorksWithRealPzlindromes(int input) {
        Assertions.assertTrue(Task5.isPalindromeDescendant(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {1234, 10, 1, 990, 0, 123322})
    public void correctWorksWithNotPalindromes(int input) {
        Assertions.assertFalse(Task5.isPalindromeDescendant(input));
    }
}
