package model;

import interfaces.CRUDGenerico;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
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

    public Iniciativa() {
    }

    /**
     * Constructor con todos los parámetros
     */
    public Iniciativa(String nombre, String descripcion, String creadorIniciativa) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creadorIniciativa = creadorIniciativa;
    }

    /**
     * Obtiene el nombre del premio.
     *
     * @return El nombre actual del premio almacenado en la variable.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece un nuevo nombre para el premio.
     *
     * @param nombre El nombre que se asignará al premio.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del premio.
     *
     * @return La descripción actual del premio almacenada en la variable.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece una nueva descripción para el premio.
     *
     * @param descripcion La descripción que se asignará al premio.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el nombre del creador de la iniciativa del premio.
     *
     * @return El nombre del creador asociado al premio.
     */
    public String getCreadorIniciativa() {
        return creadorIniciativa;
    }

    /**
     * Establece el nombre del creador de la iniciativa del premio.
     *
     * @param creadorIniciativa El nombre del creador que se asignará al premio.
     */
    public void setCreadorIniciativa(String creadorIniciativa) {
        this.creadorIniciativa = creadorIniciativa;
    }

    /**
     * Obtiene la lista de actividades asociadas al premio.
     *
     * @return La lista actual de actividades almacenada en la variable.
     */
    public ArrayList<Actividad> getList() {
        return list;
    }

    /**
     * Establece una nueva lista de actividades asociadas al premio.
     *
     * @param list La lista de actividades que se asignará al premio.
     */
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
     *
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
     *
     * @param nombre El nombre de ese objeto.
     * @return true si se pudo eliminar, false en caso contrario.
     */
    public boolean eliminarList(String nombre) {
        return list.removeIf(actividad -> actividad.getNombre().equals(nombre));
    }

    /**
     * Método que modifica un objeto en la lista.
     * Elimina el objeto anterior y añade el nuevo.
     *
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
     *
     * @return una copia de la lista de actividades.
     */
    public ArrayList<Actividad> obtenerTodos() {
        return new ArrayList<>(list);
    }

    @Override
    public String toString() {
        return "Iniciativa \n" +
                "nombre= " + nombre + '\n' +
                "descripcion= " + descripcion + '\n' +
                "creador Iniciativa= " + creadorIniciativa;
    }
}
