package edu;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private Task2() {
    }

    private static final int[] YEAR_START_DATE = new int[] {1, 13};
    private static final int[] YEAR_END_DATE = new int[] {12, 31};

    public static List<LocalDate> getAllThirteenthFridays(int year) {
        var curDate = LocalDate.of(year, YEAR_START_DATE[0], YEAR_START_DATE[1]);
        var endDate = LocalDate.of(year, YEAR_END_DATE[0], YEAR_END_DATE[1]);
        var result = new ArrayList<LocalDate>();

        while (curDate.isBefore(endDate)) {
            if (curDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
                result.add(curDate);
            }
            curDate = curDate.plusMonths(1);
        }
        return result;
    }
}
