package controller;

import model.Actividad;
import model.EstadoActividad;
import utils.Utilidades;

import java.time.LocalDate;

public class ActividadController {

    /**
     * Metodo que crea una actividad nueva pidiendo los datos de la actividad
     * @return true si la actividad se creó correctamente, false en caso contrario.
     *
     */
    public static boolean creaActividad() {
        boolean actividadCreada = false;

        Utilidades.mostrarMensaje("Ingrese los datos de la actividad:");
        String nombreActividad = Utilidades.pideString("Nombre de la actividad:");
        String descripcionActividad = Utilidades.pideString("Descripción de la actividad:");
        LocalDate fechaInicio = Utilidades.pideFecha("Introduce la fecha de inicio (dd/MM/yyyy):");
        String fechaFin = Utilidades.pideString("Introduce la fecha de fin (dd/MM/yyyy):");
        Utilidades.mostrarMensaje("Seleccione el estado de la actividad:\n" +
                "1. NO INICIADA\n" +
                "2. EN PROCESO\n" +
                "3. COMPLETADA" );
        int opcion = Utilidades.leeEntero("Opción:");
        EstadoActividad estadoActividad = null;
        switch (opcion) {
            case 1:
                estadoActividad = EstadoActividad.NO_INICIADA;
                break;
            case 2:
                estadoActividad = EstadoActividad.EN_PROCESO;
                break;
            case 3:
                estadoActividad = EstadoActividad.COMPLETADA;
                break;
        }

        Actividad actividad = new Actividad(nombreActividad, descripcionActividad, fechaInicio, fechaFin, false, estadoActividad);
        // Llenar los datos de la actividad
        if (actividad!= null) {
            actividadCreada = true;
            Utilidades.mostrarMensaje("Actividad creada con éxito");
        }
        return actividadCreada;
    }
}
