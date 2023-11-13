package edu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

public class Task3 {
    private Task3() {
    }

    static public Optional<LocalDate> parseDate(String string) {
        Map<Pattern, Validator> patterns = Map.of(
            Pattern.compile("[0-9]{4}-(0?[1-9]|1[0-2])-([12]\\d|30|31|0?[1-9])"),
            new Matcher1(),
            Pattern.compile("([12]\\d|30|31|0?[1-9])/(0?[1-9]|1[0-2])/[0-9]{1,4}"),
            new Matcher2(),
            Pattern.compile("tomorrow|today|yesterday"),
            new Matcher3(),
            Pattern.compile("\\d+ days? ago"),
            new Matcher4()
        );

        for (var pattern : patterns.keySet()) {
            if (pattern.matcher(string).matches()) {
                return Optional.of(patterns.get(pattern).validate(string));
            }
        }

        return Optional.empty();
    }

    private interface Validator {
        LocalDate validate(String str);
    }

    private static class Matcher1 implements Validator {
        @Override public LocalDate validate(String str) {
            return LocalDate.parse(str, DateTimeFormatter.ofPattern("yyyy-M-d"));
        }
    }

    private static class Matcher2 implements Validator {
        @Override public LocalDate validate(String str) {
            return LocalDate.parse(str, DateTimeFormatter.ofPattern("d/M/y"));
        }
    }

    private static class Matcher3 implements Validator {
        @Override public LocalDate validate(String str) {
            return switch (str) {
                case "tomorrow" -> LocalDate.now().plusDays(1);
                case "yesterday" -> LocalDate.now().minusDays(1);
                default -> LocalDate.now();
            };
        }
    }

    private static class Matcher4 implements Validator {
        @Override public LocalDate validate(String str) {
            return LocalDate.now().minusDays(Long.parseLong(str.split(" ")[0]));
        }
    }
}


