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

    /**
     * Obtiene la lista de usuarios cargados desde el archivo XML.
     *
     * @return Lista de usuarios registrados.
     */
    public ArrayList<Usuario> getList() {
        cargarUsuariosDesdeXML();
        return list;
    }

    /**
     * Muestra el menú de registro de usuario, crea un nuevo usuario y lo añade a la lista de usuarios registrados.
     *
     * @param usuarioNuevo Usuario que se va a registrar.
     */
    public void registrarUsuario(Usuario usuarioNuevo) {
        cargarUsuariosDesdeXML();

        // Asignar el tipo de usuario
        if (usuarioNuevo instanceof Creador) {
            usuarioNuevo.setTipo("creador");
        } else if (usuarioNuevo instanceof Voluntario) {
            usuarioNuevo.setTipo("voluntario");
        }

        // Verificar si el usuario ya está registrado
        if (list.contains(usuarioNuevo)) {
            MenuVista.muestraMensaje("¡El usuario ya existe!");
        } else {
            // Añadir usuario a la lista
            if (list.add(usuarioNuevo)) {
                MenuVista.muestraMensaje(">> ✅ ¡El usuario se ha registrado correctamente!");
                guardarUsuariosEnXML(); // Guardar los usuarios en el archivo XML
            } else {
                MenuVista.muestraMensaje(">> ❌ ¡El usuario no ha sido registrado!");
            }
        }
    }

    /**
     * Carga los usuarios desde el archivo XML y los almacena en la lista.
     */
    private void cargarUsuariosDesdeXML() {
        try {
            UsuariosContenedor wrapper = XMLManager.readXML(new UsuariosContenedor(), "usuarios.xml");
            if (wrapper != null) {
                list = new ArrayList<>(wrapper.getUsuarios());
            }
        } catch (RuntimeException e) {
            // Aquí se podría meter un log para capturar el error
        }
    }

    /**
     * Guarda la lista de usuarios en el archivo XML.
     */
    private void guardarUsuariosEnXML() {
        try {
            UsuariosContenedor wrapper = new UsuariosContenedor();
            wrapper.setUsuarios(list);
            boolean exito = XMLManager.writeXML(wrapper, "usuarios.xml");
            if (!exito) {
                MenuVista.muestraMensaje(">> ❌ Ocurrió un error al guardar los datos en XML.");
            }
        } catch (RuntimeException e) {
            MenuVista.muestraMensaje("❌ ¡Error al guardar los usuarios en XML: " + e.getMessage() + "!");
        }
    }

    /**
     * Solicita los datos de acceso al usuario e inicia sesión si las credenciales son correctas.
     *
     * @param correo Correo electrónico del usuario registrado.
     * @param contrasenna Contraseña del usuario registrada (encriptada).
     * @return El usuario que ha iniciado sesión correctamente.
     * @throws AutenticacionException Si ocurre un error de autenticación.
     */
    public Usuario iniciarSesion(String correo, String contrasenna) throws AutenticacionException {
        cargarUsuariosDesdeXML();
        UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();

        for (Usuario usuario : list) {
            if (usuario.getCorreo().equals(correo)) {
                if (usuario.verificarContrasenna(contrasenna)) {
                    Usuario usuarioFinal;

                    // Crear un nuevo usuario basado en el tipo de usuario (Creador o Voluntario)
                    if ("creador".equals(usuario.getTipo())) {
                        usuarioFinal = new Creador(usuario.getNombre(), usuario.getUsuario(),
                                usuario.getContrasenna(), usuario.getCorreo(), "");
                    } else if ("voluntario".equals(usuario.getTipo())) {
                        usuarioFinal = new Voluntario(usuario.getNombre(), usuario.getUsuario(),
                                usuario.getContrasenna(), usuario.getCorreo());
                    } else {
                        throw new AutenticacionException("Error: Tipo de usuario no reconocido");
                    }

                    // Establecer el usuario actual
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

    /**
     * Muestra el menú de selección para el tipo de usuario (Creador o Voluntario).
     *
     * @return La opción seleccionada por el usuario.
     */
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
