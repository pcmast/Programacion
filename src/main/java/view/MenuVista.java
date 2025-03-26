
package view;

import model.Creador;
import model.Usuario;
import model.Voluntario;
import utils.Utilidades;


public class MenuVista {
    /**
     * Metodo que muestra un mensaje
     * @param mensaje la cadena que se muestra.
     */
    public void muestraMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Metodo que muestra un objeto
     * @param o el objeto que se muestra
     */
    public void muestraObjeto(Object o){
        System.out.println(o);
    }

    /**
     * Menu que pide los datos para registrar un usuario, crearlo, sea creador o colaborador.
     * @return el usuario con los datos ya registrados.
     */
    public Usuario pideDatosRegistrarUsuario() {
        String nombre = Utilidades.pideString("Introduce el nombre del usuario");
        String usuario = Utilidades.pideString("Introduce el usuario");

        String correo;
        do {
            correo = Utilidades.pideString("Introduce el correo electrónico");
            if (!Utilidades.validarCorreo(correo)) {
                System.out.println("Correo inválido, por favor introduce un correo válido.");
            }
        } while (!Utilidades.validarCorreo(correo));

        String contrasenna = Utilidades.pideString("Introduce tu contraseña");
        muestraMenuCreadorOVoluntario();
        int opcion = Utilidades.leeEntero("Introduce el tipo de usuario");
        Usuario usuario1 = null;
        switch (opcion) {
            case 1:
                String ong = Utilidades.pideString("Introduce la ONG a la que perteneces");
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
    public void pideDatosInicioSesion() {
        String usuario = Utilidades.pideString("Introduce el usuario");
        String contrasenna = Utilidades.pideString("Introduce la contraseña");
    }

    /**
     * Menu para seleccionar el tipo de usuario.
     */
    public void muestraMenuCreadorOVoluntario() {
        System.out.println("Elige el tipo de usuario");
        muestraMensaje("1. Creador");
        muestraMensaje("2. Voluntario");

    }

    /**
     * Menu de los usuarios creadores.
     */
    public void menuCreador() {
        System.out.println("1. Crear iniciativa\n" +
                "2.Crear tareas de una iniciativa\n" +
                "3. Agregar colaboradores a una iniciativa\n" +
                "4. Ver iniciativas creadas\n" +
                "5. Ver iniciativas y actividades del usuario\n" +
                "6. Cerrar sesion");
    }


    /**
     * Menu de los usuarios voluntarios.
     */
    public void menuVoluntarios() {
        System.out.println("1. Ver tareas asignadas\n" +
                "2. Cambiar estado actividad\n" +
                "3. Ver iniciativas y actividades del usuario\n" +
                "4. Cerrar sesion");
    }

}