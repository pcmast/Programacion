package controller;

import exceptions.AutenticacionException;
import model.Creador;
import model.Usuario;
import model.Voluntario;
import utils.Utilidades;
import view.MenuVista;

import java.io.File;
import java.io.IOException;

public class SistemaController {
    private UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();
    private UsuarioController usuarioController = new UsuarioController();
    private ActividadController actividadController = new ActividadController();
    private IniciativaController iniciativaController = IniciativaController.getInstancia();

    /**
     * Método principal que gestiona el flujo del sistema.
     * Se encarga de mostrar el menú, gestionar el inicio de sesión,
     * el registro de nuevos usuarios, y las interacciones posteriores
     * basadas en el tipo de usuario (Creador o Voluntario).
     */
    public void sistemaControllerEmpezar() {
        Creador creador = null;
        Voluntario voluntario = null;
        int opcion, numero, terminar = 0, salir = 0;
        boolean continuar = true;

        do {
            do {
                MenuVista.mostrarMenu();
                opcion = Utilidades.leeEntero(" >> Introduce la opción que quieras: ");

                switch (opcion) {
                    case 1:
                        String correo = Utilidades.pideCorreo(" ✉ Introduce el correo de la cuenta: ");
                        String contrasenna = Utilidades.pideString(" \uD83D\uDD11 Introduce la contraseña: ");
                        Usuario usuario = null;

                        try {
                            usuario = usuarioController.iniciarSesion(correo, contrasenna);
                        } catch (AutenticacionException e) {
                            MenuVista.muestraMensaje(e.getMessage());
                        }

                        if (usuario != null) {
                            if (usuario instanceof Creador) {
                                creador = (Creador) usuario;
                                terminar = controlarCreador(creador);
                            } else if (usuario instanceof Voluntario) {
                                voluntario = (Voluntario) usuario;
                                terminar = controlarVoluntario();
                            } else {
                                MenuVista.muestraMensaje("Error: Tipo de usuario no soportado");
                            }
                            opcion = 3;
                        }
                        break;
                    case 2: // Registro de nuevo usuario
                        MenuVista.muestraMenuCreadorOVoluntario();
                        numero = Utilidades.leeEntero(" >> Introduce la opción: ");
                        Usuario nuevoUsuario = MenuVista.pideDatosRegistrarUsuario(numero);
                        usuarioActualController.setUsuario(nuevoUsuario);
                        usuarioController.registrarUsuario(nuevoUsuario);
                        opcion = 3;
                        break;

                    case 3:
                        MenuVista.muestraMensaje("Saliendo del programa...");
                        continuar = false;
                        break;
                }
            } while (opcion != 3 && continuar);

            Usuario usuarioActual = usuarioActualController.getUsuario();
            if (usuarioActual instanceof Creador && terminar != 9) {
                creador = (Creador) usuarioActual;
                terminar = controlarCreador(creador);
                creador = null;
            } else if (usuarioActual instanceof Voluntario && terminar != 4) {
                voluntario = (Voluntario) usuarioActual;
                terminar = controlarVoluntario();
                voluntario = null;
            }

        } while (salir != 1 && continuar);
    }

    /**
     * Controla el flujo de acciones disponibles para un usuario de tipo "Creador".
     * El Creador tiene varias opciones para gestionar iniciativas y actividades.
     *
     * @param creador El usuario de tipo Creador que ha iniciado sesión.
     * @return La opción seleccionada por el Creador para salir del menú.
     */
    public int controlarCreador(Creador creador) {
        int opcion2 = 0;
        do {
            MenuVista.menuCreador();
            opcion2 = Utilidades.leeEntero("Introduce la opcion: ");
            switch (opcion2) {
                case 1:
                    // Crea una nueva iniciativa
                    iniciativaController.creaIniciativa();
                    break;
                case 2:
                    // Elimina una iniciativa existente
                    iniciativaController.eliminaIniciativa();
                    break;
                case 3:
                    // Crea una nueva actividad
                    actividadController.creaActividad();
                    break;
                case 4:
                    // Elimina una actividad existente
                    actividadController.eliminaActividad();
                    break;
                case 5:
                    // Añade un usuario a una actividad
                    actividadController.annadirUsuario();
                    break;
                case 6:
                    // Elimina un usuario de una actividad
                    actividadController.eliminarUsuario();
                    break;
                case 7:
                    // Muestra las iniciativas por nombre
                    iniciativaController.muestraIniciativasNombre();
                    break;
                case 8:
                    // Muestra todas las iniciativas
                    iniciativaController.muestraIniciativas();
                    break;
                case 9:
                    break;
            }

        } while (opcion2 != 9);
        return opcion2;
    }

    /**
     * Controla el flujo de acciones disponibles para un usuario de tipo "Voluntario".
     * El Voluntario tiene opciones para interactuar con las actividades y premios.
     *
     * @return La opción seleccionada por el Voluntario para salir del menú.
     */
    public int controlarVoluntario() {
        int opcion3 = 0;
        do {
            MenuVista.menuVoluntarios();
            opcion3 = Utilidades.leeEntero("Introduce la opcion: ");
            switch (opcion3) {
                case 1:
                    // Ver actividades disponibles
                    actividadController.verActividades();
                    break;
                case 2:
                    // Actualiza el estado de una actividad
                    actividadController.actualizarEstado();
                    break;
                case 3:
                    // Muestra las actividades del usuario
                    iniciativaController.muestraActividadesUsuario();
                    break;
                case 4:
                    // Muestra los premios disponibles
                    actividadController.mostrarPremios();
                    break;
                case 5:
                    // Añade un usuario a una actividad
                    actividadController.annadirUsuario();
                    break;
                case 6:
                    // Muestra los voluntarios asignados a una actividad
                    actividadController.mostrarVoluntariosAsignados();
                    break;
                case 7:
                    return opcion3; // Salir
            }

        } while (opcion3 != 7);

        return opcion3;
    }
}
