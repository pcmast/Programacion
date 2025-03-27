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
        Usuario usuarioNuevo=MenuVista.pideDatosRegistrarUsuario();
        if(list.contains(usuarioNuevo)){
            Utilidades.mostrarMensaje("El usuario ya existe");
        }else if(list.add(usuarioNuevo)){
            Utilidades.mostrarMensaje("El usuario se ha registrado correctamente");
        }else {
            Utilidades.mostrarMensaje("El usuario no ha sido registrado.");
        }

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