package model;

import interfaces.CRUDGenerico;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Objects;

@XmlRootElement(name = "iniciativa")
@XmlAccessorType(XmlAccessType.FIELD)
public class Iniciativa implements CRUDGenerico {
    @XmlElement
    private String nombre;
    @XmlElement
    private String descripcion;
    @XmlElement
    private String creadorIniciativa;

    @XmlElementWrapper(name = "actividades")
    @XmlElement(name = "actividad")
    private ArrayList<Actividad> actividades = new ArrayList<>();

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

    @XmlElementWrapper(name = "actividades")
    @XmlElement(name = "actividad")
    private ArrayList<Actividad> lista = new ArrayList<>();

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
        return actividades;
    }

    /**
     * Establece una nueva lista de actividades asociadas al premio.
     *
     * @param list La lista de actividades que se asignará al premio.
     */
    public void setList(ArrayList<Actividad> list) {
        this.actividades = actividades;
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
        if (!actividades.contains(o)) {
            actividades.add((Actividad) o);
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
        return actividades.removeIf(actividad -> actividad.getNombre().equals(nombre));
    }

    /**
     * Método que modifica un objeto en la lista.
     * Elimina el objeto anterior y añade el nuevo.
     *
     * @param o objeto que se modificará.
     * @return true si se pudo modificar, false en caso contrario.
     */
    public boolean modificar(Object o) {
        if (actividades.contains(o)) {
            actividades.remove(o);
            actividades.add((Actividad) o);
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
        return new ArrayList<>(actividades);
    }

    @Override
    public String toString() {
        String result = "";
        result += "══════════════════════════════════════════\n";
        result += "           🌿 DETALLES DE INICIATIVA     \n";
        result += "══════════════════════════════════════════\n";
        result += "📌 Nombre: " + (nombre != null ? nombre : "N/A") + "\n";
        result += "📝 Descripción: " + (descripcion != null ? descripcion : "N/A") + "\n";
        result += "👤 Creador: " + (creadorIniciativa != null ? creadorIniciativa : "N/A") + "\n";
        result += "══════════════════════════════════════════\n";
        result += "📅 ACTIVIDADES ASOCIADAS (" + actividades.size() + ")\n";
        result += "══════════════════════════════════════════\n";

        if (actividades.isEmpty()) {
            result += "No hay actividades registradas en esta iniciativa.\n";
        } else {
            for (Actividad actividad : actividades) {
                result += "🔹 " + actividad.getNombre() + "\n";
                result += "   📆 Fecha inicio: " + (actividad.getFechaInicio() != null ? actividad.getFechaInicio() : "Por definir") + "\n";
                result += "   🏁 Estado: " + (actividad.getEstado() != null ? actividad.getEstado() : "N/A") + "\n";
                result += "   ──────────────────────────────────────\n";
            }
        }
        result += "══════════════════════════════════════════";
        return result;
    }
}

