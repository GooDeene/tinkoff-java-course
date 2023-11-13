package edu;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextThirteenthFridayAdjuster implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        if (temporal == null) {
            return temporal;
        }
        int month = temporal.get(ChronoField.MONTH_OF_YEAR);
        int day = temporal.get(ChronoField.DAY_OF_MONTH);
        int year = temporal.get(ChronoField.YEAR);
        var date = LocalDate.of(year, month, day);

        var possibleFridays = Task2.getAllThirteenthFridays(year);
        for (var fridayDate : possibleFridays) {
            if (date.isBefore(fridayDate)) {
                return fridayDate;
            }
        }

        return adjustInto(temporal
            .with(ChronoField.YEAR, ++year)
            .with(ChronoField.MONTH_OF_YEAR, 1)
            .with(ChronoField.DAY_OF_MONTH, 1));
    }
}
