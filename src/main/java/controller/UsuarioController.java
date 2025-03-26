package controller;

import model.Usuario;
import utils.Utilidades;
import view.MenuVista;

public class UsuarioController {

    /**
     * Método que muestra en pantalla el menú de registro de usuario y crea el usuario con los datos del registro.
     */
    public void registrarUsuario() {
        MenuVista.pideDatosRegistrarUsuario();
    }

    public void iniciarSesion(Usuario usuario) {
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
