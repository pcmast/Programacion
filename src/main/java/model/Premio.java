package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "Iniciativas")
@XmlAccessorType(XmlAccessType.FIELD)
public class Premio {
    @XmlElement
    private String nombre;
    @XmlElement
    private int puntosRequeridos;

    //Constructor vacío
    public Premio() {}

    public Premio(String nombre, int puntosRequeridos) {
        this.nombre = nombre;
        this.puntosRequeridos = puntosRequeridos;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntosRequeridos() {
        return puntosRequeridos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Premio premio = (Premio) o;
        return puntosRequeridos == premio.puntosRequeridos && Objects.equals(nombre, premio.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, puntosRequeridos);
    }

    @Override
    public String toString() {
        return nombre + " (Requiere " + puntosRequeridos + " puntos)";
    }
}
