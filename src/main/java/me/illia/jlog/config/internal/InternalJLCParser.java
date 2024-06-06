package me.illia.jlog.config.internal;

import me.illia.jlog.JLogConfigSettings;
import me.illia.jlog.JLogStyle;
import me.illia.jlog.config.JLogConfigParser;
import me.illia.jlog.other.JLogColor;

import java.util.HashMap;
import java.util.Map;

public class InternalJLCParser implements JLogConfigParser {
    /**
     * Parses the file and sets JLogConfigSettings. style to the parsed configuration. This is called by #init ( java. util. Map )
     *
     * @param filename - the name of the config file
     */
    @Override
    public void parse(String filename) {
        String contents = Utils.readFile(filename);
        Map<String, String> config = parseToMap(contents);
        JLogStyle style = new JLogStyle();
        for (Map.Entry<String, String> entry : config.entrySet()) {
            // Set style style for the given entry.
            switch (entry.getKey()) {
                case "error" -> style.setError(parseColor(entry.getValue(), filename, "error"));
                case "warning" -> style.setWarning(parseColor(entry.getValue(), filename, "warning"));
                case "critical" -> style.setCritical(parseColor(entry.getValue(), filename, "critical"));
                case "info" -> style.setInfo(parseColor(entry.getValue(), filename, "info"));
                case "normal" -> style.setNormal(parseColor(entry.getValue(), filename, "normal"));
                case "log_input" -> Utils.parseLogInput(entry.getValue(), true);
                default -> System.err.println("Invalid element <" + entry.getKey() + "> " + "in " + filename);
            }
        }
        JLogConfigSettings.style = style;
    }

    /**
     * Parses the contents of the config file into a Map. This is used to parse the configuration file and return the result of the parse.
     *
     * @param contents - The contents of the config file. Must be in the format key : value
     * @return A Map containing the structure of the parsed config
     */
    private Map<String, String> parseToMap(String contents) {
        Map<String, String> config = new HashMap<>();
        String[] values = contents.split(":");
        boolean isValue = false;
        String curValue = null;
        String curKey = null;
        // Sets the current value of the config.
        for (int i = 0; i < values.length; i++) {
            String[] values1 = values[i].split("\n");
            // Sets the current value of the config.
            for (int x = 0; x < values1.length; x++) {
                String replaced = values1[x].replace("[", "").replace("]", "").trim();

                // Check if the value is a value.
                if (x % 2 == 0) {
                    // Set the value to true.
                    if (x != 0 || i != 0) {
                        isValue = true;
                    }
                } else {
                    // Sets the value to false.
                    if (i != 0) {
                        isValue = false;
                    }
                }


                // Sets the current value and key.

                if (isValue) {
                    curValue = replaced;
                } else {
                    curKey = replaced;
                }

                // Set the current value of the current key.
                if (curKey != null && curValue != null) {
                    config.put(curKey, curValue);
                }
            }
        }
        return config;
    }


    /**
     * Parses a color string and returns a JLogColor.
     *
     * @param color    - The color string to parse. Must be a valid JLogColor (eg. "RED", "YELLOW", etc.)
     * @param filename - The name of the config file (used for error handling)
     * @param level    - The level
     */

    public JLogColor parseColor(String color, String filename, String level) {
        try {
            return (JLogColor) JLogColor.class.getDeclaredField(color).get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.err.println("Invalid color in " + filename + ":" + level);
            return JLogColor.RESET;
        }
    }
}
