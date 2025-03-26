package controller;

import model.Actividad;
import model.Iniciativa;
import utils.Utilidades;
import view.*;

public class IniciativaController {

    /**
     * Método que pide los necesarios al usuario para crear una iniciativa.
     * @return Iniciativa con los datos introducidos por el usuario.
     */
    public Iniciativa creaIniciativa() {

        String nombreIniciativa = Utilidades.pideString("Introduce el nombre de la iniciativa:");
        String descripcionIniciativa = Utilidades.pideString("Introduce la descripción de la iniciativa:");
        String creadorIniciativa = Utilidades.pideString("Introduce el creador de la iniciativa:");

        Iniciativa iniciativa = new Iniciativa(nombreIniciativa, descripcionIniciativa, creadorIniciativa);
        return iniciativa;
    }

    /** por corregir
     * Método que elimina una iniciativa. (por ahora solo la deja en null)
     * @param iniciativa la iniciativa que queremos eliminar
     * @return true si se ha podido eliminar o false si no se ha podido.
     */
    public boolean eliminaIniciativa(Iniciativa iniciativa) {
        boolean eliminada = false;

        Utilidades.mostrarMensaje("¿Estás seguro de eliminar la iniciativa '" + iniciativa.getNombre() + "'?");
        int opcion = Utilidades.leeEntero("1. Sí\n2. No");

        switch (opcion) {
            case 1:
                iniciativa = null;
                eliminada = true;
                Utilidades.mostrarMensaje("Iniciativa eliminada correctamente.");
                break;

            case 2:
                Utilidades.mostrarMensaje("La iniciativa no ha sido eliminada.");
                break;
        }
        return eliminada;

    }

    /**
     * Método que modifica la iniciativa pidiendo que se introduzcan los datos de la iniciativa.
     * @param iniciativa la iniciativa que queremos modificar
     * @return true si se ha podido modificar o false si no se ha podido.
     */
    public boolean modificarIniciativa(Iniciativa iniciativa){
        boolean modificada = false;
        Iniciativa iniciativaModificada = creaIniciativa();
        iniciativa = iniciativaModificada;
        modificada = true;
        return modificada;
    }

}
