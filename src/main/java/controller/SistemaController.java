package controller;

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
        int opcion, numero, terminar = 0;

        do {
            do {
                MenuVista.mostrarMenu();
                opcion = Utilidades.leeEntero(" >> Introduce la opción que quieras: ");

                switch (opcion) {
                    case 1:
                        String correo = Utilidades.pideCorreo(" ✉ Introduce el correo de la cuenta: ");
                        String contrasenna = Utilidades.pideString(" \uD83D\uDD11 Introduce la contraseña: ");
                        Usuario usuario = usuarioController.iniciarSesion(correo, contrasenna);

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
                            opcion = 4;
                        }
                        break;
                    case 2: // Registro
                        MenuVista.muestraMenuCreadorOVoluntario();
                        numero = Utilidades.leeEntero(" >> Introduce la opción: ");
                        Usuario nuevoUsuario = MenuVista.pideDatosRegistrarUsuario(numero);
                        usuarioActualController.setUsuario(nuevoUsuario);
                        usuarioController.registrarUsuario(nuevoUsuario);
                        opcion = 4;
                        break;

                    case 3: // Salir
                        terminar = 6;
                        break;
                }
            } while (opcion != 4);

            // Verificación segura del tipo de usuario
            Usuario usuarioActual = usuarioActualController.getUsuario();
            if (usuarioActual instanceof Creador) {
                creador = (Creador) usuarioActual;
                terminar = controlarCreador(creador);
                creador = null;
            } else if (usuarioActual instanceof Voluntario) {
                voluntario = (Voluntario) usuarioActual;
                terminar = controlarVoluntario();
                voluntario = null;
            } else if (terminar != 6) {
                System.out.println("Error: Tipo de usuario no reconocido");
            }

        } while (terminar == 6 || terminar == 4);
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
                    actividadController.creaActividad();
                    break;
                case 3:
                    actividadController.annadirUsuario();
                    break;
                case 4:
                    iniciativaController.muestraIniciativasNombre();
                    break;
                case 5:
                    iniciativaController.muestraIniciativas();
                    break;
            }

        } while (opcion2 != 6);
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


            }

        } while (opcion3 != 4);

        return opcion3;
    }


}