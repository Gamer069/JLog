package me.illia.jlog;

import java.util.Scanner;

public class JLogger {
    public static JLoggerStyle style;
    private static final Scanner inScanner = new Scanner(System.in);

    public static int RESULT = 1;
    // The return type of the log() method represents did the program fail, did it succeed, or did it 'super failed' aka critical error
    public static int log(JLogLvl lvl, Object msg) {
        if (style == null) {
            RESULT = -1;
            
            throw new IllegalArgumentException("You should call the method 'setStyle' before logging anything to the console!");
        }

        String stringMsg = msg.toString();

        switch (lvl) {
            case INFO -> {
                System.out.println(style.info + stringMsg);
                
                RESULT = 1;
            }

            case ERROR -> {
                System.out.println(style.error + stringMsg);

                RESULT = 0;
            }

            case WARNING -> {
                System.out.println(style.warning + stringMsg);

                RESULT = 1;
            }

            case CRITICAL -> {
                System.out.println(style.critical + "CRITICAL ERROR: " + style.criticalMsg + stringMsg);

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
        inScanner.reset();

        return inScanner.nextLine();
    }
}
