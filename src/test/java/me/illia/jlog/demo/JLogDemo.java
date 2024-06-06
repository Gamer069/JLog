package me.illia.jlog.demo;

import me.illia.jlog.JLogger;
import me.illia.jlog.config.JLogConfig;
import me.illia.jlog.config.JLogConfigType;

public class JLogDemo {
    // The JLoggerStyle() without any args just creates a default style, but with args, just put in the ansi colors as args :)
    // That's why the args for that are Strings

    // if you create a JLogger.Builder without any args, it'll use the current thread name.
    public static JLogger.Builder buildr = new JLogger.Builder("JLogDemo");
    public static JLogger logger;


    public static void main(String[] args) {
        // parse the jlc config (custom format)
        new JLogConfig("?/jlc_example_config.jlc", JLogConfigType.JLC);

        // if you DONT apply any style and the config is already loaded properly, then it'll use the config style. Else, itll use the default one.
        logger = buildr.build();
        logger.info("This is an example log message");
        logger.errorf("This is an example log format message. Simple string: \"{}\". Simple format sequence: {/} (you can use a / between {/} to cancel it out)", "hello");
        logger.debug();
        logger.stats();
        logger.saveLog("?/latest.log");
    }
}
