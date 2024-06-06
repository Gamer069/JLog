package me.illia.jlog.config.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.illia.jlog.JLogConfigSettings;
import me.illia.jlog.JLogStyle;
import me.illia.jlog.config.JLogConfigParser;
import me.illia.jlog.other.JLogColor;

import java.util.Iterator;
import java.util.Map;

public class InternalJSONParser implements JLogConfigParser {
    @Override
    public void parse(String filename) {
        String contents = Utils.readFile(filename);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(contents);
            JsonNode styleElement = jsonNode.get("style");
            JLogStyle style = new JLogStyle();
            for (Iterator<Map.Entry<String, JsonNode>> it = styleElement.fields(); it.hasNext(); ) {
                Map.Entry<String, JsonNode> field = it.next();

                switch (field.getKey()) {
                    case "error" -> style.setError(parseColor(field.getValue().asText(), filename, "error"));
                    case "warning" -> style.setWarning(parseColor(field.getValue().asText(), filename, "warning"));
                    case "critical" -> style.setCritical(parseColor(field.getValue().asText(), filename, "critical"));
                    case "info" -> style.setInfo(parseColor(field.getValue().asText(), filename, "info"));
                    case "log_input" -> Utils.parseLogInput(field.getValue().asText(), false);
                    default -> System.err.println("Invalid element <" + field.getKey() + "> " + "in " + filename);
                }
            }
            JLogConfigSettings.style = style;
        } catch (JsonProcessingException e) {
            System.out.println("Failed to parse config " + filename);
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
