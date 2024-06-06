package me.illia.jlog.config.internal;

import me.illia.jlog.JLogConfigSettings;
import me.illia.jlog.JLogStyle;
import me.illia.jlog.config.JLogConfigParser;
import me.illia.jlog.other.JLogColor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

public class InternalXMLParser implements JLogConfigParser {
    @Override
    public void parse(String filename) {
        String contents = Utils.readFile(filename);
        try {
            // Create a DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file
            Document doc = builder.parse(new InputSource(new StringReader(contents)));

            // Get the root element
            Element rootElement = doc.getDocumentElement();

            // Iterate over child elements of the 'style' element
            NodeList styleElements = rootElement.getElementsByTagName("style");
            if (styleElements.getLength() > 0) {
                JLogStyle style = new JLogStyle();
                Element styleElement = (Element) styleElements.item(0);
                NodeList childNodes = styleElement.getChildNodes();

                for (int i = 0; i < childNodes.getLength(); i++) {
                    Node node = childNodes.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        String elementName = element.getTagName();
                        String elementValue = element.getTextContent();

                        System.out.println(elementName + "=" + elementValue);

                        // Perform specific actions based on element names and values
                        switch (elementName) {
                            case "error" -> style.setError(parseColor(elementValue, filename, "error"));
                            case "warning" -> style.setWarning(parseColor(elementValue, filename, "warning"));
                            case "critical" -> style.setCritical(parseColor(elementValue, filename, "critical"));
                            case "info" -> style.setInfo(parseColor(elementValue, filename, "info"));
                            case "log_input" -> Utils.parseLogInput(elementValue, true);
                            default -> System.err.println("Invalid element <" + elementName + "> " + "in " + filename);
                        }

                    }
                }

                JLogConfigSettings.style = style;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public JLogColor parseColor(String colorStr, String filename, String level) {
        String color = colorStr.toUpperCase();
        try {
            return (JLogColor) JLogColor.class.getDeclaredField(color).get(null);
        } catch (Exception e) {
            System.err.println("Invalid color in " + filename + ":" + level);
            return JLogColor.RESET;
        }
    }
}
