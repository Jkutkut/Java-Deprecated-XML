package jkutkut.dam.ad.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.ArrayList;

/**
 * Class with the logic needed to read an XML file.
 *
 * @author jkutkut - Jorge Re
 */
public class ReadXML {
    /**
     * Prints the given node on the standard output.
     * @param node Node to print.
     * @param lvl Level of indentation (0 is no indentation).
     */
    public static void printXML(Node node, int lvl) {
        printXML(String.format("<%s>", node.getNodeName()), lvl);
        NodeList children = node.getChildNodes();
        Node child;
        String content;
        for (int i = 0; i < children.getLength(); i++) {
            child = children.item(i);
            if (child.getNodeType() == Node.TEXT_NODE) {
                content = child.getTextContent().trim();
                if (!content.isEmpty())
                    printXML(
                        content,
                        lvl + 1
                    );
            }
            else if (child.getNodeType() == Node.ELEMENT_NODE) {
                printXML(child, lvl + 1);
            }
        }
        printXML(String.format("</%s>", node.getNodeName()), lvl);
    }

    private static void printXML(String content, int lvl) {
        System.out.printf("%s%s\n", getTabs(lvl), content);
    }

    // TABS
    private static ArrayList<String> tabs = null;

    /**
     * Returns a string with the given number of tabs.
     * @param lvl Number of tabs.
     * @return String with the given number of tabs.
     */
    private static String getTabs(int lvl) {
        if (tabs == null) {
            tabs = new ArrayList<String>();
            tabs.add("");
        }
        if (lvl >= tabs.size())
            while (lvl >= tabs.size())
                tabs.add(tabs.get(tabs.size() - 1) + "  ");
        return tabs.get(lvl);
    }
}
