package me.illia.jlog;

import me.illia.jlog.log.JLogFile;
import me.illia.jlog.other.JLogColor;
import me.illia.jlog.other.JLogTimestamp;

import java.util.*;

public class JLogger {
    private static final Scanner IN = new Scanner(System.in);
    public static Date date;
    public static String LOG_PATH = "*/latest.log";
    public static ArrayList<String> LOGS = new ArrayList<>();
    private final String name;
    private final HashMap<JLogLvl, Integer> stats = new HashMap<>();
    private JLogStyle style = new JLogStyle();
    private boolean enableStats = true; // enable stats by default

    private JLogger(String name) {
        for (JLogLvl lvl : JLogLvl.values()) {
            stats.put(lvl, 0);
        }
        this.name = name;
    }

    public String input() {
        IN.reset();
        String result = IN.nextLine();

        if (JLogConfigSettings.logInput)
            LOGS.add("(" + JLogTimestamp.formatted(new Date()) + ") [" + name + "] " + "{INPUT}" + " " + result + '\n');

        return result;
    }

    public String input(String prefix) {
        System.out.print(prefix);
        return input();
    }

    public void log(JLogLvl lvl, Object msg) {
        String stringMsg = msg.toString();

        if (!stringMsg.endsWith("\r")) stringMsg += '\r';
        date = new Date();

        StringBuilder loggedMsg = new StringBuilder(JLogColor.CYAN.normalize() + "(" + JLogTimestamp.formatted(date) + ")" + JLogColor.RESET.normalize() + " ");

        StringBuilder logFileMsg = new StringBuilder("(" + JLogTimestamp.formatted(date) + ") ");

        if (enableStats) stats.put(lvl, stats.get(lvl) + 1);

        switch (lvl) {
            case INFO -> {
                loggedMsg.append("[")
                        .append(name)
                        .append("] ")
                        .append(style.getInfo().normalize())
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
                        .append(style.getError().normalize())
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
                        .append(style.getWarning().normalize())
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
                        .append(style.getCritical().normalize())
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
                        .append("] ")
                        .append(style.getNormal().normalize())
                        .append("{NORMAL} ")
                        .append(stringMsg)
                        .append(JLogColor.RESET.normalize());

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
        log(lvl, stringMsg);
    }

    public void saveLog(String logPath) {
        /*
            in logPath, you can use '~' in the path (if you don't know, ~ means home directory).
            you can also use '?' in the path (? means project directory, see 'JLogFileParser.java' for more details.)
            make logPath empty to use the default path. ("")
         */
        boolean dateMode;
        dateMode = logPath.isEmpty();

        if (dateMode) {
            new JLogFile(LOGS.toArray(), true, this);
        } else {
            LOG_PATH = logPath;
            new JLogFile(LOG_PATH, LOGS.toArray(), this);
        }
    }

    public void stats() {
        enableStats = false;
        log(JLogLvl.INFO, "Log stats: ");
        for (JLogLvl lvl : JLogLvl.values()) {
            if (stats.get(lvl) == 0) continue;
            else log(lvl, stats.get(lvl));
        }
        enableStats = true; // if you're gonna use this function in the middle of ur code or smth
    }

    public boolean getStatsEnabled() {
        return enableStats;
    }

    public void setStatsEnabled(boolean enabled) {
        enableStats = enabled;
    }

    public void debug() {
        try {
            throw new RuntimeException("");
        } catch (RuntimeException e) {
            logf(JLogLvl.INFO, "{}: {}() (line {})", e.getStackTrace()[1].getFileName(), e.getStackTrace()[1].getMethodName(), e.getStackTrace()[1].getLineNumber());
        }
    }

    // aliases
    public void warning(String message) {
        log(JLogLvl.WARNING, message);
    }

    public void warn(String message) {
        log(JLogLvl.WARNING, message);
    }

    public void error(String message) {
        log(JLogLvl.ERROR, message);
    }

    public void critical(String message) {
        log(JLogLvl.CRITICAL, message);
    }

    public void crit(String message) {
        log(JLogLvl.CRITICAL, message);
    }

    public void info(String message) {
        log(JLogLvl.INFO, message);
    }
    // format

    public void infof(String message, Object... args) {
        logf(JLogLvl.INFO, message, args);
    }

    public void warningf(String message, Object... args) {
        logf(JLogLvl.WARNING, message, args);
    }

    public void warnf(String message, Object... args) {
        logf(JLogLvl.WARNING, message, args);
    }

    public void errorf(String message, Object... args) {
        logf(JLogLvl.ERROR, message, args);
    }

    public void errf(String message, Object... args) {
        logf(JLogLvl.ERROR, message, args);
    }

    public void criticalf(String message, Object... args) {
        logf(JLogLvl.CRITICAL, message, args);
    }

    public void critf(String message, Object... args) {
        logf(JLogLvl.CRITICAL, message, args);
    }

    //format end

    // aliases end
    public static class Builder {
        private final JLogger cache;

        public Builder(String name) {
            this.cache = new JLogger(name);
            this.cache.style = null;
        }

        public Builder() {
            this.cache = new JLogger(Thread.currentThread().getName());
        }

        public Builder style(JLogStyle style) {
            this.cache.style = style;
            return this;
        }

        public JLogger build() {
            if (this.cache.style == null) {
                this.cache.style = Objects.requireNonNullElseGet(JLogConfigSettings.style, JLogStyle::new);
            }
            return cache;
        }
    }
}
