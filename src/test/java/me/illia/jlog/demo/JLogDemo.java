package me.illia.jlog.demo;

import me.illia.jlog.*;

public class JLogDemo {
    public static final JLoggerStyle style = new JLoggerStyle();
    public static void main(String[] args) {
        JLogger.setStyle(style);
        JLogger.log(JLogLvl.NORMAL, "Hello JLog!");
    }
}
