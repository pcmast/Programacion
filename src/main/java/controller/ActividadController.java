package controller;

import dataAcces.XMLManager;
import dataAcces.XMLManagerActividades;
import dataAcces.XMLManagerIniciativas;
import model.*;
import utils.UsuariosContenedor;
import utils.Utilidades;
import view.*;

import java.io.File;
import java.util.ArrayList;

public class ActividadController {

    private UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();
    private UsuarioController usuarioController = new UsuarioController();
    private IniciativaController iniciativaController = IniciativaController.getInstancia();
    private ArrayList<Actividad> actividades;

    /**
     * Constructor de la clase. Inicializa la lista de actividades leyendo desde el XML.
     */
    public ActividadController() {
        actividades = (ArrayList<Actividad>) XMLManagerActividades.obtenerTodasActividades();
    }

    /**
     * Crea una nueva actividad, la asigna a una iniciativa existente y la guarda en la lista de actividades.
     */
    public void creaActividad() {
        iniciativaController.muestraIniciativasNombre();

        String nombreIniciativa = Utilidades.pideString("Introduce el nombre de la iniciativa:");
        Iniciativa iniciativa = iniciativaController.obtenerIniciativa(nombreIniciativa);

        if (iniciativa == null) {
            MenuVista.muestraMensaje("❌ Iniciativa no encontrada");
            return;
        }

        Actividad actividad = MenuIniciativaActividad.pideDatosCrearActividad();
        actividad.setIniciativa(iniciativa.getNombre());

        if (iniciativa.annadirList(actividad)) {
            // Guardar las iniciativas y actividades actualizadas
            iniciativaController.guardarIniciativas();
            actividades.add(actividad);
            guardarActividadesCrear(actividades);

            MenuVista.muestraMensaje("✅ Actividad creada correctamente en: " + iniciativa.getNombre());
        } else {
            MenuVista.muestraMensaje("❌ No se pudo crear la actividad (¿ya existe?)");
        }
    }

    /**
     * Guarda las actividades actuales en el archivo XML.
     */
    public void guardarActividades() {
        try {
            // Guardar las actividades en el archivo XML
            XMLManagerActividades.guardarActividades(actividades);
        } catch (Exception e) {
            System.err.println("Error al guardar actividades: " + e.getMessage());
            MenuVista.muestraMensaje("❌ Error al guardar las actividades.");
        }
    }

    /**
     * Guarda una lista específica de actividades en el archivo XML.
     * @param list La lista de actividades a guardar.
     */
    public void guardarActividadesCrear(ArrayList<Actividad> list) {
        try {
            XMLManagerActividades.guardarActividades(list);
        } catch (Exception e) {
            System.err.println("Error al guardar actividades: " + e.getMessage());
            MenuVista.muestraMensaje("❌ Error al guardar las actividades.");
        }
    }

    /**
     * Elimina una actividad de la lista de actividades, según el nombre de la iniciativa y la actividad.
     */
    public void eliminaActividad() {
        File archivo = new File("actividades.xml");
        ArrayList<Actividad> actividadesDesdeXML = new ArrayList<>();
        if (archivo.exists()) {
            actividadesDesdeXML = (ArrayList<Actividad>) XMLManagerActividades.obtenerTodasActividades();
        }

        boolean eliminado = false;
        String nombreIniciativa = Utilidades.pideString("Introduce el nombre de la iniciativa");
        String nombre = Utilidades.pideString("Introduce el nombre de la actividad a borrar");

        for (Actividad actividad : actividadesDesdeXML) {
            if (actividad.getNombre().equals(nombre)) {
                actividadesDesdeXML.remove(actividad);
                eliminado = iniciativaController.eliminarActividad(actividad, nombreIniciativa);
                break;
            }
        }

        if (eliminado) {
            MenuVista.muestraMensaje("Se ha eliminado correctamente.");
            try {
                guardarActividades();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        } else {
            MenuVista.muestraMensaje("No se ha podido eliminar la actividad.");
        }
    }


    /**
     * Muestra todas las actividades en las que está inscrito un voluntario.
     */
    public void verActividades() {
        File archivo = new File("actividades.xml");
        ArrayList<Actividad> actividadesDesdeXML = new ArrayList<>();

        if (archivo.exists()) {
            actividadesDesdeXML = (ArrayList<Actividad>) XMLManagerActividades.obtenerTodasActividades();

            if (actividadesDesdeXML == null || actividadesDesdeXML.isEmpty()) {
                MenuVista.muestraMensaje("📭 No hay actividades registradas en el sistema.");
            } else {
                MenuVista.muestraMensaje("📋 Lista de actividades disponibles:");
                MenuVista.muestraMensaje("----------------------------------");
                for (Actividad actividad : actividadesDesdeXML) {
                    MenuIniciativaActividad.muestraObjeto(actividad);
                    MenuVista.muestraMensaje("----------------------------------");
                }
            }
        } else {
            MenuVista.muestraMensaje("❌ El archivo de actividades no existe.");
        }
    }

    /**
     * Muestra los premios obtenidos por un voluntario, junto con los puntos actuales.
     */
    public void mostrarPremios() {
        Voluntario voluntario = (Voluntario) usuarioActualController.getUsuario();
        ArrayList<Premio> listaPremios = voluntario.verPremiosObtenidos();

        if (listaPremios.isEmpty()) {
            MenuVista.muestraMensaje("🎁 No has obtenido ningún premio todavía.");
        } else {
            MenuVista.muestraMensaje("🎉 Premios obtenidos:");
            MenuVista.muestraMensaje("------------------------------");
            for (Premio premio : listaPremios) {
                MenuIniciativaActividad.muestraObjeto(premio);
                MenuVista.muestraMensaje("------------------------------");
            }
        }

        MenuVista.muestraMensaje("🏅 Puntos actuales: " + voluntario.getPuntos());
    }

    /**
     * Actualiza el estado de una actividad y otorga puntos al voluntario que la realiza.
     */
    public void actualizarEstado() {
        Voluntario voluntario = (Voluntario) usuarioActualController.getUsuario();
        File archivo = new File("actividades.xml");

        if (!archivo.exists()) {
            MenuVista.muestraMensaje("❌ No hay actividades registradas en el sistema.");
            return;
        }

        ArrayList<Actividad> list = (ArrayList<Actividad>) XMLManagerActividades.obtenerTodasActividades();

        if (list == null || list.isEmpty()) {
            MenuVista.muestraMensaje("📭 No hay actividades disponibles para actualizar.");
            return;
        }

        // Mostrar las actividades disponibles
        MenuVista.muestraMensaje("📋 Lista de actividades:");
        MenuVista.muestraMensaje("----------------------------------");
        for (Actividad actividad : list) {
            MenuIniciativaActividad.muestraObjeto(actividad);
            MenuVista.muestraMensaje("----------------------------------");
        }

        // Solicitar al usuario el nombre de la actividad y el nuevo estado
        String nombre = Utilidades.pideString("Introduce el nombre de la actividad");
        EstadoActividad estadoActividad = MenuIniciativaActividad.seleccionarEstado();
        String comentario = Utilidades.pideString("Introduce un comentario");

        // Actualizar el estado de la actividad
        for (Actividad actividad : list) {
            if (actividad.getNombre().equals(nombre)) {
                actividad.actualizarEstado(estadoActividad, comentario);
                voluntario.otorgarPuntos();
            }
        }

        guardarActividades();
    }

    /**
     * Elimina un voluntario de una actividad específica.
     */
    public void eliminarUsuario() {
        boolean eliminarUsuario = false;

        String nombreVoluntario = Utilidades.pideString("Introduce el nombre del voluntario:");
        String nombreActividad = Utilidades.pideString("Introduce el nombre de la actividad:");
        ArrayList<Usuario> usuarios = usuarioController.getList();
        ArrayList<Actividad> list = actividades;

        if (list != null) {
            for (Actividad actividad : list) {
                if (actividad.getNombre().equals(nombreActividad)) {
                    for (Usuario usuario : usuarios) {
                        if (usuario.getNombre().equals(nombreVoluntario)) {
                            // Eliminar al voluntario de la actividad
                            actividad.eliminarList(usuario.getNombre());
                            ArrayList<String> listaVoluntarios = actividad.getVoluntario();
                            listaVoluntarios.remove(usuario.getNombre());
                            eliminarUsuario = true;

                            MenuVista.muestraMensaje(" ✅ El voluntario " + usuario.getNombre() + " ha sido eliminado de la actividad '" + actividad.getNombre() + "'.");
                        }
                    }
                }
                if (eliminarUsuario) {
                    break;
                }
            }
        }

        guardarActividades();

        if (!eliminarUsuario) {
            MenuVista.muestraMensaje("No se ha podido eliminar el usuario. Asegúrate de que el nombre del voluntario y la actividad son correctos.");
        }
    }

    /**
     * Muestra los voluntarios asignados a una actividad.
     */
    public void mostrarVoluntariosAsignados() {
        String nombreActividad = Utilidades.pideString("Introduce el nombre de la actividad:");
        boolean encontrada = false;

        for (Actividad actividad : actividades) {
            if (actividad.getNombre().equals(nombreActividad)) {
                encontrada = true;
                ArrayList<String> list = actividad.getVoluntario();
                if (list.isEmpty()) {
                    MenuVista.muestraMensaje("⚠️ No hay voluntarios asignados a esta actividad.");
                } else {
                    MenuVista.muestraMensaje("👥 Voluntarios asignados a \"" + nombreActividad + "\":");
                    for (String s : list) {
                        MenuVista.muestraMensaje(" - " + s);
                    }
                }
            }
        }

        if (!encontrada) {
            MenuVista.muestraMensaje("❌ No se encontró ninguna actividad con ese nombre.");
        }
    }

    /**
     * Añade un voluntario a una actividad existente.
     */
    public void annadirUsuario() {
        if (actividades == null || actividades.isEmpty()) {
            MenuVista.muestraMensaje("❌ No hay actividades disponibles.");
            return;
        }

        verActividades();

        String nombreVoluntario = Utilidades.pideString("Introduce el nombre del voluntario");
        String nombreActividad = Utilidades.pideString("Introduce el nombre de la actividad");

        ArrayList<Usuario> usuarios = usuarioController.getList();
        Usuario usuarioEncontrado = null;

        // Buscar al voluntario por nombre
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombreVoluntario)) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        if (usuarioEncontrado == null) {
            MenuVista.muestraMensaje("❌ Voluntario no encontrado.");
            return;
        }

        for (Actividad actividad : actividades) {
            if (actividad.getNombre().equals(nombreActividad)) {
                actividad.annadirList(usuarioEncontrado);
                MenuVista.muestraMensaje(" ✅ Voluntario añadido correctamente a la actividad.");
                break;
            }
        }

        guardarActividades();
    }
}
