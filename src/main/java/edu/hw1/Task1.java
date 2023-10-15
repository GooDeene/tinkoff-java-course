package edu.hw1;

public class Task1 {
    public static int minutesToSeconds(String time) {
        String[] timeComponents = time.split(":");

        int minutes = Integer.parseInt(timeComponents[0]);
        int seconds = Integer.parseInt(timeComponents[1]);

        if (seconds >= 60 || seconds < 0 || minutes < 0) {
            return -1;
        }

        return minutes * 60 + seconds;
    }
}
