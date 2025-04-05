package model;

public enum EstadoActividad {
    // Enumeración de los posibles estados de una actividad
    NO_INICIADA("La actividad se encuentra no iniciada"),
    EN_PROCESO("La actividad se encuentra en proceso"),
    COMPLETADA("La actividad se encuentra completada");

    private final String descripcion;

    /**
     * Constructor de la enumeración que asigna la descripción a cada estado.
     *
     * @param descripcion La descripción del estado de la actividad.
     */
    EstadoActividad(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la descripción del estado de la actividad.
     *
     * @return La descripción asociada al estado.
     */
    public String getDescripcion() {
        return descripcion;
    }
}
