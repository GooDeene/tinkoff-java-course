package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test public void correctWorksWithEvenLenString() {
        String data = "123456";
        Assertions.assertEquals("214365", Task4.fixString(data));
    }

    @Test public void correctWorksWithOddLenString() {
        String data = "badce";
        Assertions.assertEquals("abcde", Task4.fixString(data));
    }

    @Test public void correctWorksWithEmptyString() {
        String data = "";
        Assertions.assertEquals("", Task4.fixString(data));
    }
}
