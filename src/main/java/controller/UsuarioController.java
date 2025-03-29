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
    public void registrarUsuario(Usuario usuarioNuevo) {

        if(list.contains(usuarioNuevo)){
            MenuVista.muestraMensaje("El usuario ya existe");
        }else if(list.add(usuarioNuevo)){
            MenuVista.muestraMensaje("El usuario se ha registrado correctamente");
        }else {
            MenuVista.muestraMensaje("El usuario no ha sido registrado.");
        }

    }
    /**
     * Método que muestra en pantalla el menú de inicio de sesión, y comprueba si el usuario existe en la lista y si la contraseña introducida es correcta. Si es así se establece ese usuario como el actual.
     */
    public void iniciarSesion(String correo, String contrasenna) {
        UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();
        Usuario usuario = usuarioActualController.getUsuario();
        if(usuario!=null && list.contains(usuario)){
            Usuario usuarioInicioSesion= null;
            usuarioActualController.setUsuario(usuarioInicioSesion);
        }else {
            MenuVista.muestraMensaje("Usuario o contraseña incorrectos.");
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