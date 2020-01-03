package com.mango.zombies;

public class Time {

    //region Fields
    private int milliseconds;
    //endregion

    //region Public Methods
    /**
     * Gets the time in milliseconds.
     */
    public int totalMilliseconds() {
        return milliseconds;
    }

    /**
     * Gets the time in seconds.
     */
    public double totalSeconds() {
        return milliseconds / 1000.0;
    }

    /**
     * Gets the time in ticks.
     */
    public long totalTicks() {
        return (long)totalSeconds() * 20;
    }
    //endregion

    //region Public Static Methods
    public static Time fromMinutes(double minutes) {

        Time time = new Time();
        time.milliseconds = (int)(minutes * 60 * 1000);

        return time;
    }

    public static Time fromSeconds(double seconds) {

        Time time = new Time();
        time.milliseconds = (int)(seconds * 1000);

        return time;
    }

    public static Time fromTicks(double ticks) {

        Time time = new Time();
        time.milliseconds = (int)(ticks / 20 * 1000);

        return time;
    }
    //endregion
}
