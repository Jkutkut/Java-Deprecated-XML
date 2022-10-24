package jkutkut.dam.ad.xstream.ej1.main;

import jkutkut.dam.ad.xstream.ej1.javabean.Agencia;
import jkutkut.dam.ad.xstream.ej1.javabean.Ciudad;
import jkutkut.dam.ad.xstream.ej1.javabean.Viaje;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CrearXMLViajes {

    private static final String FILENAME = "res/jkutkut/dam/ad/xml/agencia.xml";

    private static final Object[][] data = {
        {1234, "Semana Santa en Rivera Maya", "MAD", "Madrid", "España", 7, 845},
        {1235, "Semana Santa en Barcelona", "BCN", "Barcelona", "España", 7, 845},
        {1236, "Semana Santa en Roma", "ROM", "Roma", "Italia", 7, 845},
        {1237, "Semana Santa en Lisboa", "LIS", "Lisboa", "Portugal", 7, 845},
        {1238, "Semana Santa en París", "PAR", "París", "Francia", 7, 845},
        {1239, "Semana Santa en Londres", "LON", "Londres", "Reino Unido", 7, 845},
        {1240, "Semana Santa en Nueva York", "NYC", "Nueva York", "Estados Unidos", 7, 845},
        {1241, "Semana Santa en San Francisco", "SFO", "San Francisco", "Estados Unidos", 7, 845},
        {1242, "Semana Santa en Los Ángeles", "LAX", "Los Ángeles", "Estados Unidos", 7, 845},
        {1243, "Semana Santa en Miami", "MIA", "Miami", "Estados Unidos", 7, 845},
        {1244, "Semana Santa en Las Vegas", "LAS", "Las Vegas", "Estados Unidos", 7, 845},
        {1245, "Semana Santa en Cancún", "CUN", "Cancún", "México", 7, 845},
        {1246, "Semana Santa en Acapulco", "ACA", "Acapulco", "México", 7, 845},
        {1247, "Semana Santa en Puerto Vallarta", "PVR", "Puerto Vallarta", "México", 7, 845},
        {1248, "Semana Santa en Cabo San Lucas", "SJD", "Cabo San Lucas", "México", 7, 845},
        {1249, "Semana Santa en Los Cabos", "SJD", "Los Cabos", "México", 7, 845},
        {1250, "Semana Santa en Cancún", "CUN", "Cancún", "México", 7, 845},
    };

    public static void main(String[] args) {
        Agencia agencia = loadAgencia();
        storeWithDom(agencia, FILENAME);
    }

    private static Agencia loadAgencia() {
        Agencia agencia = new Agencia();
        for (Object[] row : data) {
            agencia.addViaje(new Viaje(
                (int) row[0],
                (String) row[1],
                new Ciudad(
                    (String) row[2],
                    (String) row[3],
                    (String) row[4]
                ),
                (int) row[5],
                (int) row[6]
            ));
        }
        return agencia;
    }

    private static void storeWithDom(Agencia agencia, String filename) {
        System.out.println("Storing XML in the file " + filename + " using DOM");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();

            Document document = implementation.createDocument(null, "agencia", null);
            document.setXmlVersion("1.0");

            Node rootNode = document.getDocumentElement();
            Node nodeViaje, nodeId, nodeNombre, nodeCiudad, nodeDias, nodePrecio;
            for (Viaje viaje : agencia.getViajes()) {
                nodeViaje = document.createElement("viaje");

                nodeId = document.createElement("id");
                nodeId.appendChild(document.createTextNode(String.valueOf(viaje.getId())));
                nodeViaje.appendChild(nodeId);

                nodeNombre = document.createElement("nombre");
                nodeNombre.appendChild(document.createTextNode(viaje.getNombre()));
                nodeViaje.appendChild(nodeNombre);

                // Ciudad
                nodeCiudad = document.createElement("ciudad");

                Node nodeCodigo = document.createElement("codigo");
                nodeCodigo.appendChild(document.createTextNode(viaje.getCiudad().getCodigo()));
                nodeCiudad.appendChild(nodeCodigo);

                Node nodeNombreCiudad = document.createElement("nombreCiudad");
                nodeNombreCiudad.appendChild(document.createTextNode(viaje.getCiudad().getNombre()));
                nodeCiudad.appendChild(nodeNombreCiudad);

                Node nodePais = document.createElement("pais");
                nodePais.appendChild(document.createTextNode(viaje.getCiudad().getPais()));
                nodeCiudad.appendChild(nodePais);

                nodeViaje.appendChild(nodeCiudad);

                nodeDias = document.createElement("dias");
                nodeDias.appendChild(document.createTextNode(String.valueOf(viaje.getDias())));
                nodeViaje.appendChild(nodeDias);

                nodePrecio = document.createElement("precio");
                nodePrecio.appendChild(document.createTextNode(String.valueOf(viaje.getPrecio())));
                nodeViaje.appendChild(nodePrecio);

                rootNode.appendChild(nodeViaje);
            }

            Source source = new DOMSource(document);
            Result result = new StreamResult(new File(filename));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("XML stored in the file " + filename + " using DOM");
    }
}
