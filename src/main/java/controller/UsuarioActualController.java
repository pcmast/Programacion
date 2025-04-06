package controller;

import model.Creador;
import model.Usuario;
import model.Voluntario;

public class UsuarioActualController {
    private static UsuarioActualController instance;

    /**
     * Obtiene la instancia única del controlador de usuario actual (Singleton).
     * Si la instancia aún no ha sido creada, la crea.
     *
     * @return La instancia única de UsuarioActualController.
     */
    public static UsuarioActualController getInstance(){
        if (instance == null){
            instance = new UsuarioActualController();
        }
        return instance;
    }

    private Usuario usuario = null;

    // La instancia está declarada nuevamente, pero no se está utilizando (puede eliminarse).
    private static UsuarioActualController instancia;

    /**
     * Obtiene el usuario actual que ha iniciado sesión.
     *
     * @return El usuario actual.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario actual que ha iniciado sesión.
     *
     * @param usuario El usuario que se quiere establecer como el usuario actual.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
