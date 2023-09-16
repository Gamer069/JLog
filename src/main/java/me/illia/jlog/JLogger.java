package me.illia.jlog;

import me.illia.jlog.log.LogFile;
import me.illia.jlog.other.JLogColor;
import me.illia.jlog.other.JLogTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class JLogger {
    private static final Scanner IN = new Scanner(System.in);
    public static Date date;
    public static String LOG_PATH = "*/latest.log";
    public static ArrayList<String> LOGS = new ArrayList<>();
    private static JLoggerStyle style = new JLoggerStyle();

    public static void log(JLogLvl lvl, Object msg) {
        String stringMsg = msg.toString();

        if (!stringMsg.endsWith("\r")) stringMsg += '\r';
        date = new Date();

        StringBuilder loggedMsg = new StringBuilder("{" + JLogTimestamp.formatted(date) + "}: ");

        StringBuilder logFileMsg = new StringBuilder("{" + JLogTimestamp.formatted(date) + "}: ");

        switch (lvl) {
            case INFO -> {
                loggedMsg.append(style.info.normalize());
                loggedMsg.append("INFO: ");
                loggedMsg.append(stringMsg);
                loggedMsg.append(JLogColor.RESET.normalize());

                logFileMsg.append("INFO: ");
                logFileMsg.append(stringMsg);
            }
            case ERROR -> {
                loggedMsg.append(style.error.normalize());
                loggedMsg.append("ERROR: ");
                loggedMsg.append(stringMsg);
                loggedMsg.append(JLogColor.RESET.normalize());

                logFileMsg.append("ERROR: ");
                logFileMsg.append(stringMsg);
            }
            case WARNING -> {
                loggedMsg.append(style.warning.normalize());
                loggedMsg.append("WARNING: ");
                loggedMsg.append(stringMsg);
                loggedMsg.append(JLogColor.RESET.normalize());

                logFileMsg.append("WARNING: ");
                logFileMsg.append(stringMsg);
            }
            case CRITICAL -> {
                loggedMsg.append(style.critical.normalize());
                loggedMsg.append("CRITICAL ERROR: ");
                loggedMsg.append(stringMsg);
                loggedMsg.append(JLogColor.RESET.normalize());

                logFileMsg.append("CRITICAL ERROR: ");
                logFileMsg.append(stringMsg);
            }
            case NORMAL -> System.out.println(stringMsg);

            default -> throw new IllegalStateException("Unexpected value: " + lvl);
        }

        if (lvl != JLogLvl.NORMAL) {
            LOGS.add(logFileMsg.toString());
            System.out.println(loggedMsg);
        } else {
            LOGS.add(stringMsg);
        }
    }

    public static void setStyle(JLoggerStyle style) {
        // Check if it's null, then don't set it.

        // Setting the style to default by using null in setStyle is not recommended. The style is already set to default.

        if (style != null) {
            JLogger.style = style;
        }
    }

    public static String input() {
        IN.reset();

        return IN.nextLine();
    }

    public static void saveLog(String logPath) {
        /*
            in logPath, you can use '~' in the path,
            you can also use '*' in the path ('*' means the root project directory, where pom.xml/build.gradle is),
            make logPath empty to use the default path.
         */

        LOG_PATH = logPath.isEmpty() || logPath.isBlank() ? LOG_PATH : logPath;

        new LogFile(LOG_PATH, LOGS.toArray());
    }
}
