package controller;

import model.Usuario;
import utils.Utilidades;
import view.MenuVista;

import java.util.ArrayList;

public class UsuarioController {
    private ArrayList<Usuario> list = new ArrayList<>();

    /**
     * Método que muestra en pantalla el menú de registro de usuario y crea el usuario con los datos del registro.
     */
    public void registrarUsuario() {
        MenuVista.pideDatosRegistrarUsuario();

    }

    public void iniciarSesion(Usuario usuario) {
        UsuarioActualController usuarioActualController = new UsuarioActualController();
        Usuario usuario1 = usuarioActualController.getUsuario();
        MenuVista.pideDatosInicioSesion();
    }

    public void tipoMenu() {
        MenuVista.muestraMenuCreadorOVoluntario();
    }

    public void menuCreador() {
        MenuVista.menuCreador();
    }

    public void menuVoluntarios() {
        MenuVista.menuVoluntarios();
    }


}