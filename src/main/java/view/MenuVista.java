package view;

import model.Creador;
import model.Usuario;
import model.Voluntario;
import utils.Utilidades;

public class MenuVista {

    /**
     * Muestra un mensaje en consola.
     *
     * @param mensaje El mensaje a mostrar.
     */
    public static void muestraMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Muestra un número entero en consola.
     *
     * @param numero El número a mostrar.
     */
    public static void muestraEntero(int numero){
        System.out.println(numero);
    }

    /**
     * Muestra el menú principal del sistema.
     */
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
     * Menú que pide los datos para registrar un usuario y crea el usuario
     * ya sea como creador o voluntario, según la opción seleccionada.
     *
     * @param opcion Opción seleccionada por el usuario (1 para creador, 2 para voluntario).
     * @return El usuario creado con los datos registrados.
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
                usuario1 = new Creador(nombre, usuario, contrasenna, correo, ong);
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
     * Menú que pide los datos necesarios para el inicio de sesión.
     */
    public static void pideDatosInicioSesion() {
        System.out.println("────────────────────────────────────────────");
        String usuario = Utilidades.pideString(" ⮞ 👤 Introduce el usuario: ");
        String contrasenna = Utilidades.pideString(" ⮞ 🔑 Introduce la contraseña: ");
    }

    /**
     * Menú para seleccionar el tipo de usuario (Creador o Voluntario).
     */
    public static void muestraMenuCreadorOVoluntario() {
        System.out.println();
        System.out.println(" ➤ Elige el tipo de usuario: ");
        muestraMensaje("    1. Creador");
        muestraMensaje("    2. Voluntario");
    }

    /**
     * Menú para los usuarios creadores con las opciones disponibles para gestionar iniciativas.
     */
    public static void menuCreador() {
        System.out.println("────────────────────────────────────────────");
        System.out.println(" ➤ Menú de Creador:");
        muestraMensaje("    1. Crear iniciativa");
        muestraMensaje("    2. Eliminar iniciativa");
        muestraMensaje("    3. Crear tareas de una iniciativa");
        muestraMensaje("    4. Eliminar Actividad");
        muestraMensaje("    5. Agregar colaboradores a una iniciativa");
        muestraMensaje("    6. Eliminar colaboradores de una iniciativa");
        muestraMensaje("    7. Ver iniciativas creadas");
        muestraMensaje("    8. Ver iniciativas y actividades del usuario");
        muestraMensaje("    9. Cerrar Sesión");
    }

    /**
     * Menú para los usuarios voluntarios con las opciones disponibles para gestionar actividades.
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
