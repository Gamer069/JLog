package me.illia.jlog.demo;

import me.illia.jlog.JLogLvl;
import me.illia.jlog.JLogger;
import me.illia.jlog.JLoggerStyle;

import me.illia.jlog.other.*;

public class JLogDemo {
    // The JLoggerStyle() without any args just creates a default style, but with args, just put in the ansi colors as args :)
    // That's why the args for that are Strings
    public static final JLoggerStyle style = new JLoggerStyle();

    public static void initStyle() {
        style.setCritical(JLogColor.DARK_RED);

        // Set the JLogger style
        JLogger.setStyle(style);

        JLogger.log(JLogLvl.CRITICAL, "WOW!!!!!");
    }


    public static void main(String[] args) {
        initStyle();

        JLogger.log(JLogLvl.CRITICAL, "WOW!");
    }
}
