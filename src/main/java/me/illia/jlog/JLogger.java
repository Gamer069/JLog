package me.illia.jlog;

import java.util.Scanner;

public class JLogger {
    public static JLoggerStyle style;
    private static final Scanner inScanner = new Scanner(System.in);
    // The return type of the log() method represents did the program fail, did it succeed, or did it 'super failed' aka critical error
    public static int log(JLogLvl lvl, Object msg, int count) {
        int result = -2;

        if (style == null) {
            result = -1;
            throw new IllegalArgumentException("You should call the method 'setStyle' before logging anything to the console!");
        }

        String stringMsg = msg.toString();

        switch (lvl) {
            case INFO -> {
                for (int i = 1; i <= count; i++) {
                    System.out.println(style.info + stringMsg);
                }
                result = 1;
            }

            case ERROR -> {
                for (int i = 1; i <= count; i++) {
                    System.out.println(style.error + stringMsg);
                }
                result = 0;
            }

            case WARNING -> {
                for (int i = 1; i <= count; i++) {
                    System.out.println(style.warning + stringMsg);
                }

                result = 1;
            }

            case CRITICAL -> {
                for (int i = 1; i <= count; i++) {
                    System.out.println(style.critical + "CRITICAL ERROR: " + style.criticalMsg + stringMsg);
                }
                result = -1;
            }

            case NORMAL -> {
                for (int i = 1; i <= count; i++) {
                    System.out.println(stringMsg);
                }
                result = 1;
            }
        }

        if (result <= 0) System.exit(result);
        
        return result;
    }

    public static void setStyle(JLoggerStyle style) {
        JLogger.style = style;
    }

    public static String getUserInput() {
        inScanner.reset();

        return inScanner.nextLine();
    }
}
