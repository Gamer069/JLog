package me.illia.jlog.config.internal;

import me.illia.jlog.JLogConfigSettings;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utils {
    public static String readFile(String filename) {
        StringBuilder result = new StringBuilder();
        File file = new File(filename);
        if (file.exists() && file.isFile() && file.canRead()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    result.append(data).append("\n");
                }
            } catch (FileNotFoundException ignored) {
                /*
                 * we ignore the exception because the if statement has already checked if the file exists
                 * in other words, this exception shouldn't be thrown
                 */
            }
        } else {
            throw new RuntimeException("Failed to read config (check that the config file actually exists and is a file, and is readable by this process)");
        }
        return result.toString();
    }

    public static void parseLogInput(String val, boolean ignoreCase) {
        if (ignoreCase) {
            val = val.toLowerCase();
        }
        if (!val.equals("true") && !val.equals("false")) {
            System.err.println("Invalid value at log_input in your config");
            return;
        }
        JLogConfigSettings.logInput = Boolean.parseBoolean(val);
    }
}
