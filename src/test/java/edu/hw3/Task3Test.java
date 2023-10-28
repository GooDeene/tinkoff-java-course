package edu.hw3;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class Task3Test {
    @ParameterizedTest
    @ArgumentsSource(FreqDictArgumentsProvider.class)
    public void correctWorksWithDifferentCorrectData(Object[] inputArray, Object[] keys, Integer[] values) {
        var taskResult = Task3.freqDict(inputArray);
        for (int i = 0; i < keys.length; i++) {
            Assertions.assertEquals(values[i], taskResult.get(keys[i]));
        }
        Assertions.assertEquals(keys.length, taskResult.size());
    }

    @Test
    public void correctWorksWithEmptyArray() {
        var taskResult = Task3.freqDict(new Object[0]);
        Assertions.assertTrue(taskResult.isEmpty());
    }

    @Test
    public void correctWorksWithNull() {
        var taskResult = Task3.freqDict(null);
        Assertions.assertNull(taskResult);
    }
}

class FreqDictArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
            Arguments.of(
                new Object[] {"a", "bb", "a", "bb"},
                new Object[] {"a", "bb"},
                new Integer[] {2, 2}
            ),
            Arguments.of(
                new Object[] {"this", "and", "that", "and"},
                new Object[] {"this", "and", "that"},
                new Integer[] {1, 2, 1}
            ),
            Arguments.of(
                new Object[] {"код", "код", "код", "bug"},
                new Object[] {"код", "bug"},
                new Integer[] {3, 1}
            ),
            Arguments.of(
                new Object[] {1, 1, 2, 2},
                new Object[] {1, 2},
                new Integer[] {2, 2}
            ),
            Arguments.of(
                new Object[] {true, true, true, false},
                new Object[] {true, false},
                new Integer[] {3, 1}
            ),
            Arguments.of(
                new Object[] {3.14, 2.71, 2.71},
                new Object[] {3.14, 2.71},
                new Integer[] {1, 2}
            )
        );
    }
}
