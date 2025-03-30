package controller;

import dataAcces.XMLManager;
import model.*;
import utils.Utilidades;
import view.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class ActividadController {

    private UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();
    private  UsuarioController usuarioController = new UsuarioController();
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

    public void annadirUsuario(){
        boolean annadirUsuario = false;
        Creador creador = (Creador) usuarioActualController.getUsuario();
        String nombreVoluntario = Utilidades.pideString("Introduce el nombre del voluntario");
        String nombreIniciativa = Utilidades.pideString("Introduce el nombre la iniciativa");
        String nombreActividad = Utilidades.pideString("Introduce el nombre de la actividad");
        ArrayList<Iniciativa> list = creador.getList();
        if (list != null){
        for (Iniciativa iniciativa :list){
            if (iniciativa.getNombre().equals(nombreIniciativa)){
                ArrayList<Actividad> list1 = iniciativa.getList();
                if (list1 != null){
                for (Actividad actividad:list1){
                    if (actividad.getNombre().equals(nombreActividad)){
                        ArrayList<Usuario> list2 = usuarioController.getList();
                        if (list2 != null){
                        for (Usuario usuario:list2){
                            if (usuario.getUsuario().equals(nombreVoluntario)){
                              annadirUsuario = actividad.annadirList(usuario);
                            }
                        }
                        }
                    }

                }
                }
            }

        }
        }
        if (!annadirUsuario){
            MenuVista.muestraMensaje("No se a podido añadir el usuario");
        }


    }


}