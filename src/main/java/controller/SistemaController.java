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
        int opcion = 0;
        int numero = 0;
        int terminar = 0;
        do {
            do {
                MenuVista.mostrarMenu();
                opcion = Utilidades.leeEntero(" >> Introduce la opcion que quieras: ");

                switch (opcion) {
                    case 1:
                        String correo = Utilidades.pideCorreo(" ✉ Introduce el correo de la cuenta: ");
                        String contrasenna = Utilidades.pideString(" \uD83D\uDD11 Introduce la contraseña: ");
                        usuarioController.iniciarSesion(correo, contrasenna);
                        opcion = 4;
                        break;
                    case 2:
                        MenuVista.muestraMenuCreadorOVoluntario();
                        numero = Utilidades.leeEntero(" >> Introduce la opcion: ");
                        if (numero == 1) {
                            creador = (Creador) MenuVista.pideDatosRegistrarUsuario(numero);
                            usuarioActualController.setUsuario(creador);
                            usuarioController.registrarUsuario(creador);
                            opcion = 4;
                        } else {
                            voluntario = (Voluntario) MenuVista.pideDatosRegistrarUsuario(numero);
                            usuarioActualController.setUsuario(voluntario);
                            usuarioController.registrarUsuario(voluntario);
                        }
                        break;
                    case 3:
                        terminar = 6;
                }
            } while (opcion != 4);

            if (creador != null && terminar != 6) {
                terminar = controlarCreador(creador);
                creador = null;
            }
            if (voluntario != null && terminar != 6) {
                terminar = controlarVoluntario();
                voluntario = null;
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