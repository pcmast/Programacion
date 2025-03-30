package controller;

import dataAcces.XMLManager;
import model.*;
import utils.Utilidades;
import view.*;

import java.util.ArrayList;
import java.util.Vector;

public class IniciativaController {
    private UsuarioActualController usuarioActualController =UsuarioActualController.getInstance();
    /**
     * Método que crea una iniciativa para un creador
     *
     */
    public void creaIniciativa() {
        try {
            ArrayList<Iniciativa> iniciativasDesdeXML = XMLManager.readXML(new ArrayList<>(), "iniciativas.xml");
            if (iniciativasDesdeXML != null) {
                Creador creador = (Creador) usuarioActualController.getUsuario();
                creador.setList(iniciativasDesdeXML);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        Creador creador = (Creador) usuarioActualController.getUsuario();
        Iniciativa iniciativa = MenuIniciativaActividad.pideDatosCrearIniciativa(creador);
        boolean creado = creador.crearIniciativa(iniciativa);

        if (creado) {
            MenuVista.muestraMensaje("Se ha creado correctamente.");
            try {
                XMLManager.writeXML(creador.verIniciativas(), "iniciativas.xml");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        } else {
            MenuVista.muestraMensaje("No se ha podido crear la iniciativa.");
        }
    }


    /** por corregir
     * Método que elimina una iniciativa. (por ahora solo la deja en null)
     * @param
     */
    public void eliminaIniciativa() {
        try {
            ArrayList<Iniciativa> iniciativasDesdeXML = XMLManager.readXML(new ArrayList<>(), "iniciativas.xml");
            if (iniciativasDesdeXML != null) {
                Creador creador = (Creador) usuarioActualController.getUsuario();
                creador.setList(iniciativasDesdeXML);
            }
        } catch (RuntimeException e) {
            e.printStackTrace(); // Registrar error sin interacción con el usuario.
        }

        boolean eliminado = false;
        Creador creador = (Creador) usuarioActualController.getUsuario();
        ArrayList<Iniciativa> list = creador.verIniciativas();
        String nombre = Utilidades.pideString("Introduce el nombre de la iniciativa");

        for (Iniciativa iniciativa : list) {
            eliminado = iniciativa.eliminarList(nombre);
        }

        if (eliminado) {
            MenuVista.muestraMensaje("Se ha eliminado correctamente.");
            try {
                XMLManager.writeXML(creador.verIniciativas(), "iniciativas.xml");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        } else {
            MenuVista.muestraMensaje("No se ha podido eliminar la iniciativa.");
        }
    }


    /**
     * Método que modifica una iniciativa pidiendo al usuario que cree una nueva con los datos actualizados y eliminando la anterior
     *
     */
    public void modificarIniciativa() {
        try {
            ArrayList<Iniciativa> iniciativasDesdeXML = XMLManager.readXML(new ArrayList<>(), "iniciativas.xml");
            if (iniciativasDesdeXML != null) {
                Creador creador = (Creador) usuarioActualController.getUsuario();
                creador.setList(iniciativasDesdeXML);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        // Modificar iniciativa.
        boolean actualizado = false;
        Creador creador = (Creador) usuarioActualController.getUsuario();
        ArrayList<Iniciativa> list = creador.verIniciativas();
        Iniciativa iniciativa = MenuIniciativaActividad.pideDatosCrearIniciativa(creador);

        for (Iniciativa iniciativa1 : list) {
            actualizado = iniciativa1.modificar(iniciativa);
        }

        if (actualizado) {
            MenuVista.muestraMensaje("Se ha podido modificar correctamente.");
            try {
                XMLManager.writeXML(creador.verIniciativas(), "iniciativas.xml");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        } else {
            MenuVista.muestraMensaje("No se ha podido modificar la iniciativa.");
        }
    }


    /**
     * Método que muestra todas las iniciativas de un creador
     *
     */
    public void muestraIniciativas() {
        try {
            ArrayList<Iniciativa> iniciativasDesdeXML = XMLManager.readXML(new ArrayList<>(), "iniciativas.xml");
            if (iniciativasDesdeXML != null) {
                Creador creador = (Creador) usuarioActualController.getUsuario();
                creador.setList(iniciativasDesdeXML);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        Creador creador = (Creador) usuarioActualController.getUsuario();
        ArrayList<Iniciativa> list = creador.verIniciativas();

        for (Iniciativa iniciativa : list) {
            MenuIniciativaActividad.muestraObjeto(iniciativa);
            ArrayList<Actividad> list1 = iniciativa.getList();
            for (Actividad actividad : list1) {
                MenuIniciativaActividad.muestraObjeto(actividad);
            }
        }
    }

    public void muestraIniciativasNombre(){
        Creador creador = (Creador) usuarioActualController.getUsuario();
        ArrayList<Iniciativa> list = creador.verIniciativas();
        for (Iniciativa iniciativa:list){
            MenuVista.muestraMensaje(iniciativa.getNombre());
        }
    }

    public void muestraIniciativasUsuario(){
        Voluntario voluntario = (Voluntario) usuarioActualController.getUsuario();
        ArrayList<Actividad> list = voluntario.getList();
        for (Actividad actividad:list){
            MenuIniciativaActividad.muestraObjeto(actividad);
        }

    }



}