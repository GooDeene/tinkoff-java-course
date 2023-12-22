package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task3Test {
    @Test
    public void correctTrueWithCorrectData() {
        var array1 = new Integer[] {1, 2, 3, 4};
        var array2 = new Integer[] {0, 6};
        Assertions.assertTrue(Task3.isNestable(array1, array2));
    }

    @Test
    public void correctFalseWithCorrectData() {
        var array1 = new Integer[] {9, 9, 8};
        var array2 = new Integer[] {8, 9};
        Assertions.assertFalse(Task3.isNestable(array1, array2));
    }

    @Test
    public void correctWorksWithEmptyArray() {
        var array1 = new Integer[] {};
        var array2 = new Integer[] {8, 9};
        Assertions.assertFalse(Task3.isNestable(array1, array2));
    }
}
