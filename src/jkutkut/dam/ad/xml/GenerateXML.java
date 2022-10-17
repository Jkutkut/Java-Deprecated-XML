package jkutkut.dam.ad.xml;


import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class GenerateXML {
	private static final String FILENAME = "res/jkutkut/dam/ad/xml/alumnos.xml";

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			
			Document document = implementation.createDocument(null, "alumnos.xml",null);
			document.setXmlVersion("1.0");
			
			Element elemento = document.createElement("alumno");
			document.getDocumentElement().appendChild(elemento);
			
			Element elemento2 = document.createElement("nombre");
			Text texto = document.createTextNode("Sara");
			elemento2.appendChild(texto);
			elemento.appendChild(elemento2);
			
			elemento2 = document.createElement("edad");
			texto = document.createTextNode("22");
			elemento2.appendChild(texto);
			elemento.appendChild(elemento2);
			
			elemento = document.createElement("alumno");   
			document.getDocumentElement().appendChild(elemento);
			
			elemento2 = document.createElement("nombre");
			texto = document.createTextNode("Juan");
			elemento2.appendChild(texto);
			elemento.appendChild(elemento2);
			
			elemento2 = document.createElement("edad");
			texto = document.createTextNode("21");
			elemento2.appendChild(texto);
			elemento.appendChild(elemento2);
			
			Source source = new DOMSource(document);
			Result result = new StreamResult(new File(FILENAME));
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			
			transformer.transform(source,  result);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}



	}

}
