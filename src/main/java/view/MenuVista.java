
package view;

import model.Creador;
import model.Usuario;
import model.Voluntario;
import utils.Utilidades;


public class MenuVista {
    public static void muestraMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public static void mostrarMenu() {
        System.out.println("\n--------Sistema de Gestión de Voluntariado--------");
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Registrarse");
        System.out.println("3. Entrar");
        System.out.println("4. Salir");

    }


    /**
     * Menu que pide los datos para registrar un usuario, crearlo, sea creador o colaborador
     *
     * @return el usuario con los datos ya registrados.
     */
    public static Usuario pideDatosRegistrarUsuario(int opcion) {
        String nombre = Utilidades.pideString("Introduce el nombre del usuario: ");
        String usuario = Utilidades.pideString("Introduce el usuario: ");
        String correo = Utilidades.pideString("Introduce el correo: ");
        String contrasenna = Utilidades.pideString("Introduce tu contraseña: ");

        Usuario usuario1 = null;
        switch (opcion) {
            case 1:
                String ong = Utilidades.pideString("Introduce la ong que pertenece: ");
                usuario1 = new Creador(nombre, usuario, contrasenna, correo, ong);
                break;
            case 2:
                usuario1 = new Voluntario(nombre, usuario, contrasenna, correo);
                break;
            default:
                System.out.println("Opción incorrecta, vuelve a intentarlo");
        }
        return usuario1;
    }

    /**
     * Menu que pide los datos en caso de inicio de sesion.
     */
    public static void pideDatosInicioSesion() {
        String usuario = Utilidades.pideString("Introduce el usuario: ");
        String contrasenna = Utilidades.pideString("Introduce la contraseña: ");
    }

    /**
     * Menu que pide el usuario
     * @return el nombre de usuario.
     */
    public static String pideUsuario() {
        String usuario = Utilidades.pideString("Introduce el usuario: ");
        return usuario;
    }

    /**
     * Menu que pide la contraseña
     * @return la contraseña.
     */
    public static String pideContrasenna(){
        String contrasenna = Utilidades.pideString("Introduce la contraseña");
        return contrasenna;
    }

    /**
     * Menu para seleccionar el tipo de usuario.
     */
    public static void muestraMenuCreadorOVoluntario() {
        System.out.println("Elige el tipo de usuario: ");
        muestraMensaje("1. Creador");
        muestraMensaje("2. Voluntario");
    }

    /**
     * Menu de los usuarios creadores.
     */
    public static void menuCreador() {
        System.out.println("1. Crear iniciativa\n" +
                "2. Crear tareas de una iniciativa\n" +
                "3. Agregar colaboradores a una iniciativa\n" +
                "4. Ver iniciativas creadas\n" +
                "5. Ver iniciativas y actividades del usuario\n" +
                "6. Cerrar sesion");
    }

    /**
     * Menu de los usuarios voluntarios.
     */
    public static void menuVoluntarios() {
        System.out.println("1. Ver tareas asignadas\n" +
                "2. Cambiar estado actividad\n" +
                "3. Ver iniciativas y actividades del usuario\n" +
                "4. Cerrar sesion");
    }

}