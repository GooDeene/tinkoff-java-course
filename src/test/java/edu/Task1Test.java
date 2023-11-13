package edu;

import com.sun.source.util.TaskListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.stream.Stream;


public class Task1Test {
    @Test
    public void correctWorksWithCorrectData() {
        var data = Stream.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        );
        var expectedAvgTime = Duration.ofMinutes(220);
        Assertions.assertEquals(expectedAvgTime, Task1.avarageSessionTime(data));
    }

    @Test
    public void correctWorksWithZeroAvarageTime() {
        var data = Stream.of("2020-01-01, 20:00 - 2020-01-01, 20:00");
        Assertions.assertEquals(Duration.ofSeconds(0), Task1.avarageSessionTime(data));
    }

    @Test
    public void skipIncorrectLines() {
        var data = Stream.of("2020-01-01, 20:00 - 2020-01-01, 22:00",
            "2020-01-01, 25:00 - 2020-01-01, 68:13");
        Assertions.assertEquals(Duration.ofMinutes(120), Task1.avarageSessionTime(data));
    }

    @Test
    public void skipNullStrings() {
        var data = Stream.of(null,
            "2020-01-01, 20:00 - 2020-01-01, 22:02");
        Assertions.assertEquals(Duration.ofMinutes(122), Task1.avarageSessionTime(data));
    }

    @Test
    public void correctWorksWithNullData() {
        Assertions.assertNull(Task1.avarageSessionTime(null));
    }

    @Test
    public void avgToStringWorksCorrectl() {
        var data =Stream.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20");
        Assertions.assertEquals("3ч 40м", Task1.avarageSessionTimeToString(data));
    }
}
