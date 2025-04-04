package controller;

import exceptions.AutenticacionException;
import model.Creador;
import model.Usuario;
import model.Voluntario;
import utils.Utilidades;
import view.MenuVista;

public class SistemaController {
    private UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();
    private UsuarioController usuarioController = new UsuarioController();
    private ActividadController actividadController = new ActividadController();
    private IniciativaController iniciativaController = new IniciativaController();

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
                    case 2: // Registro
                        MenuVista.muestraMenuCreadorOVoluntario();
                        numero = Utilidades.leeEntero(" >> Introduce la opción: ");
                        Usuario nuevoUsuario = MenuVista.pideDatosRegistrarUsuario(numero);
                        usuarioActualController.setUsuario(nuevoUsuario);
                        usuarioController.registrarUsuario(nuevoUsuario);
                        opcion = 3;
                        break;

                    case 3: // Salir
                        MenuVista.muestraMensaje("Saliendo del programa...");
                        continuar = false; // Cambiar la variable de control
                        break;
                }
            } while (opcion != 3 && continuar);

            // Verificación segura del tipo de usuario
            Usuario usuarioActual = usuarioActualController.getUsuario();
            if (usuarioActual instanceof Creador && terminar != 8) {
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

    public int controlarCreador(Creador creador) {
        int opcion2 = 0;
        do {
            MenuVista.menuCreador();
            opcion2 = Utilidades.leeEntero("Introduce la opcion: ");
            switch (opcion2) {
                case 1:
                    iniciativaController.creaIniciativa();
                    break;
                case 2:
                    iniciativaController.eliminaIniciativa();
                    break;
                case 3:
                    actividadController.creaActividad();
                    break;
                case 4:
                    actividadController.annadirUsuario();
                    break;
                case 5:
                    actividadController.eliminarUsuario();
                    break;
                case 6:
                    iniciativaController.muestraIniciativasNombre();
                    break;
                case 7:
                    iniciativaController.muestraIniciativas();
                    break;
                case 8:
                    break;
            }

        } while (opcion2 != 8);
        return opcion2;
    }

    public int controlarVoluntario() {
        int opcion3 = 0;
        do {
            MenuVista.menuVoluntarios();
            opcion3 = Utilidades.leeEntero("Introduce la opcion: ");
            switch (opcion3) {
                case 1:
                    actividadController.verActividades();
                    break;
                case 2:
                    actividadController.actualizarEstado();
                    break;
                case 3:
                    iniciativaController.muestraIniciativasNombre();
                    break;
                case 4:
                    actividadController.mostrarPremios();
                    break;
                case 5:
                    actividadController.annadirUsuario();
                    break;
                case 6:
                    actividadController.mostrarVoluntariosAsignados();

            }

        } while (opcion3 != 7);

        return opcion3;
    }


}

