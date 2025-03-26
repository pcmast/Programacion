package controller;

import model.Actividad;
import model.EstadoActividad;
import utils.Utilidades;

import java.time.LocalDate;
import java.util.ArrayList;

public class ActividadController {

    /**
     * Metodo que crea una actividad nueva pidiendo los datos de la actividad
     *
     * @return true si la actividad se creó correctamente, false en caso contrario.
     */
    public Actividad creaActividad() {

        boolean actividadCreada = false;

        Utilidades.mostrarMensaje("Ingrese los datos de la actividad:");
        String nombreActividad = Utilidades.pideString("Nombre de la actividad:");
        String descripcionActividad = Utilidades.pideString("Descripción de la actividad:");
        LocalDate fechaInicio = Utilidades.pideFecha("Introduce la fecha de inicio (dd/MM/yyyy):");
        String fechaFin = Utilidades.pideString("Introduce la fecha de fin (dd/MM/yyyy):");
        Utilidades.mostrarMensaje("Seleccione el estado de la actividad:\n" +
                "1. NO INICIADA\n" +
                "2. EN PROCESO\n" +
                "3. COMPLETADA");
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
        if (actividad != null) {
            actividadCreada = true;
            Utilidades.mostrarMensaje("Actividad creada con éxito");
        }
        return actividad;
    }

    /**
     * Metodo que primero comprueba que la lista de actividades no este vacia y despues muestra la lista de actividades. De esa lista se introduce el nomnbre
     * de la actividad que se quiere eliminar y si esta actividad está en la lista de actividades se elimina.
     *
     * @return true si la actividad se ha eliminado correctamente, false en caso contrario.
     *
     */
    public Actividad eliminaActividad(Actividad actividad) {
        boolean actividadEliminada = false;
        Utilidades.mostrarMensaje("Está seguro de eliminar la actividad "+ actividad.getNombre()+ " ?");
        Utilidades.mostrarMensaje("1. Si");
        Utilidades.mostrarMensaje("2. No");
        int opcion = Utilidades.leeEntero("Opción:");
        switch (opcion) {
            case 1:
               actividad = null;
                break;
            case 2:
                Utilidades.mostrarMensaje("Operación cancelada");
                break;
        }

        return actividad;
    }


    public Actividad modificaActividad(Actividad actividad) {
        boolean actividadModificada = false;
        Actividad actvididadmodificada = creaActividad();
        return actvididadmodificada;
    }


}

