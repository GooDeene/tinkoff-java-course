package edu;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class Task1 {
    private static final int SECONDS_IN_MINUTE = 60;

    private Task1() {
    }

    public static Duration avarageSessionTime(Stream<String> sessionsTimestamps) {
        if (sessionsTimestamps == null) {
            return null;
        }
        var avarageInSeconds = sessionsTimestamps
            .filter(Task1::validateInputString)
            .map(x -> {
                var stamps = x.split(" - ");
                return Duration.between(parseDateTime(stamps[0]), parseDateTime(stamps[1]));
            })
            .mapToLong(Duration::toSeconds)
            .average();

        return avarageInSeconds.isPresent()
            ? Duration.ofSeconds((long) avarageInSeconds.getAsDouble())
            : Duration.ofSeconds(0);
    }

    public static String avarageSessionTimeToString(Stream<String> sessionTimestamps) {
        var avg = avarageSessionTime(sessionTimestamps);
        var result = new StringBuilder();
        if (avg != null) {
            result.append(avg.toHours());
            result.append("ч ");
            result.append(avg.toMinutes() - avg.toHours() * SECONDS_IN_MINUTE);
            result.append("м");
        }
        return result.toString();
    }

    @SuppressWarnings("LineLength")
    private static boolean validateInputString(String str) {
        return str != null && str.matches(
            "[0-9]{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[12]\\d|30|31), ([0-1]\\d|2[0-3]|0?[0-9]):([0-5]\\d|0?[0-9]) - [0-9]{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[12]\\d|30|31), ([0-1]\\d|2[0-3]|0?[0-9]):([0-5]\\d|0?[0-9])");
    }

    private static LocalDateTime parseDateTime(String input) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
        return LocalDateTime.parse(input, formatter);
    }
}
