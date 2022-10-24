package jkutkut.dam.ad.xstream.ej1.javabean;

public class Viaje {
    private final int id;
    private final String nombre;
    private final Ciudad ciudad;
    private final int dias;
    private final double precio;

    public Viaje(int id, String nombre, Ciudad ciudad, int dias, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.dias = dias;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public int getDias() {
        return dias;
    }

    public double getPrecio() {
        return precio;
    }
}
