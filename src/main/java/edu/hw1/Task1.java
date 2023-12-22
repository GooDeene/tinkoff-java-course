package edu.hw1;

class Task1 {
    private Task1() {
    }

    private static final int SECONDS_IN_MINUTE = 60;

    public static int minutesToSeconds(String time) {
        String[] timeComponents = time.split(":");

        int minutes = Integer.parseInt(timeComponents[0]);
        int seconds = Integer.parseInt(timeComponents[1]);

        if (seconds >= SECONDS_IN_MINUTE || seconds < 0 || minutes < 0) {
            return -1;
        }

        return minutes * SECONDS_IN_MINUTE + seconds;
    }
}
