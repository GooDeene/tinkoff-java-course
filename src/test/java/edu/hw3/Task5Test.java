package edu.hw3;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task5Test {
    @ParameterizedTest
    @ArgumentsSource(ContactsArgumentsProvider.class)
    public void correctWorksWithCorrectContactsAsc(String[] inputData, String[] rightOrder) {
        var taskResult = Task5.parseContacts(inputData, "ASC");
        Assertions.assertEquals(rightOrder.length, taskResult.length);
        for (int i = 0; i < inputData.length; i++) {
            Assertions.assertEquals(Contact.withFullName(rightOrder[i]), taskResult[i]);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ContactsArgumentsProvider.class)
    public void correctWorksWithCorrectContactsDesc(String[] inputData, String[] reversedOrder) {
        var taskResult = Task5.parseContacts(inputData, "DESC");
        Assertions.assertEquals(reversedOrder.length, taskResult.length);
        for (int i = 0; i < inputData.length; i++) {
            Assertions.assertEquals(Contact.withFullName(reversedOrder[reversedOrder.length - 1 - i]), taskResult[i]);
        }
    }

    @Test
    public void correctWorksWithEmptyInput() {
        Assertions.assertEquals(0, Task5.parseContacts(new String[0], "ASC").length);
    }

    @Test
    public void correctWorksWithNullAndIncorrectSortingOrder() {
        assertThatThrownBy(() -> Task5.parseContacts(null, "ASC"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Список контактов не может быть Null и/или ошибка в порядке сортировки!");
        assertThatThrownBy(() -> Task5.parseContacts(new String[] {"test"}, "AVADA"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Список контактов не может быть Null и/или ошибка в порядке сортировки!");
    }
}

class ContactsArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
            Arguments.of(
                new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"},
                new String[] {"Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"}
            ),
            Arguments.of(
                new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"},
                new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"}
            ),
            Arguments.of(
                new String[] {"Bb", "Cc", "Aa Aa"},
                new String[] {"Aa Aa", "Bb", "Cc"}
            )
        );
    }
}
