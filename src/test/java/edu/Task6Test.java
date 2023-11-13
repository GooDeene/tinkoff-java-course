package edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Task6Test {
    @ParameterizedTest
    @ValueSource(strings = {"abc", "acha", "aa", "hfgg", ""})
    public void correctWorksWithCorrectStrings(String subsequence) {
        Assertions.assertTrue(Task6.isStringSubsequenceOfLine(subsequence, "achfdbaabgabcaabg"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcd", "ahh", "aaaaaaaaa", "fh"})
    public void correctWorksWithInCorrectStrings(String subsequence) {
        Assertions.assertFalse(Task6.isStringSubsequenceOfLine(subsequence, "achfdbaabgabcaabg"));
    }

    @Test
    public void correctWorksWithEmptyFullString() {
        Assertions.assertFalse(Task6.isStringSubsequenceOfLine("abc", ""));
    }

    @Test
    public void correctWorksWithNullStrings() {
        Assertions.assertFalse(Task6.isStringSubsequenceOfLine(null, "abc"));
        Assertions.assertFalse(Task6.isStringSubsequenceOfLine("abc", null));
        Assertions.assertFalse(Task6.isStringSubsequenceOfLine(null, null));
    }
}

