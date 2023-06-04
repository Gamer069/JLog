package me.illia.jlog.demo;

import me.illia.jlog.JLogLvl;
import me.illia.jlog.JLogger;
import me.illia.jlog.JLoggerStyle;
import me.illia.jlog.other.JLogColor;

public class JLogDemo {
    // The JLoggerStyle() without any args just creates a default style, but with args, just put in the ansi colors as args :)
    // That's why the args for that are Strings
    public static final JLoggerStyle style = new JLoggerStyle();

    public static void initStyle() {
        style.setCritical(JLogColor.DARK_RED);
        style.setError(JLogColor.RED);
        style.setWarning(JLogColor.YELLOW);
        style.setInfo(JLogColor.GREEN);

        // Set the JLogger style
        JLogger.setStyle(style);
    }


    public static void main(String[] args) {
        initStyle();

        JLogger.log(JLogLvl.CRITICAL, "This is an example log message");
        JLogger.log(JLogLvl.WARNING, "This is another example of a log message");

        String input = JLogger.input();
        JLogger.log(JLogLvl.INFO, "The user sent: '" + input + "'");

        // An empty string makes the api save a log to the default path (project dir/latest.log). A * means the project directory, and ~ ofc means home directory.
        JLogger.saveLog("*/lol.log");
    }
}
