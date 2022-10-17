package jkutkut.dam.ad.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

public class ReadXML {

    private static final String FILENAME = "res/jkutkut/dam/ad/xml/alumnos.xml";

    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = dbf.newDocumentBuilder();
            Document document = docBuilder.parse(FILENAME);

            printXML(document.getFirstChild(), 0);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (SAXException e) {
            e.printStackTrace();
            return;
        }

    }

    private static void printXML(Node node, int lvl) {
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
