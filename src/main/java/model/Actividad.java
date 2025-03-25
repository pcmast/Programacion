package model;

import java.time.LocalDate;
import java.util.List;

public class  Actividad {

    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private String fechaFin;
    private boolean voluntario;
    private EstadoActividad estado;           // enum: no_iniciada, en_proceso, completada
    private List<Comentario> comentarios;

    public Actividad() {
    }

    /**
     * Constructor con parámetros (útil para inicializar fácilmente)
     */
    public Actividad(String nombre, String descripcion, LocalDate fechaInicio,
                     String fechaFin, boolean voluntario, EstadoActividad estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.voluntario = voluntario;
        this.estado = estado;
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

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean esVoluntario() {
        return voluntario;
    }

    public void setVoluntario(boolean voluntario) {
        this.voluntario = voluntario;
    }

    public EstadoActividad getEstado() {
        return estado;
    }



    public void setEstado(EstadoActividad estado) {
        this.estado = estado;
    }

    public void agregarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    /**
     * Método para cambiar el estado e insertar un comentario
     */
    public void actualizarEstado(EstadoActividad nuevoEstado, String comentario) {
        this.estado = nuevoEstado;
        if (comentario != null && !comentario.trim().isEmpty()) { //Esta línea de comandos hace que el comentario si es vacío, lo detecte como vacío
            //agregarComentario(new Comentario(comentario, responsable, LocalDate.now()));
        }
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin='" + fechaFin + '\'' +
                ", voluntario=" + voluntario +
                ", estado=" + estado +
                '}';
    }
}
