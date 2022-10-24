package jkutkut.dam.ad.dom.test.Bin2XML;

import jkutkut.dam.ad.dom.ReadXML;
import jkutkut.dam.ad.dom.test.Bin2XML.beans.Empleado;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

/**
 * Crear una clase que genere un fichero binario secuencial
 * (empleadosObj.dat) para almacenar información de 5 objetos
 * de tipo Empleado. Para cada empleado se almacenará un id, el
 * nombre, el número de departamento al que pertenece y su
 * salario.
 */
public class Binary2XML {
    private static final String[] nombres = {"Alberto", "Guillermo", "Alejandro", "Ana", "Patricia"};
    private static final int[] departamentos = {10, 20, 30, 20, 10};
    private static final double[] salarios = {2000.00, 1500.50, 3000.40, 2300.60, 1900.10};

    private static final String BIN_FILENAME = "res/jkutkut/dam/ad/xml/empleados.dat";
    private static final String XML_FILENAME = "res/jkutkut/dam/ad/xml/empleados.xml";

    public static void main(String[] args) throws IOException {
        storeEmpleadosAsBin(
            importEmpleados(),
            BIN_FILENAME
        );
        storeEmpleadosAsXML(
            readEmpleadosFromBin(BIN_FILENAME),
            "empleados",
            XML_FILENAME
        );

        System.out.println("Done!");
        System.out.println("Check the files: " + BIN_FILENAME + " and " + XML_FILENAME);
        ReadXML.printDocumentXML(XML_FILENAME);
    }

    private static void storeEmpleadosAsBin(Empleado[] importEmpleados, String binFilename) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(binFilename));
        System.out.println("Storing empleados in the file " + binFilename);
        for (Empleado e : importEmpleados) {
            System.out.print(".");
            oos.writeObject(e);
        }
        System.out.println("\nEmpleados stored at " + binFilename);
    }

    private static Empleado[] readEmpleadosFromBin(String binFilename) throws IOException {
        System.out.println("Reading empleados from the file " + binFilename);
        ObjectInputStream ios = new ObjectInputStream(new FileInputStream(binFilename));
        ArrayList<Empleado> lst = new ArrayList<>();
        Object p;
        while (true) {
            try {
                p = ios.readObject();
                if (p instanceof Empleado) {
                    System.out.print(".");
                    lst.add((Empleado) p);
                }
                else
                    throw new ClassNotFoundException("The objects here should be of class Producto");
            }
            catch (EOFException e) { // End of file
//                System.out.println("EOFException: " + e.getMessage());
                break;
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        ios.close();
        System.out.println("\nEmpleados read from " + binFilename);
        Empleado[] arr = new Empleado[0];
        return lst.toArray(arr);
    }

    private static void storeEmpleadosAsXML(Empleado[] empleados, String root, String filename) {
        System.out.println("Storing empleados as XML in the file " + filename);
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();

            Document document = implementation.createDocument(null, root,null);
            document.setXmlVersion("1.0");

            Node rootNode = document.getDocumentElement();
            for (Empleado e : empleados) {
                System.out.print(".");
                Element empleado = document.createElement("empleado");
                String[] values = e.toStringArray();
                for (int i = 0; i < Empleado.ATTRIBUTES.length; i++) {
                    Element field = document.createElement(Empleado.ATTRIBUTES[i]);
                    Text text = document.createTextNode(values[i]);
                    field.appendChild(text);
                    empleado.appendChild(field);
                }
                rootNode.appendChild(empleado);
            }

            Source source = new DOMSource(document);
            Result result = new StreamResult(new File(filename));

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
        System.out.println("\nEmpleados stored as XML in the file " + filename);
    }

    private static Empleado[] importEmpleados() {
        System.out.println("Importing empleados");
        Empleado[] empleados = new Empleado[nombres.length];
        for (int i = 0; i < empleados.length; i++) {
            System.out.print(".");
            empleados[i] = new Empleado(
                i + 1,
                nombres[i],
                departamentos[i],
                salarios[i]
            );
        }
        System.out.println("\nEmpleados imported");
        return empleados;
    }

}
