package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task8Test {
    @Test
    public void correctWorksWithIntegersList() {
        var iterator = new BackwardIterator<>(List.of(1, 2, 3, 2));
        ArrayList<Integer> iterationResult = new ArrayList<>();
        while (iterator.hasNext()) {
            iterationResult.add(iterator.next());
        }

        var rightAnswers = new Integer[] {2, 3, 2, 1};
        for (int i = 0; i < rightAnswers.length; i++) {
            Assertions.assertEquals(rightAnswers[i], iterationResult.get(i));
        }

        var iterator2 = new BackwardIterator<>(List.of("a", "b", "c"));
    }

    @Test
    public void correctWorksWithStrings() {
        var iterator = new BackwardIterator<>(List.of("a", "b", "c"));
        ArrayList<String> iterationResult = new ArrayList<>();
        while (iterator.hasNext()) {
            iterationResult.add(iterator.next());
        }

        var rightAnswers = new String[] {"c", "b", "a"};
        for (int i = 0; i < rightAnswers.length; i++) {
            Assertions.assertEquals(rightAnswers[i], iterationResult.get(i));
        }
    }

    @Test
    public void throwsExceptionWithTryToNextEmptyIterator() {
        var iterator = new BackwardIterator<>(new ArrayList<>());
        assertThatThrownBy(iterator::next)
            .isInstanceOf(NullPointerException.class);
    }
}
