package me.illia.jlog.demo;

import me.illia.jlog.JLogLvl;
import me.illia.jlog.JLogger;
import me.illia.jlog.JLoggerStyle;
import me.illia.jlog.other.JLogColor;

public class JLogDemo {
    // The JLoggerStyle() without any args just creates a default style, but with args, just put in the ansi colors as args :)
    // That's why the args for that are Strings
    public static final JLoggerStyle style = new JLoggerStyle();
    public static JLogger.Builder buildr = new JLogger.Builder("JLogDemo");
    public static JLogger logger;

    public static void initStyle() {
        style.setCritical(JLogColor.DARK_RED);
        style.setError(JLogColor.RED);
        style.setWarning(JLogColor.YELLOW);
        style.setInfo(JLogColor.GREEN);

        // Set the JLogger style && init the logger
        logger = buildr.style(style).build();
    }


    public static void main(String[] args) {
        initStyle();

        logger.log(JLogLvl.CRITICAL, "This is an example log message");
        logger.log(JLogLvl.WARNING, "This is another example of a log message");

        String input = JLogger.input();
        logger.log(JLogLvl.INFO, "The user sent: '" + input + "'");

        // if dateMode is set to false,
        // an empty string makes the api save a log to the default path (project dir/latest.log), A * means the project directory, and ~ ofc means home directory.
        // Else, an empty string makes the api save the log like project dir/hour-minute-second.log (the time when the save operation occurrs).
        logger.saveLog("", true);
    }
}
