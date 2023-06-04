package me.illia.jlog.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogFile {
    private final Object[] LOGS;
    private String LOG_PATH;

    public LogFile(String LOG_PATH, Object[] LOGS) {
        this.LOG_PATH = LOG_PATH;
        this.LOGS = LOGS;

        createLogFile();
    }

    public void createLogFile() {
        if (LOG_PATH == null) {
            throw new RuntimeException("The log file path cannot be null. ");
        }
        // Format the LOG_PATH properly
        LOG_PATH = LOG_PATH.replace("~", System.getProperty("user.home"));
        // ~ = home dir,
        // * = project dir.
        // (eg. you have a maven project named 'test', '*' will be in the 'test' root project directory.
        LOG_PATH = LOG_PATH.replace("*", System.getProperty("user.dir"));

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
