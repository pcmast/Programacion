package controller;

import dataAcces.XMLManager;
import model.Usuario;
import model.UsuariosContenedor;
import utils.Utilidades;
import view.MenuVista;

import java.util.ArrayList;


public class UsuarioController {
    private ArrayList<Usuario> list = new ArrayList<>();

    public ArrayList<Usuario> getList() {
        return list;
    }

    /**
     * Método que muestra en pantalla el menú de registro de usuario,crea el usuario con los datos del registro y lo añade a la lista de usuarios registrados.
     *
     * @param usuarioNuevo devuelve el usuario nuevo que se ha registrado
     */
    public void registrarUsuario(Usuario usuarioNuevo) {
        cargarUsuariosDesdeXML();

        if (list.contains(usuarioNuevo)) {
            MenuVista.muestraMensaje("¡El usuario ya existe!");
        } else {
            if (list.add(usuarioNuevo)) {
                MenuVista.muestraMensaje(">> ✅ ¡El usuario se ha registrado correctamente!");
                guardarUsuariosEnXML(); // Guardar en XML
            } else {
                MenuVista.muestraMensaje(">> ❌ ¡El usuario no ha sido registrado!");
            }
        }
    }

    private void cargarUsuariosDesdeXML() {
        try {
            UsuariosContenedor wrapper = XMLManager.readXML(new UsuariosContenedor(), "usuarios.xml");
            if (wrapper!= null) {
                list = new ArrayList<>(wrapper.getUsuarios());
            }
        } catch (RuntimeException e) {
            MenuVista.muestraMensaje("❌ Error al obtener los usuarios desde XML.");
        }
    }

    private void guardarUsuariosEnXML() {
        try {
            UsuariosContenedor wrapper = new UsuariosContenedor();
            wrapper.setUsuarios(list);
            boolean exito = XMLManager.writeXML(wrapper, "usuarios.xml");
            if (exito) {
                MenuVista.muestraMensaje(">> ✅ Datos guardados correctamente en XML");
            } else {
                MenuVista.muestraMensaje(">> ❌ Ocurrió un error al guardar los datos en XML.");
            }
        } catch (RuntimeException e) {
            MenuVista.muestraMensaje("❌ ¡Error al guardar los usuarios en XML: " + e.getMessage() + "!");
        }
    }

    /**
     * Método que muestra en pantalla el menú de inicio de sesión, y comprueba si el usuario existe en la lista y si la contraseña introducida es correcta. Si es así se establece ese usuario como el actual.
     */
    public void iniciarSesion(String correo, String contrasenna) {
        cargarUsuariosDesdeXML();
        UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();
        boolean usuarioValido = false;
        for (Usuario usuario : list) {
            if (usuario.getCorreo().equals(correo) && !usuarioValido) {
                if (usuario.verificarContrasenna(contrasenna)) {
                    usuarioActualController.setUsuario(usuario);
                    MenuVista.muestraMensaje("Inicio de sesión exitoso. ¡Bienvenido, " + usuario.getNombre() + "!");
                    usuarioValido = true;
                } else {
                    MenuVista.muestraMensaje("La contraseña es incorrecta.");
                    usuarioValido = true;
                }
            }
        }
        if (!usuarioValido) {
            MenuVista.muestraMensaje("El correo proporcionado no está registrado.");
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