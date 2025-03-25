package model;

import java.time.LocalDate;

public class Actividad {

    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private String fechaFin;
    private boolean voluntario;
    private EstadoActividad estado;           // enum: no_iniciada, en_proceso, completada

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

    /**
     * Método para cambiar el estado (Enum)
     */
    public void cambiarEstado(EstadoActividad nuevoEstado) {
        this.estado = nuevoEstado;
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
