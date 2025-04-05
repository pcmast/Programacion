package controller;

import dataAcces.XMLManagerActividades;
import dataAcces.XMLManagerIniciativas;
import model.*;
import utils.Utilidades;
import view.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class IniciativaController {
    private UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();
    private ArrayList<Iniciativa> iniciativas;
    private static IniciativaController instancia;


    private IniciativaController() {
        this.iniciativas = (ArrayList<Iniciativa>) XMLManagerIniciativas.obtenerTodasIniciativas();
    }
    public static IniciativaController getInstancia() {
        if (instancia == null) {
            instancia = new IniciativaController();
        }
        return instancia;
    }


    public void creaIniciativa() {
        Creador creador = (Creador) usuarioActualController.getUsuario();
        Iniciativa iniciativa = MenuIniciativaActividad.pideDatosCrearIniciativa(creador);

        if (iniciativaExiste(iniciativa.getNombre())) {
            MenuVista.muestraMensaje("Error: Iniciativa ya existe");
            return;
        }

        if (creador.crearIniciativa(iniciativa)) {
            iniciativas.add(iniciativa);
            guardarIniciativas();

            MenuVista.muestraMensaje("Iniciativa creada con éxito");
        } else {
            MenuVista.muestraMensaje("Error al crear iniciativa");
        }
    }


    /**
     * Elimina una iniciativa por su nombre y hace la comprobacion de que existe y la elimina de la lista de creador
     * y luego de la lista donde
     * estan todas las iniciativas
     */
    public void eliminaIniciativa() {
        String nombre = Utilidades.pideString("Nombre de la iniciativa a eliminar:");
        Creador creador = (Creador) usuarioActualController.getUsuario();
        boolean eliminada = false;

        // Eliminar de la lista general
        for (Iniciativa iniciativa: iniciativas) {
            if (iniciativa.getNombre().equals(nombre)) {
                iniciativas.remove(iniciativa);
                eliminada = true;
                break;
            }
        }
        if (eliminada){
            eliminaActividad(nombre);
        }

        guardarIniciativas();
        MenuVista.muestraMensaje("Iniciativa eliminada");
    }

    public boolean eliminarActividad(Actividad actividad, String nombreIniciativa){
        boolean eliminado = false;
        ArrayList<Iniciativa> listaIniciativas = (ArrayList<Iniciativa>) XMLManagerIniciativas.obtenerTodasIniciativas();
        ArrayList<Actividad> listaTemporal = new ArrayList<>();
        for (Iniciativa iniciativa : listaIniciativas) {
            if (iniciativa.getNombre().equals(nombreIniciativa)) {
                listaTemporal = iniciativa.getList();
                listaTemporal.remove(actividad);
                eliminado = true;
                iniciativa.setList(listaTemporal);
                break;
            }
        }
        if (eliminado){
            iniciativas = listaIniciativas;
        }
        guardarIniciativas();
        return eliminado;
    }

    public void eliminaActividad(String nombreIniciativa) {
        File archivo = new File("actividades.xml");
        ArrayList<Actividad> actividadesDesdeXML = new ArrayList<>();
        if (archivo.exists()) {
            actividadesDesdeXML = (ArrayList<Actividad>) XMLManagerActividades.obtenerTodasActividades();
        }

        boolean eliminado = false;

        for (Actividad actividad : actividadesDesdeXML) {
            if (actividad.getIniciativa().equals(nombreIniciativa)) {
                actividadesDesdeXML.remove(actividad);
                eliminarActividad(actividad,nombreIniciativa);
                break;
            }
        }
            MenuVista.muestraMensaje("Se ha eliminado correctamente.");
            try {
                XMLManagerActividades.guardarActividades(actividadesDesdeXML);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

    }



    //borrar
    public void modificarIniciativa() {
        String nombreActual = Utilidades.pideString("Nombre de la iniciativa a modificar:");
        Creador creador = (Creador) usuarioActualController.getUsuario();
        Iniciativa iniciativa = obtenerIniciativa(nombreActual);

        if (iniciativa != null) {
            Iniciativa nuevosDatos = MenuIniciativaActividad.pideDatosCrearIniciativa(creador);
            iniciativa.setNombre(nuevosDatos.getNombre());
            iniciativa.setDescripcion(nuevosDatos.getDescripcion());
            guardarIniciativas();
            MenuVista.muestraMensaje("Iniciativa modificada");
        } else {
            MenuVista.muestraMensaje("Iniciativa no encontrada");
        }
    }

    public void muestraIniciativas() {
        if (iniciativas.isEmpty()) {
            MenuVista.muestraMensaje("No hay iniciativas registradas");
            return;
        }


        Creador creador = (Creador) usuarioActualController.getUsuario();
        MenuVista.muestraMensaje("=== LISTADO DE INICIATIVAS ===");
        for (Iniciativa iniciativa : iniciativas) {
            if(iniciativa.getCreadorIniciativa().equals(creador.getNombre())){
                MenuVista.muestraMensaje(iniciativa.toString());
            }
        }
        MenuVista.muestraMensaje("=============================");
    }

    public void muestraIniciativasNombre() {

        if (iniciativas.isEmpty()) {
            MenuVista.muestraMensaje("No hay iniciativas registradas");
            return;
        }

        Creador creador = (Creador) usuarioActualController.getUsuario();

        MenuVista.muestraMensaje("=== NOMBRES DE INICIATIVAS ===");
        for (Iniciativa iniciativa : iniciativas) {
            if(iniciativa.getCreadorIniciativa().equals(creador.getNombre())){
                MenuVista.muestraMensaje("- " + iniciativa.getNombre());
            }

        }
        MenuVista.muestraMensaje("==============================");

    }
    //borrar
    public void muestraActividadesUsuario() {
        Usuario usuario = usuarioActualController.getUsuario();
        ArrayList<Actividad> listaActividades = (ArrayList<Actividad>) XMLManagerActividades.obtenerTodasActividades();
        ArrayList<Actividad> listaDeActividadesAnnadidos = new ArrayList<>();
        for (Actividad actividad:listaActividades){
            ArrayList<String> listaNombreUsuarios = actividad.getVoluntario();
            for (String nombre: listaNombreUsuarios){
                if (nombre.equals(usuario.getNombre())){
                listaDeActividadesAnnadidos.add(actividad);
                }
            }
        }
        if (listaActividades.isEmpty()) {
            MenuVista.muestraMensaje("No tienes iniciativas asignadas");
            return;
        }

        MenuVista.muestraMensaje("=== TUS ACTIVIDADES ===");
        for (Actividad actividad : listaDeActividadesAnnadidos) {
            MenuVista.muestraMensaje(actividad.toString());
        }
        MenuVista.muestraMensaje("=======================");
    }



    public void guardarIniciativas() {
        try {
            // Guardar las iniciativas en el XML
            XMLManagerIniciativas.guardarIniciativas(iniciativas);

            MenuVista.muestraMensaje("✅ Iniciativas guardadas correctamente.");
        } catch (Exception e) {
            System.err.println("Error al guardar iniciativas: " + e.getMessage());
            MenuVista.muestraMensaje("❌ Error al guardar las iniciativas.");
        }
    }

    private boolean iniciativaExiste(String nombre) {
        for (Iniciativa iniciativa : iniciativas) {
            if (iniciativa.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    public Iniciativa obtenerIniciativa(String nombre) {
        for (Iniciativa iniciativa : iniciativas) {
            if (iniciativa.getNombre().equalsIgnoreCase(nombre)) {
                return iniciativa;
            }
        }
        return null;
    }

    private boolean contieneIniciativa(List<Iniciativa> lista, Iniciativa iniciativa) {
        for (Iniciativa i : lista) {
            if (i.equals(iniciativa)) {
                return true;
            }
        }
        return false;
    }
}