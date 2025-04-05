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


    /**
     * Constructor privado para inicializar el controlador con las iniciativas obtenidas
     * desde el archivo XML.
     */
    private IniciativaController() {
        this.iniciativas = (ArrayList<Iniciativa>) XMLManagerIniciativas.obtenerTodasIniciativas();
    }

    /**
     * Método para obtener la instancia única del controlador (patrón Singleton).
     *
     * @return instancia única del controlador de iniciativas.
     */
    public static IniciativaController getInstancia() {
        if (instancia == null) {
            instancia = new IniciativaController();
        }
        return instancia;
    }

    /**
     * Método que crea una nueva iniciativa. Verifica si ya existe una con el mismo nombre
     * y, si no existe, la agrega a la lista y la guarda.
     */
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
     * Método para eliminar una iniciativa, verificando que exista y luego
     * eliminándola tanto de la lista de iniciativas como de las actividades asociadas.
     */
    public void eliminaIniciativa() {
        String nombre = Utilidades.pideString("Nombre de la iniciativa a eliminar:");
        Creador creador = (Creador) usuarioActualController.getUsuario();
        boolean eliminada = false;

        for (Iniciativa iniciativa : iniciativas) {
            if (iniciativa.getNombre().equals(nombre)) {
                iniciativas.remove(iniciativa);
                eliminada = true;
                break;
            }
        }

        // Si la iniciativa fue eliminada, se eliminan las actividades asociadas
        if (eliminada) {
            eliminaActividad(nombre);
        }

        guardarIniciativas();
        MenuVista.muestraMensaje("Iniciativa eliminada");
    }

    /**
     * Elimina una actividad asociada a una iniciativa dada.
     *
     * @param actividad        la actividad a eliminar.
     * @param nombreIniciativa el nombre de la iniciativa a la que está asociada la actividad.
     * @return true si la actividad fue eliminada exitosamente.
     */
    public boolean eliminarActividad(Actividad actividad, String nombreIniciativa) {
        boolean eliminado = false;
        ArrayList<Iniciativa> listaIniciativas = (ArrayList<Iniciativa>) XMLManagerIniciativas.obtenerTodasIniciativas();
        ArrayList<Actividad> listaTemporal = new ArrayList<>();

        // Busca la iniciativa en la lista y elimina la actividad
        for (Iniciativa iniciativa : listaIniciativas) {
            if (iniciativa.getNombre().equals(nombreIniciativa)) {
                listaTemporal = iniciativa.getList();
                listaTemporal.remove(actividad);
                eliminado = true;
                iniciativa.setList(listaTemporal);
                break;
            }
        }

        // Si la actividad fue eliminada, se actualiza la lista de iniciativas
        if (eliminado) {
            iniciativas = listaIniciativas;
        }
        guardarIniciativas();
        return eliminado;
    }

    /**
     * Elimina todas las actividades asociadas a una iniciativa específica
     * y actualiza el archivo de actividades XML.
     *
     * @param nombreIniciativa el nombre de la iniciativa cuya actividades se van a eliminar.
     */
    public void eliminaActividad(String nombreIniciativa) {
        File archivo = new File("actividades.xml");
        ArrayList<Actividad> actividadesDesdeXML = new ArrayList<>();

        // Verifica si existe el archivo de actividades
        if (archivo.exists()) {
            actividadesDesdeXML = (ArrayList<Actividad>) XMLManagerActividades.obtenerTodasActividades();
        }

        boolean eliminado = false;

        // Elimina las actividades asociadas a la iniciativa
        for (Actividad actividad : actividadesDesdeXML) {
            if (actividad.getIniciativa().equals(nombreIniciativa)) {
                actividadesDesdeXML.remove(actividad);
                eliminarActividad(actividad, nombreIniciativa);
                break;
            }
        }

        // Guarda los cambios en el archivo XML
        MenuVista.muestraMensaje("Se ha eliminado correctamente.");
        try {
            XMLManagerActividades.guardarActividades(actividadesDesdeXML);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }


    /**
     * Muestra todas las iniciativas creadas por el usuario actual.
     */
    public void muestraIniciativas() {
        if (iniciativas.isEmpty()) {
            MenuVista.muestraMensaje("No hay iniciativas registradas");
            return;
        }

        Creador creador = (Creador) usuarioActualController.getUsuario();
        MenuVista.muestraMensaje("=== LISTADO DE INICIATIVAS ===");
        for (Iniciativa iniciativa : iniciativas) {
            if (iniciativa.getCreadorIniciativa().equals(creador.getNombre())) {
                MenuVista.muestraMensaje(iniciativa.toString());
            }
        }
        MenuVista.muestraMensaje("=============================");
    }

    /**
     * Muestra solo los nombres de las iniciativas creadas por el usuario actual.
     */
    public void muestraIniciativasNombre() {
        if (iniciativas.isEmpty()) {
            MenuVista.muestraMensaje("No hay iniciativas registradas");
            return;
        }

        Creador creador = (Creador) usuarioActualController.getUsuario();
        MenuVista.muestraMensaje("=== NOMBRES DE INICIATIVAS ===");
        for (Iniciativa iniciativa : iniciativas) {
            if (iniciativa.getCreadorIniciativa().equals(creador.getNombre())) {
                MenuVista.muestraMensaje("- " + iniciativa.getNombre());
            }
        }
        MenuVista.muestraMensaje("==============================");
    }

    //borrar

    /**
     * Muestra todas las actividades asignadas al usuario actual.
     */
    public void muestraActividadesUsuario() {
        Usuario usuario = usuarioActualController.getUsuario();
        ArrayList<Actividad> listaActividades = (ArrayList<Actividad>) XMLManagerActividades.obtenerTodasActividades();
        ArrayList<Actividad> listaDeActividadesAnnadidos = new ArrayList<>();

        // Filtra las actividades asignadas al usuario
        for (Actividad actividad : listaActividades) {
            ArrayList<String> listaNombreUsuarios = actividad.getVoluntario();
            for (String nombre : listaNombreUsuarios) {
                if (nombre.equals(usuario.getNombre())) {
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

    /**
     * Guarda la lista de iniciativas en el archivo XML.
     */
    public void guardarIniciativas() {
        try {
            XMLManagerIniciativas.guardarIniciativas(iniciativas);
        } catch (Exception e) {
            System.err.println("Error al guardar iniciativas: " + e.getMessage());
            MenuVista.muestraMensaje("❌ Error al guardar las iniciativas.");
        }
    }

    /**
     * Verifica si ya existe una iniciativa con el nombre proporcionado.
     *
     * @param nombre el nombre de la iniciativa a verificar.
     * @return true si la iniciativa ya existe, false si no existe.
     */
    private boolean iniciativaExiste(String nombre) {
        for (Iniciativa iniciativa : iniciativas) {
            if (iniciativa.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene una iniciativa a partir de su nombre.
     *
     * @param nombre el nombre de la iniciativa a obtener.
     * @return la iniciativa si se encuentra, null si no se encuentra.
     */
    public Iniciativa obtenerIniciativa(String nombre) {
        for (Iniciativa iniciativa : iniciativas) {
            if (iniciativa.getNombre().equalsIgnoreCase(nombre)) {
                return iniciativa;
            }
        }
        return null;
    }

    /**
     * Verifica si una iniciativa ya está presente en una lista de iniciativas.
     *
     * @param lista      la lista de iniciativas.
     * @param iniciativa la iniciativa a verificar.
     * @return true si la iniciativa está en la lista, false si no lo está.
     */
    private boolean contieneIniciativa(List<Iniciativa> lista, Iniciativa iniciativa) {
        for (Iniciativa i : lista) {
            if (i.equals(iniciativa)) {
                return true;
            }
        }
        return false;
    }
}
