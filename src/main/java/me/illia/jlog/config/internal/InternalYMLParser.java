package me.illia.jlog.config.internal;

import me.illia.jlog.JLogConfigSettings;
import me.illia.jlog.JLogStyle;
import me.illia.jlog.config.JLogConfigParser;
import me.illia.jlog.other.JLogColor;
import org.yaml.snakeyaml.Yaml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class InternalYMLParser implements JLogConfigParser {
    @Override
    public void parse(String filename) {
        String contents = Utils.readFile(filename);
        try (InputStream stream = new ByteArrayInputStream(contents.getBytes())) {
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(stream);
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                String elementName = entry.getKey();
                Object elementValue = entry.getValue();

                // Perform specific actions based on element names and values
                JLogStyle style = new JLogStyle();

                if (elementName.equals("style") && elementValue instanceof Map<?, ?> styleMap) {
                    for (Map.Entry<?, ?> styleEntry : styleMap.entrySet()) {
                        String styleElementName = styleEntry.getKey().toString();
                        String styleElementValue = styleEntry.getValue().toString();

                        switch (styleElementName) {
                            case "error" -> style.setError(parseColor(styleElementValue, filename, "error"));
                            case "warning" -> style.setWarning(parseColor(styleElementValue, filename, "warning"));
                            case "critical" -> style.setCritical(parseColor(styleElementValue, filename, "critical"));
                            case "info" -> style.setInfo(parseColor(styleElementValue, filename, "info"));
                            case "log_input" -> Utils.parseLogInput(styleElementValue, false);
                            default -> System.err.println("Invalid element <" + styleElementName + ">");
                        }
                    }
                }

                // Set the style or perform other actions as needed
                JLogConfigSettings.style = style;
            }
        } catch (IOException e) {
            System.err.println("Failed to close stream");
        }
    }

    public JLogColor parseColor(String color, String filename, String level) {
        try {
            return (JLogColor) JLogColor.class.getDeclaredField(color.toUpperCase()).get(null);
        } catch (Exception e) {
            System.err.println("Invalid color in " + filename + ":" + level);
            return JLogColor.RESET;
        }
    }
}
