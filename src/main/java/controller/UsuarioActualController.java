package controller;

import model.Usuario;

public class UsuarioActualController {

    private Usuario usuario = null;

    public UsuarioActualController() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
