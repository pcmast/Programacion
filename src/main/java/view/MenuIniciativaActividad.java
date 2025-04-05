package view;

import model.Actividad;
import model.EstadoActividad;
import model.Iniciativa;
import model.Usuario;
import utils.Utilidades;

import java.time.LocalDate;

public class MenuIniciativaActividad {
    public static void muestraObjeto(Object o) {
        System.out.println(o);
    }

    /**
     * Metodo que pide los datos necesarios para crear una iniciativa.
     *
     * @param usuario El usuario el cual crea la iniciativa del que cojemos su nombre.
     * @return la iniciativa nueva.
     */
    public static Iniciativa pideDatosCrearIniciativa(Usuario usuario) {
        String nombre = Utilidades.pideString("Introduce el nombre de la iniciativa: ");
        String descripcion = Utilidades.pideString("Introduce una descripcion a la iniciativa: ");
        String creadorIniciativa = usuario.getNombre();

        Iniciativa iniciativaNueva = new Iniciativa(nombre, descripcion, creadorIniciativa);

        return iniciativaNueva;
    }

    /**
     * Método que pide los datos para crear una actividad con selección numérica de estado
     *
     * @return Actividad creada con los datos introducidos
     */
    public static Actividad pideDatosCrearActividad() {
        Actividad actividad = new Actividad();

        actividad.setNombre(Utilidades.pideString("Nombre de la actividad: "));
        actividad.setDescripcion(Utilidades.pideString("Descripción: "));
        actividad.setFechaInicio(Utilidades.pideFecha("Fecha inicio (dd/mm/aaaa): "));
        actividad.setFechaFin(Utilidades.pideFecha("Fecha fin (dd/mm/aaaa): "));

        // Menú de selección de estado
        actividad.setEstado(seleccionarEstado());

        return actividad;
    }

    /**
     * Método para seleccionar el estado de una actividad mediante opciones numéricas
     * @return EstadoActividad seleccionado
     */
    public static EstadoActividad seleccionarEstado() {
        System.out.println("\n══════════════════════════════");
        System.out.println("  SELECCIÓN DE ESTADO DE ACTIVIDAD");
        System.out.println("══════════════════════════════");
        System.out.println("1. No iniciada");
        System.out.println("2. En progreso");
        System.out.println("3. Completada");
        System.out.println("══════════════════════════════");

        int opcion;
        do {
            opcion = Utilidades.leeEntero("Seleccione el estado (1-3): ");
            if (opcion < 1 || opcion > 3) {
                System.out.println("❌ Opción inválida. Por favor ingrese 1, 2 o 3");
            }
        } while (opcion < 1 || opcion > 3);

        EstadoActividad estadoSeleccionado;
        switch(opcion) {
            case 1:
                estadoSeleccionado = EstadoActividad.NO_INICIADA;
                break;
            case 2:
                estadoSeleccionado = EstadoActividad.EN_PROCESO;
                break;
            case 3:
                estadoSeleccionado = EstadoActividad.COMPLETADA;
                break;
            default:
                estadoSeleccionado = EstadoActividad.NO_INICIADA;
        }

        System.out.printf("\n✅ Estado seleccionado: %s\n", estadoSeleccionado.getDescripcion());
        return estadoSeleccionado;
    }
}
