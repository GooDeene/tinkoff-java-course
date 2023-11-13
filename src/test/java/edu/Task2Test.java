package edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class Task2Test {
    @Test
    public void correctWorksWithCorrectData() {
        Assertions.assertEquals("[1925-02-13, 1925-03-13, 1925-11-13]", Task2.getAllThirteenthFridays(1925).toString());
        Assertions.assertEquals("[2024-09-13, 2024-12-13]", Task2.getAllThirteenthFridays(2024).toString());
    }

    @Test
    public void correctWorksWithZeroYear() {
        Assertions.assertEquals("[0000-10-13]", Task2.getAllThirteenthFridays(0).toString());
    }

    @Test
    public void nextFridayCorrectWorksWithYearWith2Fridays() {
        Assertions.assertEquals(
            LocalDate.of(2024, 9, 13),
            new NextThirteenthFridayAdjuster().adjustInto(LocalDate.of(2024, 1, 1))
        );
    }

    @Test
    public void nextFridayCorrectWorksWithoutFridaysThisYear() {
        Assertions.assertEquals(LocalDate.of(2024, 9, 13),
            new NextThirteenthFridayAdjuster().adjustInto(LocalDate.of(2023, 12, 25)));
    }

    @Test
    public void nexFridayCorrectWorksWithNull() {
        Assertions.assertNull(new NextThirteenthFridayAdjuster().adjustInto(null));
    }
}
