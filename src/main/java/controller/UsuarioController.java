package controller;

import model.Usuario;
import utils.Utilidades;
import view.MenuVista;

import java.util.ArrayList;

import static view.MenuVista.menuCreador;
import static view.MenuVista.menuVoluntarios;

public class UsuarioController {
    private ArrayList<Usuario> list = new ArrayList<>();

    /**
     * Método que muestra en pantalla el menú de registro de usuario,crea el usuario con los datos del registro y lo añade a la lista de usuarios registrados.
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

    /**
     * Método que muestra en pantalla el menú de inicio de sesión, y comprueba si el usuario existe en la lista y si la contraseña introducida es correcta. Si es así se establece ese usuario como el actual.
     */
    public void iniciarSesion() {
        UsuarioActualController usuarioActualController = new UsuarioActualController();
        Usuario usuario = usuarioActualController.getUsuario();
        if(usuario!=null && list.contains(usuario)){
            Usuario usuarioInicioSesion= MenuVista.pideDatosInicioSesion();
            usuarioActualController.setUsuario(usuarioInicioSesion);
        }else {
            Utilidades.mostrarMensaje("Usuario o contraseña incorrectos.");
        }

    }

    public void tipoUsuario() {
        MenuVista.muestraMenuCreadorOVoluntario();
        int opcion = Utilidades.leeEntero("Opción:");
        switch (opcion) {
            case 1:
                MenuVista.menuCreador();
                break;
            case 2:
                MenuVista.menuVoluntarios();
                break;
            default:
                Utilidades.mostrarMensaje("Opción no válida");
        }
    }



}