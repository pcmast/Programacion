package view;

import model.Creador;
import model.Usuario;
import model.Voluntario;
import utils.Utilidades;

public class MenuVista {
    public static void muestraMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    public static void muestraEntero(int numero){
        System.out.println(numero);
    }

    public static void mostrarMenu() {
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║    SISTEMA DE GESTIÓN DE VOLUNTARIADO     ║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.println("║  1. Iniciar Sesión                        ║");
        System.out.println("║  2. Registrarse                           ║");
        System.out.println("║  3. Salir                                 ║");
        System.out.println("╚═══════════════════════════════════════════╝");
    }

    /**
     * Menu que pide los datos para registrar un usuario, crearlo, sea creador o colaborador
     * @return el usuario con los datos ya registrados.
     */
    public static Usuario pideDatosRegistrarUsuario(int opcion) {
        System.out.println("");
        System.out.println("────────────────────────────────────────────");
        String nombre = Utilidades.pideString(" ⮞ 👤 Introduce tu nombre: ");
        String usuario = Utilidades.pideString(" ⮞ Introduce el usuario: ");
        String correo = Utilidades.pideCorreo(" ⮞ ✉ Introduce el correo: ");
        String contrasenna = Utilidades.pideString(" ⮞ 🔑 Introduce tu contraseña: ");

        Usuario usuario1 = null;
        switch (opcion) {
            case 1:
                String ong = Utilidades.pideString(" ⮞ 🤝 Introduce la ONG que pertenece: ");
                usuario1 = new Creador(nombre, usuario, contrasenna, correo,ong);
                break;
            case 2:
                usuario1 = new Voluntario(nombre, usuario, contrasenna, correo);
                break;
            default:
                System.out.println(" ❌ Opción incorrecta, vuelve a intentarlo");
        }
        return usuario1;
    }

    /**
     * Menu que pide los datos en caso de inicio de sesion.
     */
    public static void pideDatosInicioSesion() {
        System.out.println("────────────────────────────────────────────");
        String usuario = Utilidades.pideString(" ⮞ 👤 Introduce el usuario: ");
        String contrasenna = Utilidades.pideString(" ⮞ 🔑 Introduce la contraseña: ");
    }

    /**
     * Menu para seleccionar el tipo de usuario.
     */
    public static void muestraMenuCreadorOVoluntario() {
        System.out.println();
        System.out.println(" ➤ Elige el tipo de usuario: ");
        muestraMensaje("    1. Creador");
        muestraMensaje("    2. Voluntario");
    }

    /**
     * Menu de los usuarios creadores.
     */
    public static void menuCreador() {
        System.out.println("────────────────────────────────────────────");
        System.out.println(" ➤ Menú de Creador:");
        muestraMensaje("    1. Crear iniciativa");
        muestraMensaje("    2. Eliminar iniciativa");
        muestraMensaje("    3. Crear tareas de una iniciativa");
        muestraMensaje("    4. Agregar colaboradores a una iniciativa");
        muestraMensaje("    5. Eliminar colaboradores de una iniciativa");
        muestraMensaje("    6. Ver iniciativas creadas");
        muestraMensaje("    7. Ver iniciativas y actividades del usuario");
        muestraMensaje("    8. Cerrar Sesión");
    }

    /**
     * Menu de los usuarios voluntarios.
     */
    public static void menuVoluntarios() {
        System.out.println("────────────────────────────────────────────");
        System.out.println(" ➤ Menú de Voluntario:");
        muestraMensaje("    1. Ver tareas");
        muestraMensaje("    2. Cambiar estado actividad");
        muestraMensaje("    3. Ver iniciativas y actividades del usuario");
        muestraMensaje("    4. Mostrar Puntos y Premios");
        muestraMensaje("    5. Añadirte a una actividad");
        muestraMensaje("    6. Mostrar voluntarios asignados a una actividad");
        muestraMensaje("    7. Cerrar Sesión");
    }
}
