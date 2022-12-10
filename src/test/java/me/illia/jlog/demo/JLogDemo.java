package me.illia.jlog.demo;

import me.illia.jlog.*;

public class JLogDemo {
    // The JLoggerStyle() Without any args just creates a default style, but with args, just put in the ansi colors as args :)
    // That's why the args for that are Strings
    public static final JLoggerStyle style = new JLoggerStyle();
    public static void main(String[] args) {
        // Get the user input
        String input = JLogger.getUserInput();

        // Set the JLogger style
        JLogger.setStyle(style);

        // Print out the user input green
        JLogger.log(JLogLvl.INFO, input);
    }
}
