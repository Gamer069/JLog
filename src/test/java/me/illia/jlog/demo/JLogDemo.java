package me.illia.jlog.demo;

import me.illia.jlog.*;

public class JLogDemo {
    // The JLoggerStyle() Without any args just creates a default style, but with args, just put in the ansi colors as args :)
    // That's why the args for that are Strings
    public static final JLoggerStyle style = new JLoggerStyle();
    public static void main(String[] args) {
        // Set the JLogger style
        JLogger.setStyle(style);

        // Print out 'Hello JLog!' yellow
        JLogger.log(JLogLvl.WARNING, "Hello JLog!");
    }
}
