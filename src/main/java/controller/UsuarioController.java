package controller;

import dataAcces.XMLManager;
import model.Creador;
import model.Usuario;
import model.UsuariosContenedor;
import model.Voluntario;
import utils.Utilidades;
import view.MenuVista;

import java.util.ArrayList;

/**
 * Clase que controla la lógica de negocio relacionada con los usuarios.
 */
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

        // Asignar el tipo de usuario
        if (usuarioNuevo instanceof Creador) {
            usuarioNuevo.setTipo("creador");
        } else if (usuarioNuevo instanceof Voluntario) {
            usuarioNuevo.setTipo("voluntario");
        }

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
    public Usuario iniciarSesion(String correo, String contrasenna) {
        cargarUsuariosDesdeXML();
        UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();

        for (Usuario usuario : list) {
            if (usuario.getCorreo().equals(correo)) {
                if (usuario.verificarContrasenna(contrasenna)) {
                    Usuario usuarioFinal;

                    // Convertir el usuario base al tipo correcto
                    if ("creador".equals(usuario.getTipo())) {
                        usuarioFinal = new Creador(usuario.getNombre(), usuario.getUsuario(),
                                usuario.getContrasenna(), usuario.getCorreo(), "");
                    } else if ("voluntario".equals(usuario.getTipo())) {
                        usuarioFinal = new Voluntario(usuario.getNombre(), usuario.getUsuario(),
                                usuario.getContrasenna(), usuario.getCorreo());
                    } else {
                        MenuVista.muestraMensaje("Error: Tipo de usuario no reconocido");
                        return null;
                    }

                    usuarioActualController.setUsuario(usuarioFinal);

                    // Mensaje personalizado según tipo de usuario
                    MenuVista.muestraMensaje("Inicio de sesión exitoso. ¡Bienvenido " +
                            usuario.getTipo() + ", " + usuario.getNombre() + "!");

                    return usuarioFinal;
                } else {
                    MenuVista.muestraMensaje("La contraseña es incorrecta.");
                    return null;
                }
            }
        }

        MenuVista.muestraMensaje("El correo proporcionado no está registrado.");
        return null;
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