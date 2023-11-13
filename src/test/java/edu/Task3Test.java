package edu;

import net.bytebuddy.implementation.auxiliary.MethodCallProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.time.LocalDate;

public class Task3Test {
    @Test
    public void correctWorksWithCorrectExaplesData() {
        Assertions.assertEquals(LocalDate.of(2020, 10, 10), Task3.parseDate("2020-10-10").get());
        Assertions.assertEquals(LocalDate.of(2020, 12, 2), Task3.parseDate("2020-12-2").get());
        Assertions.assertEquals(LocalDate.of(1976, 3, 1), Task3.parseDate("1/3/1976").get());
        Assertions.assertEquals(LocalDate.of(20, 3, 1), Task3.parseDate("1/3/20").get());
        Assertions.assertEquals(LocalDate.now().plusDays(1), Task3.parseDate("tomorrow").get());
        Assertions.assertEquals(LocalDate.now().minusDays(1), Task3.parseDate("yesterday").get());
        Assertions.assertEquals(LocalDate.now(), Task3.parseDate("today").get());
        Assertions.assertEquals(LocalDate.now().minusDays(1), Task3.parseDate("1 day ago").get());
        Assertions.assertEquals(LocalDate.now().minusDays(2234), Task3.parseDate("2234 days ago").get());
    }

    @Test
    public void correctWorksWithIncorrectData() {
        Assertions.assertFalse(Task3.parseDate("2020-19-89").isPresent());
        Assertions.assertFalse(Task3.parseDate("2020-19-8").isPresent());
        Assertions.assertFalse(Task3.parseDate("1/19/1978").isPresent());
        Assertions.assertFalse(Task3.parseDate("toomorrow").isPresent());
        Assertions.assertFalse(Task3.parseDate("friday").isPresent());
        Assertions.assertFalse(Task3.parseDate("w day ago").isPresent());
        Assertions.assertFalse(Task3.parseDate("3 days later").isPresent());
    }
}
