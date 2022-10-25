package jkutkut.dam.ad.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestorContenido extends DefaultHandler {

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("Inicio del documento");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        System.out.println("\nuri: " + uri);
        System.out.println("localName: " + localName);
        System.out.println("qName: " + qName);

        System.out.println("\nAtributos de " + localName + ":");

        for (int i = 0; i < attributes.getLength(); i++) {
            System.out.println("uri de atributo " + (i+1) + ": " + attributes.getURI(i));
            System.out.println("localName de atributo " + (i+1) + ": " + attributes.getLocalName(i));
            System.out.println("qName de atributo " + (i+1) + ": " + attributes.getQName(i));
            System.out.println("valor de atributo " + (i+1) + ": " + attributes.getValue(i));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        System.out.println("Contenido de elemento final: ");
        String car = new String(ch, start, length);
        car = car.replace("[\t\n]", "");
        if (car.length() > 0) System.out.println("\t" + car);

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        System.out.println("Fin de la etiqueta " + localName);
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("Fin del documento");
    }
}

