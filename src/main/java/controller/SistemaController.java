package controller;

import model.Creador;
import model.Usuario;
import model.Voluntario;
import utils.Utilidades;
import view.MenuVista;

public class SistemaController {
    private UsuarioActualController usuarioActualController = new UsuarioActualController();
    private UsuarioController usuarioController = new UsuarioController();
    private ActividadController actividadController = new ActividadController();
    private IniciativaController iniciativaController = new IniciativaController();

    public void sistemaControllerEmpezar() {

        Creador creador =null;
        Voluntario voluntario =null;
        int opcion = 0;
        int numero = 0;
        int terminar = 0;
        do {
            do {
                MenuVista.mostrarMenu();
                opcion = Utilidades.leeEntero("Introduce la opcion que quieras: ");

                switch (opcion) {
                    case 1:
                        usuarioController.iniciarSesion();
                        break;
                    case 2:
                       MenuVista.muestraMenuCreadorOVoluntario();
                        numero = Utilidades.leeEntero("Introduce la opcion");
                        if (numero == 1){
                        creador = (Creador) MenuVista.mostrarMenuPrincipal(opcion);
                            usuarioController.registrarUsuario(creador);
                        }else {
                            voluntario = (Voluntario) MenuVista.mostrarMenuPrincipal(opcion);
                            usuarioController.registrarUsuario(voluntario);
                        }
                        break;
                    default:
                        MenuVista.muestraMensaje("Introduce 1|2|3");
                }
            } while (opcion != 3);
            if (creador != null){
               terminar= controlarCreador(creador);
            }
            if (voluntario != null){
              terminar = controlarVoluntario();
            }


        }while (terminar != 6 && terminar !=4);
    }

    public int controlarCreador(Creador creador){
        int opcion2 = 0;
        do {
                MenuVista.menuCreador();
                opcion2 = Utilidades.leeEntero("Introduce la opcion: ");
                switch (opcion2){
                    case 1:
                        iniciativaController.creaIniciativa();
                        break;
                    case 2:
                        actividadController.creaActividad();
                        break;
                    case 3:
                        break;
                    case 4:
                        iniciativaController.muestraIniciativasNombre();
                        break;
                    case 5:
                        iniciativaController.muestraIniciativas();
                        break;
                }

        }while (opcion2 != 6);
        return opcion2;
    }
    public int controlarVoluntario(){
        int opcion3 = 0;
        do {
            MenuVista.menuVoluntarios();
            opcion3 = Utilidades.leeEntero("Introduce la opcion: ");
            switch (opcion3){
                case 1:
                    actividadController.verActividades();
                    break;
                case 2:
                    actividadController.actualizarEstado();
                    break;

            }

        }while (opcion3 != 4);

        return opcion3;
    }


}