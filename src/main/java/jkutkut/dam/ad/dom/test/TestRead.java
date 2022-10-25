package jkutkut.dam.ad.dom.test;

import jkutkut.dam.ad.dom.ReadXML;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TestRead {
    private static final String FILENAME = "res/jkutkut/dam/ad/xml/alumnos.xml";

    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = dbf.newDocumentBuilder();
            Document document = docBuilder.parse(FILENAME);

            ReadXML.printDocumentXML(document);
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
}
