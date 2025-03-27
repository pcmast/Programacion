package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Premio {
    @XmlElement
    private String nombre;

    @XmlElement
    private int puntosRequeridos;

    /**
     * Constructor vacío
     */
    public Premio() {}

    /**
     * Constructor con parámetros.
     *
     * @param nombre Nombre del premio.
     * @param puntosRequeridos Puntos necesarios para obtener el premio.
     */
    public Premio(String nombre, int puntosRequeridos) {
        this.nombre = nombre;
        this.puntosRequeridos = puntosRequeridos;
    }

    /**
     * Obtiene el nombre del premio.
     *
     * @return Nombre del premio.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la cantidad de puntos requeridos para el premio.
     *
     * @return Puntos necesarios para obtener el premio.
     */
    public int getPuntosRequeridos() {
        return puntosRequeridos;
    }

    /**
     * Compara si dos objetos Premio son iguales.
     *
     * @param o Objeto a comparar.
     * @return true si los premios son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Premio premio = (Premio) o;
        return puntosRequeridos == premio.puntosRequeridos && Objects.equals(nombre, premio.nombre);
    }

    /**
     * Genera un código hash para el objeto Premio.
     *
     * @return Código hash basado en el nombre y los puntos requeridos.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre, puntosRequeridos);
    }

    /**
     * Representación en cadena del objeto Premio.
     *
     * @return Cadena con el formato "nombre (Requiere X puntos)".
     */
    @Override
    public String toString() {
        return nombre + " (Requiere " + puntosRequeridos + " puntos)";
    }
}
