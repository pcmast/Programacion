package controller;

import model.Actividad;
import model.Iniciativa;
import model.Usuario;
import model.Creador;
import utils.Utilidades;
import view.*;

public class IniciativaController {

    /**
     * Método que crea una iniciativa para un creador
     * @param creador el creador que creará la iniciativa
     */
    public void creaIniciativa(Creador creador) {
        creador.crearIniciativa(menuIniciativaActividad.pideDatosCrearIniciativa(creador));
    }

    /** por corregir
     * Método que elimina una iniciativa. (por ahora solo la deja en null)
     * @param iniciativa la iniciativa que queremos eliminar
     */
    public void eliminaIniciativa(Creador creador,Iniciativa iniciativa) {
        creador.eliminarIniciativa(iniciativa.getNombre(),creador.getNombre());
    }

    /**
     * Método que modifica una iniciativa pidiendo al usuario que cree una nueva con los datos actualizados y eliminando la anterior
     * @param creador el creador que modificará la iniciativa y creará la iniciativa nueva
     * @param iniciativa la iniciativa que queremos modificar
     */
    public void modificarIniciativa(Creador creador,Iniciativa iniciativa){
        creaIniciativa(creador);
        eliminaIniciativa(creador, iniciativa);
    }

    /**
     * Método que muestra todas las iniciativas de un creador
     * @param creador el creador que queremos mostrar las iniciativas
     */
    public void muestraIniciativas(Creador creador) {
        Utilidades.mostrarMensaje("Iniciativas de " + creador.getNombre() + ":" );
        creador.verIniciativas();
    }



}
