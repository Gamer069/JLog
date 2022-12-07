package me.illia.jlog;

import java.util.Random;

public class JLogger {
    public static JLoggerStyle style;
    // The return type of the log() method represents did the program fail, did it succeed, or did it 'super failed' aka critical error
    public static int log(JLogLvl lvl, Object msg) {
        int result = -2;

        if (style == null) {
            result = -1;
            throw new IllegalArgumentException("You should call the method 'setStyle' before logging anything to the console!");
        }

        String stringMsg = msg.toString();

        switch (lvl) {
            case INFO -> {
                System.out.println("\u001B[32m" + stringMsg);
                result = 1;
            }

            case ERROR -> {
                System.out.println(style.error + stringMsg);
                result = 0;
            }

            case WARNING -> {
                System.out.println(style.warning + stringMsg);
                result = 1;
            }

            case CRITICAL -> {
                System.out.println(style.critical + "CRITICAL ERROR: " + style.criticalMsg + stringMsg);
                result = -1;
            }

            case NORMAL -> {
                System.out.println(stringMsg);
                result = 1;
            }
        }

        if (result <= 0) System.exit(result);
        
        return result;
    }

    public static void setStyle(JLoggerStyle style) {
        JLogger.style = style;
    }
}
