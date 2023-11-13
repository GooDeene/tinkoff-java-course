package edu;

import java.util.regex.Pattern;

public class Task6 {
    private Task6() {}

    public static boolean isStringSubsequenceOfLine(String subsequence, String fullString) {
        var patternBuilder = new StringBuilder();
        for (var e : subsequence.toCharArray()) {
            patternBuilder.append(".*");
            patternBuilder.append(e);
        }
        patternBuilder.append(".*");
        var pattern = Pattern.compile(patternBuilder.toString());
        return pattern.matcher(fullString).matches();
    }
}
