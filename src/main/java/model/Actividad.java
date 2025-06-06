package model;

import utils.LocalDateAdapter;
import interfaces.CRUDGenerico;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "actividad")
@XmlAccessorType(XmlAccessType.FIELD)
public class Actividad implements CRUDGenerico {

    @XmlElement
    private String nombre;
    @XmlElement
    private String descripcion;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaInicio;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaFin;
    @XmlElement
    private ArrayList<String> voluntario = new ArrayList<>();
    @XmlElement
    private EstadoActividad estado;           // enum: no_iniciada, en_proceso, completada
    @XmlElement
    private String comentario;
    @XmlElement
    private String iniciativa;
    @XmlElement(name = "usuario", type = Usuario.class)
    private ArrayList<Usuario> list = new ArrayList<>();

    /**
     * Constructor vacío
     */
    public Actividad() {
    }

    /**
     * Constructor con parámetros (útil para inicializar fácilmente)
     */
    public Actividad(String nombre, String descripcion, LocalDate fechaInicio,
                     LocalDate fechaFin, ArrayList<String> voluntario, EstadoActividad estado, String iniciativa) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.voluntario = voluntario;
        this.estado = estado;
        this.iniciativa = iniciativa;
    }

    public String getNombre() {
        return nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public ArrayList<String> getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(ArrayList<String> voluntario) {
        this.voluntario = voluntario;
    }

    public String getIniciativa() {
        return iniciativa;
    }

    public void setIniciativa(String iniciativa) {
        this.iniciativa = iniciativa;
    }

    public EstadoActividad getEstado() {
        return estado;
    }

    public ArrayList<Usuario> getList() {
        return list;
    }

    public void setList(ArrayList<Usuario> list) {
        this.list = list;
    }

    public void setEstado(EstadoActividad estado) {
        this.estado = estado;
    }


    /**
     * Método para cambiar el estado e insertar un comentario
     */
    public void actualizarEstado(EstadoActividad nuevoEstado, String nuevoComentario) {
        this.estado = nuevoEstado;
        if (nuevoComentario != null && !nuevoComentario.trim().isEmpty()) {
            this.comentario = nuevoComentario;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Actividad actividad = (Actividad) o;
        return Objects.equals(nombre, actividad.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }


    @Override
    /**
     *  Método que añade un objeto si no lo contiene a una lista.
     *      *
     *      * @param o el objeto que se va a añadir.
     *      * @return true si se pudo añadir, false en caso contrario.
     */
    public boolean annadirList(Object o) {
        boolean annadido = false;
        if (!list.contains(o)) {
            list.add((Usuario) o);
            annadido = true;
        }

        return annadido;

    }


    @Override
    /**
     * Método que elimina un objeto por su nombre.
     *
     * @param nombre El nombre de ese objeto.
     * @return true si se pudo eliminar, false en caso contrario.
     */
    public boolean eliminarList(String cadena) {
        boolean eliminado = false;
        for (Usuario usuario : list) {
            if (usuario.getNombre().equals(cadena)) {
                list.remove(usuario);
                eliminado = true;
                break;
            }
        }
        return eliminado;
    }


    @Override
    /**
     * Método que devuelve una copia de la lista de actividades.
     *
     * @return una copia de la lista de actividades.
     */
    public List obtenerTodos() {
        return getList();
    }

    @Override
    public String toString() {
        System.out.println("═══════════════════════════════");
        System.out.println("         📌 ACTIVIDAD         ");
        System.out.println("═══════════════════════════════");
        System.out.println("📌 Nombre: " + (nombre == null ? "N/A" : nombre));
        System.out.println("📝 Descripción: " + (descripcion == null ? "N/A" : descripcion));
        System.out.println("📅 Fecha de inicio: " + (fechaInicio == null ? "N/A" : fechaInicio));
        System.out.println("📅 Fecha de fin: " + (fechaFin == null ? "N/A" : fechaFin));
        System.out.println("🙋 Voluntarios asignados: " + (voluntario != null ? list.size() : 0));
        System.out.println("📌 Estado: " + (estado == null ? "N/A" : estado));
        System.out.println("📝 Comentario: "+(comentario == null ? "N/A":comentario));

        System.out.println("═══════════════════════════════");
        return "";
    }

    //Aquí hemos implementado una nueva metodología para los toStrings, para poder jugar con ellos, es decir, código más limpio, y una salida más limpia.
    //Sabemos perfectamente lo que quiere decir la interrogación y para que se utiliza. Y en que casos.
}
