package jkutkut.dam.ad.sax;

import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class ReadXMLSax {

    private static final String FILENAME = "AndroidManifest.xml";

    public static void main(String[] args) {
        try {
            XMLReader procXML = XMLReaderFactory.createXMLReader();
            GestorContenido gestor = new GestorContenido();
            procXML.setContentHandler(gestor);

            InputSource fileXML = new InputSource(FILENAME);
            procXML.parse(fileXML);

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}