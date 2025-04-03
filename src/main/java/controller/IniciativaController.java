package controller;

import dataAcces.XMLManagerIniciativas;
import model.*;
import utils.Utilidades;
import view.*;

import java.util.ArrayList;
import java.util.List;

public class IniciativaController {
    private UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();
    private ArrayList<Iniciativa> iniciativas;

    public IniciativaController() {
        this.iniciativas = (ArrayList<Iniciativa>) XMLManagerIniciativas.obtenerTodasIniciativas();
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

        // Eliminar de las iniciativas del creador
        ArrayList<Iniciativa> iniciativasCreador = creador.verIniciativas();
        for (Iniciativa iniciativa:iniciativasCreador){
            if (iniciativa.getNombre().equals(nombre)){
                iniciativasCreador.remove(iniciativa);
                eliminada = true;
            }
        }

        // Eliminar de la lista general
        if (eliminada) {
            for (Iniciativa iniciativa: iniciativas) {
                if (iniciativa.getNombre().equals(nombre)) {
                    iniciativas.remove(iniciativa);
                    break;
                }
            }

            guardarIniciativas();
            MenuVista.muestraMensaje("Iniciativa eliminada");
        } else {
            MenuVista.muestraMensaje("Iniciativa no encontrada");
        }
    }

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

        MenuVista.muestraMensaje("=== LISTADO DE INICIATIVAS ===");
        for (Iniciativa iniciativa : iniciativas) {
            MenuVista.muestraMensaje(iniciativa.toString());
        }
        MenuVista.muestraMensaje("=============================");
    }

    public void muestraIniciativasNombre() {
        if (iniciativas.isEmpty()) {
            MenuVista.muestraMensaje("No hay iniciativas registradas");
            return;
        }

        MenuVista.muestraMensaje("=== NOMBRES DE INICIATIVAS ===");
        for (Iniciativa iniciativa : iniciativas) {
             MenuVista.muestraMensaje("- " + iniciativa.getNombre());

        }
        MenuVista.muestraMensaje("==============================");
    }

    public void muestraIniciativasUsuario() {
        Usuario usuario = usuarioActualController.getUsuario();
        List<Iniciativa> iniciativasUsuario = new ArrayList<>();

        if (usuario instanceof Creador) {
            Creador creador = (Creador) usuario;
            iniciativasUsuario = creador.verIniciativas();
        } else if (usuario instanceof Voluntario) {
            Voluntario voluntario = (Voluntario) usuario;
            for (Actividad actividad : voluntario.getList()) {
                Iniciativa iniciativa = obtenerIniciativa(actividad.getIniciativa());
                if (iniciativa != null && !contieneIniciativa(iniciativasUsuario, iniciativa)) {
                    iniciativasUsuario.add(iniciativa);
                }
            }
        }

        if (iniciativasUsuario.isEmpty()) {
            MenuVista.muestraMensaje("No tienes iniciativas asignadas");
            return;
        }

        MenuVista.muestraMensaje("=== TUS INICIATIVAS ===");
        for (Iniciativa iniciativa : iniciativasUsuario) {
            MenuVista.muestraMensaje(iniciativa.toString());
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
