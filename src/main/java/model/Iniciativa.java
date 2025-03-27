package model;

import interfaces.CRUDGenerico;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "Iniciativas")
@XmlAccessorType(XmlAccessType.FIELD)
public class Iniciativa implements CRUDGenerico {
    @XmlElement
    private String nombre;
    @XmlElement
    private String descripcion;
    @XmlElement
    private String creadorIniciativa;
    @XmlElement(name = "iniciativa", type = Iniciativa.class)
    private ArrayList<Actividad> list = new ArrayList<>();

    public Iniciativa() {}

    // Constructor con todos los parámetros
    public Iniciativa(String nombre, String descripcion, String creadorIniciativa) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creadorIniciativa = creadorIniciativa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCreadorIniciativa() {
        return creadorIniciativa;
    }

    public void setCreadorIniciativa(String creadorIniciativa) {
        this.creadorIniciativa = creadorIniciativa;
    }

    public ArrayList<Actividad> getList() {
        return list;
    }

    public void setList(ArrayList<Actividad> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Iniciativa that = (Iniciativa) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(creadorIniciativa, that.creadorIniciativa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, creadorIniciativa);
    }

    /**
     * Método que añade un objeto si no lo contiene a una lista.
     * @param o el objeto que se va a añadir.
     * @return true si se pudo añadir, false en caso contrario.
     */
    public boolean annadirList(Object o) {
        if (!list.contains(o)) {
            list.add((Actividad) o);
            return true;
        }
        return false;
    }

    /**
     * Método que elimina un objeto por su nombre.
     * @param nombre El nombre de ese objeto.
     * @return true si se pudo eliminar, false en caso contrario.
     */
    public boolean eliminarList(String nombre) {
        return list.removeIf(actividad -> actividad.getNombre().equals(nombre));
    }

    /**
     * Método que modifica un objeto en la lista.
     * Elimina el objeto anterior y añade el nuevo.
     * @param o objeto que se modificará.
     * @return true si se pudo modificar, false en caso contrario.
     */
    public boolean modificar(Object o) {
        if (list.contains(o)) {
            list.remove(o);
            list.add((Actividad) o);
            return true;
        }
        return false;
    }

    /**
     * Método que devuelve una copia de la lista de actividades.
     * @return una copia de la lista de actividades.
     */
    public ArrayList<Actividad> obtenerTodos() {
        return new ArrayList<>(list);
    }

    @Override
    public String toString() {
        return "Iniciativa" +
                "nombre= " + nombre + '\n' +
                "descripcion= " + descripcion + '\n' +
                "creadorIniciativa= " + creadorIniciativa;
    }
}
