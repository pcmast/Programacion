package model;

public class Premio {
    private String nombre;
    private int puntosRequeridos;

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
    public String toString() {
        return nombre + " (Requiere " + puntosRequeridos + " puntos)";
    }
}
