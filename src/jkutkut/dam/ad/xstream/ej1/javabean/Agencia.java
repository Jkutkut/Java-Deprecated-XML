package jkutkut.dam.ad.xstream.ej1.javabean;

import java.util.ArrayList;

public class Agencia {
    private ArrayList<Viaje> viajes;

    public Agencia() {
        viajes = new ArrayList<>();
    }

    public void addViaje(Viaje viaje) {
        viajes.add(viaje);
    }

    public ArrayList<Viaje> getViajes() {
        return viajes;
    }
}
