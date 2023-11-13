package edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Task5Test {
    @ParameterizedTest
    @ValueSource(strings = {
        "А123ВЕ777",
        "О777ОО177",
        "О001ОО01",
        "А123АА196"
    })
    public void correctWorksWithCorrectCarNumber(String carNumber) {
        Assertions.assertTrue(Task5.validateRussianCarNumber(carNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "123АВЕ777",
        "А123ВГ77",
        "А123ВЕ7777",
        "АААААА96"
    })
    public void correctWorksWithInCorrectCarNumber(String carNumber) {
        Assertions.assertFalse(Task5.validateRussianCarNumber(carNumber));
    }

    @Test
    public void correctWorksWithNull() {
        Assertions.assertFalse(Task5.validateRussianCarNumber(null));
    }
}

