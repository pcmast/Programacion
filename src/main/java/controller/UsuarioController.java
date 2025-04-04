package controller;

import dataAcces.XMLManager;
import exceptions.AutenticacionException;
import model.Creador;
import model.Usuario;
import utils.UsuariosContenedor;
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
        cargarUsuariosDesdeXML();
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
     * Metodo que pide a los usuarios los datos de acceso para iniciar sesión.
     * @param correo correo previamente preguntado al registrarse
     * @param contrasenna contraseña previamente preguntada al registrarse (Encriptada)
     * @return devuelve el usuario que ha iniciado sesión. O null si no se ha podido iniciar sesión.
     * @throws AutenticacionException si ocurre un error de autenticación
     */
    public Usuario iniciarSesion(String correo, String contrasenna) throws AutenticacionException {
        cargarUsuariosDesdeXML();
        UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();

        for (Usuario usuario : list) {
            if (usuario.getCorreo().equals(correo)) {
                if (usuario.verificarContrasenna(contrasenna)) {
                    Usuario usuarioFinal;

                    if ("creador".equals(usuario.getTipo())) {
                        usuarioFinal = new Creador(usuario.getNombre(), usuario.getUsuario(),
                                usuario.getContrasenna(), usuario.getCorreo(), "");
                    } else if ("voluntario".equals(usuario.getTipo())) {
                        usuarioFinal = new Voluntario(usuario.getNombre(), usuario.getUsuario(),
                                usuario.getContrasenna(), usuario.getCorreo());
                    } else {
                        throw new AutenticacionException("Error: Tipo de usuario no reconocido");
                    }

                    usuarioActualController.setUsuario(usuarioFinal);

                    MenuVista.muestraMensaje("Inicio de sesión exitoso. ¡Bienvenido " +
                            usuario.getTipo() + ", " + usuario.getNombre() + "!");

                    return usuarioFinal;
                } else {
                    throw new AutenticacionException("La contraseña es incorrecta.");
                }
            }
        }

        throw new AutenticacionException("El correo proporcionado no está registrado.");
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
