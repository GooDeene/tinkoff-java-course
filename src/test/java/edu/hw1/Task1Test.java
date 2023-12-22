package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    void worksWithCorrectInputData() {
        String inputString = "999:22";
        var result = Task1.minutesToSeconds(inputString);
        Assertions.assertEquals(59962, result);
    }

    @Test
    void worksWithIncorrectSeconds() {
        String inputString = "10:60";
        var result = Task1.minutesToSeconds(inputString);
        Assertions.assertEquals(-1, result);
    }

    @Test
    void worksWithIncorrectMinutes() {
        String input = "-8:40";
        var result = Task1.minutesToSeconds(input);
        Assertions.assertEquals(-1, result);
    }

    @Test
    void worksWithCorrectDataAndZeroMinutes() {
        String input = "0000:18";
        var result = Task1.minutesToSeconds(input);
        Assertions.assertEquals(18, result);
    }

    @Test
    void correctWorksWithSecondsMoreSixty() {
        String input = "00:89";
        Assertions.assertEquals(-1, Task1.minutesToSeconds(input));
    }
}
