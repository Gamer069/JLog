package me.illia.jlog;

import me.illia.jlog.log.JLogFile;
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
    private JLoggerStyle style = new JLoggerStyle();
    private String name;

    private JLogger(String name) {
        this.name = name;
    }

    public static String input() {
        IN.reset();
        String result = IN.nextLine();

        LOGS.add("(" + JLogTimestamp.formatted(new Date()) + ") " + "INPUT: " + result + '\n');

        return result;
    }

    public void log(JLogLvl lvl, Object msg) {
        String stringMsg = msg.toString();

        if (!stringMsg.endsWith("\r")) stringMsg += '\r';
        date = new Date();

        StringBuilder loggedMsg = new StringBuilder(JLogColor.CYAN.normalize() + "(" + JLogTimestamp.formatted(date) + ")" + JLogColor.RESET.normalize() + " ");

        StringBuilder logFileMsg = new StringBuilder("(" + JLogTimestamp.formatted(date) + ") ");

        switch (lvl) {
            case INFO -> {
                loggedMsg.append("[")
                        .append(name)
                        .append("] ")
                        .append(style.info.normalize())
                        .append("{INFO} ")
                        .append(stringMsg)
                        .append(JLogColor.RESET.normalize());

                logFileMsg.append("[")
                        .append(name)
                        .append("] ")
                        .append("{INFO} ")
                        .append(stringMsg);
            }
            case ERROR -> {
                loggedMsg.append("[")
                        .append(name)
                        .append("] ")
                        .append(style.error.normalize())
                        .append("{ERROR} ")
                        .append(stringMsg)
                        .append(JLogColor.RESET.normalize());

                logFileMsg.append("[")
                        .append(name)
                        .append("] ")
                        .append("{ERROR} ")
                        .append(stringMsg);
            }
            case WARNING -> {
                loggedMsg.append("[")
                        .append(name)
                        .append("] ")
                        .append(style.warning.normalize())
                        .append("{WARNING} ")
                        .append(stringMsg)
                        .append(JLogColor.RESET.normalize());

                logFileMsg.append("[")
                        .append(name)
                        .append("] ")
                        .append("{WARNING} ")
                        .append(stringMsg);
            }
            case CRITICAL -> {
                loggedMsg.append("[")
                        .append(name)
                        .append("] ")
                        .append(style.critical.normalize())
                        .append("{CRITICAL} ")
                        .append(stringMsg)
                        .append(JLogColor.RESET.normalize());

                logFileMsg.append("[")
                        .append(name)
                        .append("] ")
                        .append("{CRITICAL} ")
                        .append(stringMsg);
            }
            case NORMAL -> {
                loggedMsg.append("[")
                        .append(name)
                        .append("]")
                        .append("{NORMAL} ")
                        .append(stringMsg);

                logFileMsg.append("[")
                        .append(name)
                        .append("] ")
                        .append("{NORMAL} ")
                        .append(stringMsg);
            }

            default -> throw new IllegalStateException("Unexpected value: " + lvl);
        }


        LOGS.add(logFileMsg.toString());
        System.out.println(loggedMsg);
    }

    public void logf(JLogLvl lvl, Object msg, Object... args) {
        String stringMsg = msg.toString();

        if (!stringMsg.endsWith("\r")) stringMsg += '\r';
        stringMsg = JParser.parse(stringMsg, args);
        date = new Date();

        StringBuilder loggedMsg = new StringBuilder(JLogColor.CYAN.normalize() + "(" + JLogTimestamp.formatted(date) + ")" + JLogColor.RESET.normalize() + " ");

        StringBuilder logFileMsg = new StringBuilder("(" + JLogTimestamp.formatted(date) + ") ");

        switch (lvl) {
            case INFO -> {
                loggedMsg.append("[")
                        .append(name)
                        .append("] ")
                        .append(style.info.normalize())
                        .append("{INFO} ")
                        .append(stringMsg)
                        .append(JLogColor.RESET.normalize());

                logFileMsg.append("[")
                        .append(name)
                        .append("] ")
                        .append("{INFO} ")
                        .append(stringMsg);
            }
            case ERROR -> {
                loggedMsg.append("[")
                        .append(name)
                        .append("] ")
                        .append(style.error.normalize())
                        .append("{ERROR} ")
                        .append(stringMsg)
                        .append(JLogColor.RESET.normalize());

                logFileMsg.append("[")
                        .append(name)
                        .append("] ")
                        .append("{ERROR} ")
                        .append(stringMsg);
            }
            case WARNING -> {
                loggedMsg.append("[")
                        .append(name)
                        .append("] ")
                        .append(style.warning.normalize())
                        .append("{WARNING} ")
                        .append(stringMsg)
                        .append(JLogColor.RESET.normalize());

                logFileMsg.append("[")
                        .append(name)
                        .append("] ")
                        .append("{WARNING} ")
                        .append(stringMsg);
            }
            case CRITICAL -> {
                loggedMsg.append("[")
                        .append(name)
                        .append("] ")
                        .append(style.critical.normalize())
                        .append("{CRITICAL} ")
                        .append(stringMsg)
                        .append(JLogColor.RESET.normalize());

                logFileMsg.append("[")
                        .append(name)
                        .append("] ")
                        .append("{CRITICAL} ")
                        .append(stringMsg);
            }
            case NORMAL -> {
                loggedMsg.append("[")
                        .append(name)
                        .append("]")
                        .append("{NORMAL} ")
                        .append(stringMsg);

                logFileMsg.append("[")
                        .append(name)
                        .append("] ")
                        .append("{NORMAL} ")
                        .append(stringMsg);
            }

            default -> throw new IllegalStateException("Unexpected value: " + lvl);
        }


        LOGS.add(logFileMsg.toString());
        System.out.println(loggedMsg);
    }

    public void saveLog(String logPath, boolean dateMode) {
        /*
            in logPath, you can use '~' in the path (if you don't know, ~ means home directory).
            you can also use '*' in the path (* means project directory, see 'JLogFileParser.java' for more details.)
            make logPath empty to use the default path. ("")
         */


        if (dateMode) {
            new JLogFile(LOGS.toArray(), true, this);
        } else {
            LOG_PATH = logPath.isEmpty() || logPath.isBlank() ? LOG_PATH : logPath;
            new JLogFile(LOG_PATH, LOGS.toArray(), this);
        }
    }

    public static class Builder {
        private final JLogger cache;

        public Builder(String name) {
            this.cache = new JLogger(name);
            this.cache.name = name;
        }

        public Builder style(JLoggerStyle style) {
            this.cache.style = style;
            return this;
        }

        public JLogger build() {
            return cache;
        }
    }
}
