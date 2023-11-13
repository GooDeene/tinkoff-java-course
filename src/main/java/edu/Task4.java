package edu;

import java.util.regex.Pattern;

public class Task4 {
    private Task4() {}
    public static boolean validatePassword(String pass) {
        var pattern = Pattern.compile("[~!@#$%^&*|]");
        return pattern.matcher(pass).find();
    }
}
