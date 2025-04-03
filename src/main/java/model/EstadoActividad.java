package model;

public enum EstadoActividad {
    NO_INICIADA("La actividad se encuentra no iniciada"),
    EN_PROCESO("La actividad se encuentra en proceso"),
    COMPLETADA("La actividad se encuentra completada"),;

    private final String descripcion;

    EstadoActividad(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
        return descripcion;
    }
}
