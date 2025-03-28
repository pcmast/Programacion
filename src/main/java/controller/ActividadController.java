package controller;

import model.*;
import utils.Utilidades;
import view.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class ActividadController {

    private UsuarioActualController usuarioActualController = new UsuarioActualController();
    /**
     * Metodo que crea una actividad nueva y la añade a la lista de actividades del usuario actual
     *
     */
    public void creaActividad() {
        Creador creador = (Creador) usuarioActualController.getUsuario();
        Actividad actividad = MenuIniciativaActividad.pideDatosCrearActividad();
        boolean creado = creador.crearActividad(actividad, actividad.getIniciativa());
        if (creado){
            MenuVista.muestraMensaje("Se ha creado correctamente");
        }else {
            MenuVista.muestraMensaje("No se ha podido crear la actividad");
        }
    }

    /**
     * Método que elimina una actividad de la lista de actividades del usuario actual
     *
     */
    public void eliminaActividad() {
        boolean eliminado = false;
        Creador creador = (Creador) usuarioActualController.getUsuario();
        String nombre = Utilidades.pideString("Introduce el nombre de la actividad");
        for (Iniciativa iniciativa:creador.verIniciativas()){
            for (Actividad actividad:iniciativa.getList()){
                if (actividad.getNombre().equals(nombre)){
                    eliminado = actividad.eliminarList(nombre);
                    break;
                }
            }
        }
        if (eliminado){
            MenuVista.muestraMensaje("Se ha eliminado correctamente");
        }else {
            MenuVista.muestraMensaje("No se ha podido eliminar la actividad");
        }
    }

    /**
     * Método que modifica una actividad de la lista de actividades del usuario actual
     */
    public void modificaActividad() {
        boolean actualizado = false;
        Creador creador = (Creador) usuarioActualController.getUsuario();
        ArrayList<Iniciativa> list = creador.verIniciativas();
        Actividad actividad = MenuIniciativaActividad.pideDatosCrearActividad();
        for (Iniciativa iniciativa:list){
            actualizado=iniciativa.modificar(actividad);

        }
        if (actualizado){
            MenuVista.muestraMensaje("Se ha podido modificar correctamente");
        }else {
            MenuVista.muestraMensaje("No se ha podido modificar no existe la iniciativa");
        }
    }

    /**
     * Método que muestra las actividades en las que está inscrito un voluntario
     */
    public void verActividades(){
        Creador creador = (Creador) usuarioActualController.getUsuario();
        for (Iniciativa iniciativa:creador.verIniciativas()){
            for (Actividad actividad: iniciativa.obtenerTodos()){
                MenuVista.muestraMensaje(actividad.getNombre());
            }
        }
    }

    public void actualizarEstado(){
        Voluntario voluntario = (Voluntario) usuarioActualController.getUsuario();
    }


}