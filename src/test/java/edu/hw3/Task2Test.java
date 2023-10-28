package edu.hw3;

import java.util.MissingFormatArgumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task2Test {
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
        "()()() | [(), (), ()]",
        "((())) | [((()))]",
        "((()))(())()()(()()) | [((())), (()), (), (), (()())]",
        "((())())(()(()())) | [((())()), (()(()()))]"
    })
    public void correctWorksWithNormalBracketsSequences(String sequence, String answer) {
        Assertions.assertEquals(answer, Task2.clusterize(sequence).toString());
    }

    @Test
    public void correctWorksWithEmptyString() {
        Assertions.assertEquals(0, Task2.clusterize("").size());
    }

    @Test
    public void correctWorksWithNull() {
        Assertions.assertEquals(0, Task2.clusterize(null).size());
    }

    @ParameterizedTest
    @ValueSource(strings = {")(", "((())))", "()(())())"})
    public void throwExceptionWithIncorrectStrings(String input) {
        assertThatThrownBy(() -> Task2.clusterize(input))
            .isInstanceOf(MissingFormatArgumentException.class);
    }
}
