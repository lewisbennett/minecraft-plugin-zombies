package com.mango.zombies.helper;

public class TickUtils {

    //region Public Static Methods
    /**
     * Calculates how many ticks are in a number of seconds.
     * @param seconds The number of seconds to calculate ticks for.
     */
    public static long ticksFromSeconds(int seconds) {
        return seconds * 20;
    }
    //endregion
}
