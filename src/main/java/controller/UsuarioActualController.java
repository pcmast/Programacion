package controller;

import model.Usuario;

public class UsuarioActualController {
    private static UsuarioActualController instance;

    public static UsuarioActualController getInstance(){
        if (instance == null){
            instance = new UsuarioActualController();
        }
        return instance;
    }

    private Usuario usuario = null;
    private static UsuarioActualController instancia;

    private UsuarioActualController() {

    }
    public static UsuarioActualController getInstance() {
        if (instancia == null) {
            instancia = new UsuarioActualController();
        }
        return instancia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
