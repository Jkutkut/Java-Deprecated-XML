package jkutkut.dam.ad.xml.test.Bin2XML.beans;

import java.io.Serializable;

public class Empleado implements Serializable {
    public static final String[] ATTRIBUTES = {"id", "nombre", "departamento", "salario"};

    private int id;
    private String nombre;
    private int departamento;
    private double salario;

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
