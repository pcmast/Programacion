package model;

public enum EstadoActividad {
    NO_INICIADA(),
    EN_PROCESO(""),
    COMPLETADA(comentario);

    private Comentario comentario;
    EstadoActividad(Comentario comentario) {
    }
}
