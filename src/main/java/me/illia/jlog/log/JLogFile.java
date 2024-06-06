package me.illia.jlog.log;

import me.illia.jlog.JLogFileParser;
import me.illia.jlog.JLogger;
import me.illia.jlog.other.JLogTimestamp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class JLogFile {
    private final Object[] LOGS;
    private String LOG_PATH;

    public final JLogger logger;
    boolean dateMode = false;

    public JLogFile(String logPath, Object[] logs, JLogger logger) {
        this.LOG_PATH = logPath;
        this.LOGS = logs;
        this.logger = logger;

        createLogFile();
    }

    public JLogFile(Object[] logs, boolean dateMode, JLogger logger) {
        this.dateMode = dateMode;
        this.LOGS = logs;
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
            LOG_PATH = JLogFileParser.parse(LOG_PATH);
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
