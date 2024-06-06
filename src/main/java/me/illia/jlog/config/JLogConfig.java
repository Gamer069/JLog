package me.illia.jlog.config;

import me.illia.jlog.JLogFileParser;
import me.illia.jlog.config.internal.InternalJLCParser;
import me.illia.jlog.config.internal.InternalJSONParser;
import me.illia.jlog.config.internal.InternalXMLParser;
import me.illia.jlog.config.internal.InternalYMLParser;

public record JLogConfig(String filename, JLogConfigType type) {
    private static JLogConfigParser parser;

    public JLogConfig {
        switch (type) {
            case XML -> parser = new InternalXMLParser();
            case YAML -> parser = new InternalYMLParser();
            case JLC -> parser = new InternalJLCParser();
            case JSON -> parser = new InternalJSONParser();
        }

        parser.parse(JLogFileParser.parse(filename));
    }
}