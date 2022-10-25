package jkutkut.dam.ad.xstream.ej1.javabean;

public class Ciudad {
    private final String codigo;
    private final String nombre;
    private final String pais;

    public Ciudad(String codigo, String nombre, String pais) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.pais = pais;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }
}
