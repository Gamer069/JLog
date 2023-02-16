package me.illia.jlog;

import java.util.Scanner;

public class JLogger {
    private static final Scanner IN = new Scanner(System.in);
    public static int RESULT = 1;
    private static JLoggerStyle style;

    // The return type of the log() method represents did the program fail, did it succeed, or did it 'super failed' aka critical error
    public static int log(JLogLvl lvl, Object msg) {
        if (style == null) {
            RESULT = -1;

            throw new IllegalArgumentException("You should call the 'setStyle' method before logging anything to the console!");
        }

        String stringMsg = msg.toString();

        if (!stringMsg.endsWith("\r")) stringMsg += '\r';

        switch (lvl) {
            case INFO -> {
                System.out.println(style.info.normalize() + stringMsg);

                RESULT = 1;
            }

            case ERROR -> {
                System.out.println(style.error.normalize() + stringMsg);

                RESULT = 0;
            }

            case WARNING -> {
                System.out.println(style.warning.normalize() + stringMsg);

                RESULT = 1;
            }

            case CRITICAL -> {
                System.out.println(style.critical.normalize() + "CRITICAL ERROR: " + stringMsg);

                RESULT = -1;
            }

            case NORMAL -> {
                System.out.println(stringMsg);

                RESULT = 1;
            }
        }

        if (RESULT <= 0) System.exit(RESULT);

        return RESULT;
    }

    public static void setStyle(JLoggerStyle style) {
        JLogger.style = style;
    }

    public static String getUserInput() {
        IN.reset();

        return IN.nextLine();
    }

    public static char getUserInputChar() {
        IN.reset();

        return IN.next().charAt(0);
    }
}
