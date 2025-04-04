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

    public ActividadController() {
            actividades = (ArrayList<Actividad>) XMLManagerActividades.obtenerTodasActividades();

    }

    /**
     * Metodo que crea una actividad nueva y la añade a la lista de actividades del usuario actual
     */
    public void creaActividad() {

        iniciativaController.muestraIniciativasNombre();

        String nombreIniciativa = Utilidades.pideString("Introduce el nombre de la iniciativa:");
        Iniciativa iniciativa = iniciativaController.obtenerIniciativa(nombreIniciativa);

        if (iniciativa == null) {
            MenuVista.muestraMensaje("❌ Iniciativa no encontrada");
            //Como parar AQUI sin el uso de return
        }

        // 3. Crear la actividad
        Actividad actividad = MenuIniciativaActividad.pideDatosCrearActividad();
        if (iniciativa != null) {
            actividad.setIniciativa(iniciativa.getNombre()); // Establecer relación
        } else {
            return; //Como parar AQUI sin el uso de return
        }

        // 4. Añadir a la iniciativa
        if (iniciativa.annadirList(actividad)) {
            // Actualizar el archivo de iniciativas
            iniciativaController.guardarIniciativas();
            // Actualizar actividades
            actividades.add(actividad);
            guardarActividadesCrear(actividades);


            MenuVista.muestraMensaje("✅ Actividad creada correctamente en: " + iniciativa.getNombre());

        } else {
            MenuVista.muestraMensaje("❌ No se pudo crear la actividad (¿ya existe?)");
        }
    }

    public void guardarActividades() {
        try {
            // Guardar las iniciativas en el XML
            XMLManagerActividades.guardarActividades(actividades);

            MenuVista.muestraMensaje("✅ Actividades guardadas correctamente.");
        } catch (Exception e) {
            System.err.println("Error al guardar actividades: " + e.getMessage());
            MenuVista.muestraMensaje("❌ Error al guardar las actividades.");
        }
    }

    public void guardarActividadesCrear(ArrayList<Actividad> list) {
        try {
            // Guardar las actividades en el XML
            XMLManagerActividades.guardarActividades(list);
            MenuVista.muestraMensaje("✅ Actividades guardadas correctamente.");
        } catch (Exception e) {
            System.err.println("Error al guardar actividades: " + e.getMessage());
            MenuVista.muestraMensaje("❌ Error al guardar las actividades.");
        }
    }


    /**
     * Método que elimina una actividad de la lista de actividades del usuario actual
     */
    public void eliminaActividad() {
        File archivo = new File("actividades.xml");
        ArrayList<Actividad> actividadesDesdeXML = new ArrayList<>();
        if (archivo.exists()) {
            actividadesDesdeXML = (ArrayList<Actividad>) XMLManagerActividades.obtenerTodasActividades();
        }

        boolean eliminado = false;
        Creador creador = (Creador) usuarioActualController.getUsuario();
        String nombre = Utilidades.pideString("Introduce el nombre de la actividad");

        for (Iniciativa iniciativa : creador.verIniciativas()) {
            for (Actividad actividad : iniciativa.getList()) {
                if (actividad.getNombre().equals(nombre)) {
                    eliminado = actividad.eliminarList(nombre);
                    if (actividad.getNombre().equals(nombre)) {
                        actividadesDesdeXML.remove(actividad);
                        break;
                    }
                }
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
     * Método que modifica una actividad de la lista de actividades del usuario actual
     * borrar borrar borrar
     */
    public void modificaActividad() {
        File archivo = new File("actividades.xml");
        ArrayList<Actividad> actividadesDesdeXML = new ArrayList<>();
        if (archivo.exists()) {
            actividadesDesdeXML = XMLManager.readXML(new ArrayList<>(), "actividades.xml");
        } else {
            XMLManager.writeXML(actividadesDesdeXML, "actividades.xml");
        }
        boolean existe = false;
        boolean actualizado = false;
        Creador creador = (Creador) usuarioActualController.getUsuario();
        ArrayList<Iniciativa> list = creador.verIniciativas();
        Actividad actividad = MenuIniciativaActividad.pideDatosCrearActividad();

        for (Iniciativa iniciativa : list) {
            actualizado = iniciativa.modificar(actividad);
            existe = actividadesDesdeXML.remove(actividad);
            actividadesDesdeXML.add(actividad);
        }

        if (actualizado) {
            MenuVista.muestraMensaje("Se ha podido modificar correctamente.");
            try {
                guardarActividades();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        } else {
            MenuVista.muestraMensaje("No se ha podido modificar no existe la iniciativa.");
        }
    }


    /**
     * Método que muestra las actividades en las que está inscrito un voluntario
     */
    public void verActividades() {
        File archivo = new File("actividades.xml");
        ArrayList<Actividad> actividadesDesdeXML = new ArrayList<>();
        if (archivo.exists()) {
            actividadesDesdeXML = (ArrayList<Actividad>) XMLManagerActividades.obtenerTodasActividades();
        } else {
            MenuVista.muestraMensaje("No existen actividades");
        }

        if (actividadesDesdeXML != null) {
            for (Actividad actividad : actividadesDesdeXML) {
                MenuIniciativaActividad.muestraObjeto(actividad);
            }
        }
    }

    public void mostrarPremios() {
        Voluntario voluntario = (Voluntario) usuarioActualController.getUsuario();
        ArrayList<Premio> listaPremios = voluntario.verPremiosObtenidos();
        for (Premio premio : listaPremios) {
            MenuIniciativaActividad.muestraObjeto(premio);
        }
        MenuVista.muestraEntero(voluntario.getPuntos());

    }

    public void actualizarEstado() {
        Voluntario voluntario = (Voluntario) usuarioActualController.getUsuario();
        String nombre = Utilidades.pideString("Introdce el nombre de la actividad");
        EstadoActividad estadoActividad = EstadoActividad.valueOf(Utilidades.pideString("Introduce el estado de la actividad"));
        String comentario = Utilidades.pideString("Introduce un comentario");
        ArrayList<Actividad> list = (ArrayList<Actividad>) XMLManagerActividades.obtenerTodasActividades();
        for (Actividad actividad : list) {
            if (actividad.getNombre().equals(nombre)) {
                actividad.actualizarEstado(estadoActividad, comentario);
            }
        }
        guardarActividades();

    }

    public void eliminarUsuario() {
        boolean eliminado = false;
        Creador creador = (Creador) usuarioActualController.getUsuario();
        String voluntario = Utilidades.pideString("Introduce el nombre del voluntario");
        String nombreIniciativa = Utilidades.pideString("Introduce el nombre de la iniciativa");
        String nombreActividad = Utilidades.pideString("Introduce el nombre de la actividad");
        ArrayList<Iniciativa> list = creador.getList();
        if (list != null) {
            for (Iniciativa iniciativa : list) {
                if (iniciativa.getNombre().equals(nombreIniciativa)) {
                    ArrayList<Actividad> list1 = iniciativa.getList();
                    if (list1 != null) {
                        for (Actividad actividad : list1) {
                            if (actividad.getNombre().equals(nombreActividad)) {
                                ArrayList<Usuario> list2 = usuarioController.getList();
                                if (list2 != null) {
                                    for (Usuario usuario : list2) {
                                        if (usuario.getUsuario().equals(voluntario)) {
                                            eliminado = actividad.eliminarList(usuario.getNombre());
                                        }
                                    }
                                }
                            }

                        }
                    }
                }

            }
        }
        if (!eliminado) {
            MenuVista.muestraMensaje("No se a podido eliminar el voluntario");
        }
    }

    public void mostrarVoluntariosAsignados() {
        String nombreActividad = Utilidades.pideString("Introduce el nombre de la actividad");
        for (Actividad actividad : actividades) {
            if (actividad.getNombre().equals(nombreActividad)) {
                ArrayList<String> list = actividad.getVoluntario();
                for (String s : list) {
                    MenuVista.muestraMensaje(s);
                }
            }
        }

    }

    public void annadirUsuario() {
        boolean annadirUsuario = false;

        String nombreVoluntario = Utilidades.pideString("Introduce el nombre del voluntario");
        String nombreActividad = Utilidades.pideString("Introduce el nombre de la actividad");
        ArrayList<Usuario> usuarios = usuarioController.getList();
        ArrayList<Actividad> list = actividades;
        if (list != null) {
            for (Actividad actividad : list) {
                if (actividad.getNombre().equals(nombreActividad)) {
                    for (Usuario usuario : usuarios) {
                        if (usuario.getNombre().equals(nombreVoluntario)) {
                            actividad.annadirList(usuario);
                            ArrayList<String> listaVoluntarios = actividad.getVoluntario();
                            listaVoluntarios.add(usuario.getNombre());
                        }
                    }
                }
            }
        }
        guardarActividades();


        if (!annadirUsuario) {
            MenuVista.muestraMensaje("No se a podido añadir el usuario");
        }


    }


}