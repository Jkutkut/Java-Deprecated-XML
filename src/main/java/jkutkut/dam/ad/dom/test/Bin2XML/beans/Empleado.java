package jkutkut.dam.ad.dom.test.Bin2XML.beans;

import java.io.Serializable;

public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String[] ATTRIBUTES = {"id", "nombre", "departamento", "salario"};

    private final int id;
    private final String nombre;
    private final int departamento;
    private final double salario;

    public Empleado(int id, String nombre, int departamento, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
    }

//    public int getId() {
//        return id;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public int getDepartamento() {
//        return departamento;
//    }
//
//    public double getSalario() {
//        return salario;
//    }

    public String[] toStringArray() {
        return new String[]{
            String.valueOf(id),
            nombre,
            String.valueOf(departamento),
            String.valueOf(salario)
        };
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", departamento=" + departamento +
                ", salario=" + salario +
                '}';
    }
}
