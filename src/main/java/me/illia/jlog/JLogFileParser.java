package me.illia.jlog;

public class JLogFileParser {
    // great for parsing a path
    public static String parse(String path) {
        // ~ = home dir,
        // * = project dir.
        // (eg. you have a maven project named 'test', '*' will be in the 'test' root project directory.)
        return path.replace("*", System.getProperty("user.dir")).replace("~", System.getProperty("user.home"));
    }
}
