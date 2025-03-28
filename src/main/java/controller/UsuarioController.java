package controller;

import exceptions.AutenticacionException;
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
    public void registrarUsuario(Usuario usuarioNuevo) {

        if (list.contains(usuarioNuevo)) {
            MenuVista.muestraMensaje("El usuario ya existe");
        } else if (list.add(usuarioNuevo)) {
            MenuVista.muestraMensaje("El usuario se ha registrado correctamente");
        } else {
            MenuVista.muestraMensaje("El usuario no ha sido registrado.");
        }

    }

    /**
     * * Método que muestra en pantalla el menú de inicio de sesión, y comprueba si
     * el usuario existe en la lista y si la contraseña introducida es correcta.
     * Si es así se establece ese usuario como el actual.
     */
    public void iniciarSesion() {
        UsuarioActualController usuarioActualController = new UsuarioActualController();
        Usuario usuario = usuarioActualController.getUsuario();
        try {
            if (usuario == null) {
                throw new AutenticacionException("No se han ingresado las credenciales.");
            }
            if (!list.contains(usuario)) {
                throw new AutenticacionException("Usuario o contraseña incorrectos.");
            }
            MenuVista.muestraMensaje("Inicio de sesión exitoso.");
        } catch (AutenticacionException e) {
            MenuVista.muestraMensaje("Error de autenticación: " + e.getMessage());
            usuarioActualController.setUsuario(null);
        }
    }

    public int tipoUsuario() {
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
                MenuVista.muestraMensaje("Opción no válida");
        }
        return opcion;
    }


}