package edu.hw3;

import java.util.ArrayList;
import java.util.List;
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
    public <T> void correctWorksWithDifferentCorrectData(List<T> inputArray, List<T> keys, Integer[] values) {
        var taskResult = Task3.freqDict(inputArray);
        for (int i = 0; i < keys.size(); i++) {
            Assertions.assertEquals(values[i], taskResult.get(keys.get(i)));
        }
        Assertions.assertEquals(keys.size(), taskResult.size());
    }

    @Test
    public void correctWorksWithEmptyArray() {
        var taskResult = Task3.freqDict(new ArrayList<Integer>());
        Assertions.assertTrue(taskResult.isEmpty());
    }

    @Test
    public void correctWorksWithNull() {
        Assertions.assertNull(Task3.freqDict(null));
    }
}

class FreqDictArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
            Arguments.of(
                new ArrayList<String>() {{
                    add("a");
                    add("bb");
                    add("a");
                    add("bb");
                }},
                new ArrayList<String>() {{
                    add("a");
                    add("bb");
                }},
                new Integer[] {2, 2}
            ),
            Arguments.of(
                new ArrayList<String>() {{
                    add("this");
                    add("and");
                    add("that");
                    add("and");
                }},
                new ArrayList<String>() {{
                    add("this");
                    add("and");
                    add("that");
                }},
                new Integer[] {1, 2, 1}
            ),
            Arguments.of(
                new ArrayList<String>() {{
                    add("код");
                    add("код");
                    add("код");
                    add("bug");
                }},
                new ArrayList<String>() {{
                    add("код");
                    add("bug");
                }},
                new Integer[] {3, 1}
            ),
            Arguments.of(
                new ArrayList<Integer>() {{
                    add(1);
                    add(1);
                    add(2);
                    add(2);
                }},
                new ArrayList<Integer>() {{
                    add(1);
                    add(2);
                }},
                new Integer[] {2, 2}
            ),
            Arguments.of(
                new ArrayList<Boolean>() {{
                    add(true);
                    add(true);
                    add(true);
                    add(false);
                }},
                new ArrayList<Boolean>() {{
                    add(true);
                    add(false);
                }},
                new Integer[] {3, 1}
            ),
            Arguments.of(
                new ArrayList<Double>() {{
                    add(3.14);
                    add(2.71);
                    add(2.71);
                }},
                new ArrayList<Double>() {{
                    add(3.14);
                    add(2.71);
                }},
                new Integer[] {1, 2}
            )
        );
    }
}
