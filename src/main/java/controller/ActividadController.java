package controller;

import model.*;
import utils.Utilidades;
import view.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class ActividadController {

    private UsuarioActualController usuarioActualController = new UsuarioActualController();
    /**
     * Metodo que crea una actividad
     *
     */
    public void creaActividad() {
        Voluntario voluntario = (Voluntario) usuarioActualController.getUsuario();
        Uti
    }

    /**
     * Método que elimina una actividad
     * @param creador El creador de la actividad
     * @param actividad La actividad a eliminar
     * @param iniciativa La iniciativa a la que pertenece la actividad
     *
     */
    public void eliminaActividad(Creador creador,Actividad actividad, Iniciativa iniciativa) {
        creador.eliminarActividad(actividad.getNombre(), iniciativa.getNombre());
    }

    /**
     * Método que crea una actividad con los datos modificados y elimina la actividad que se desea modificar
     * @param creador El creador de la actividad
     * @param actividad La actividad a modificar
     * @param iniciativa La iniciativa a la que pertenece la actividad
     */
    public void modificaActividad(Creador creador,Actividad actividad, Iniciativa iniciativa) {
        creaActividad(creador, iniciativa);
        eliminaActividad(creador, actividad, iniciativa);
    }

    /**
     * Método que muestra las actividades en las que está inscrito un voluntario
     * @param voluntario el voluntario del que se quieren ver las actividades a las que está inscrito
     */
    public void verActividades(Voluntario voluntario){
        for(Actividad actividad : voluntario.verActividades() ){
            System.out.println(actividad);
        }
    }


}