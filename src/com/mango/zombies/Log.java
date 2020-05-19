package com.mango.zombies;

public class Log {

    //region Constant Values
    public static final String PREFIX = "[Zombies]";
    //endregion

    //region Public Static Methods
    /**
     * Logs formatted information to the console.
     * @param message The message to log.
     */
    public static void information(String message) {
        System.out.println(PREFIX + " " + message);
    }
    //endregion
}
