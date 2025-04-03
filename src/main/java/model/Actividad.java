package model;

import dataAcces.LocalDateAdapter;
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
public class  Actividad implements CRUDGenerico {

    @XmlElement
    private String nombre;
    @XmlElement
    private String descripcion;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaInicio;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaFin;
    @XmlElement
    private boolean voluntario;
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
                     LocalDate fechaFin, boolean voluntario, EstadoActividad estado,String iniciativa) {
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

    public boolean esVoluntario() {
        return voluntario;
    }

    public void setVoluntario(boolean voluntario) {
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
    public boolean annadirList(Object o) {
        boolean annadido = false;
        if (!list.contains(o)){
            list.add((Usuario) o);
            annadido = true;
        }

        return annadido;

    }

    @Override
    public boolean eliminarList(String cadena) {
        boolean eliminado = false;
        for (Usuario usuario:list){
            if (usuario.getNombre().equals(nombre)){
                list.remove(usuario);
                eliminado = true;
            }
        }
        return eliminado;
    }

    @Override
    public boolean modificar(Object o) {
        boolean actualizado = false;
        for (Usuario usuario:list){
            if (usuario.equals(o)){
                list.remove(usuario);
                list.add((Usuario) o);
                actualizado = true;
            }

        }
        return actualizado;
    }

    @Override
    public List obtenerTodos() {
        return getList();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════\n");
        sb.append("         📌 ACTIVIDAD         \n");
        sb.append("═══════════════════════════════\n");
        sb.append("📌 Nombre: ").append(nombre != null ? nombre : "N/A").append("\n");
        sb.append("📝 Descripción: ").append(descripcion != null ? descripcion : "N/A").append("\n");
        sb.append("📅 Fecha de inicio: ").append(fechaInicio != null ? fechaInicio : "N/A").append("\n");
        sb.append("📅 Fecha de fin: ").append(fechaFin != null ? fechaFin : "N/A").append("\n");
        sb.append("🙋 Voluntario asignado: ").append(voluntario ? "Sí" : "No").append("\n");
        sb.append("📌 Estado: ").append(estado != null ? estado : "N/A").append("\n");
        sb.append("═══════════════════════════════");
        return sb.toString();
    }

}
