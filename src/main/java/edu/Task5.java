package edu;

import java.util.regex.Pattern;

public class Task5 {
    private Task5() {

    }

    public static boolean validateRussianCarNumber(String carNumber) {
        if (carNumber == null) {
            return false;
        }
        var pattern = Pattern.compile("[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}");
        return pattern.matcher(carNumber).matches();
    }
}
