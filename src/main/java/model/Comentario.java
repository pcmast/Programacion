package model;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa un comentario en una actividad
 */
public class Comentario implements Serializable {
    private String contenido;
    private Usuario autor;
    private LocalDate fecha;

    public Comentario(String contenido, Usuario autor, LocalDate fecha) {
        this.contenido = contenido;
        this.autor = autor;
        this.fecha = fecha;
    }

    // Getters
    public String getContenido() {
        return contenido;
    }

    public Usuario getAutor() {
        return autor;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}