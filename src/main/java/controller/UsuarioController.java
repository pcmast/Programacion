package controller;

import model.Usuario;
import utils.Utilidades;
import view.MenuVista;

public class UsuarioController {

    /**
     * Método que muestra en pantalla el menú de registro de usuario y crea el usuario con los datos del registro.
     *
     * @return true si el usuario se ha registrado correctamente, false en caso contrario.
     */
    public boolean registrarUsuario() {
        boolean Registrado = false;

        Usuario usuarioRegistrado = new Usuario(MenuVista.pideDatosRegistrarUsuario());
        Registrado = true;
        return Registrado;
    }

    /**
     * por corregir
     *Método que muestra en pantalla el menú de datos de inicio de sesión del usuario y pide el tipo de inicio de sesión.
     * @param usuario
     * @return un entero que indica el tipo de inicio de sesión (1 para creador, 2 para voluntario).
     */

    public int iniciarSesion(Usuario usuario) {
        usuario.iniciarSesion(MenuVista.pideDatosInicioSesion());
        int opcionRegistro = MenuVista.muestraMenuCreadorOVoluntario();
        return opcionRegistro;
    }

    public boolean cerrarSesion(Usuario usuario) {
        Utilidades.mostrarMensaje("Cerrando sesión...");
        return usuario.cerrarSesion();

    }


}
