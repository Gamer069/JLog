package me.illia.jlog.log;

import me.illia.jlog.JLogger;
import me.illia.jlog.other.JLogTimestamp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class LogFile {
    private final Object[] LOGS;
    private String LOG_PATH;

    public final JLogger logger;
    boolean dateMode = false;

    public LogFile(String logPath, Object[] logs, JLogger logger) {
        this.LOG_PATH = logPath;
        this.LOGS = logs;
        this.logger = logger;

        createLogFile();
    }

    public LogFile(Object[] LOGS, boolean dateMode, JLogger logger) {
        this.dateMode = dateMode;
        this.LOGS = LOGS;
        this.logger = logger;

        createLogFile();
    }

    public void createLogFile() {
        if (!dateMode && LOG_PATH == null) {
            throw new RuntimeException("You must specify a log file path when creating a log file.");
        }
        if (dateMode) {
            LOG_PATH = JLogTimestamp.formatted(new Date()).replace(":", "-") + ".log";
        } else {
            // Format the LOG_PATH properly
            LOG_PATH = LOG_PATH.replace("~", System.getProperty("user.home"));
            // ~ = home dir,
            // * = project dir.
            // (eg. you have a maven project named 'test', '*' will be in the 'test' root project directory.
            LOG_PATH = LOG_PATH.replace("*", System.getProperty("user.dir"));
        }

        try {
            writeLogFile();
        } catch (IOException e) {
            throw new RuntimeException("A file error has occurred while trying to create the log file. Error: \n" + e.getMessage() + "\n. Cause: \n" + e.getCause());
        }
    }

    public void writeLogFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_PATH))) {
            for (Object log : LOGS) {
                writer.write(log.toString());
            }
        }
    }
}
