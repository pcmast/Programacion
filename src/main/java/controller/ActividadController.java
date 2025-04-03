package controller;

import dataAcces.XMLManager;
import model.*;
import utils.Utilidades;
import view.*;

import java.io.File;
import java.util.ArrayList;

public class ActividadController {

    private UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();
    private UsuarioController usuarioController = new UsuarioController();

    /**
     * Metodo que crea una actividad nueva y la añade a la lista de actividades del usuario actual
     */
    public void creaActividad() {
        // 1. Mostrar iniciativas disponibles para seleccionar
        IniciativaController iniciativaController = new IniciativaController();
        iniciativaController.muestraIniciativasNombre();

        // 2. Pedir iniciativa a la que pertenecerá la actividad
        String nombreIniciativa = Utilidades.pideString("Introduce el nombre de la iniciativa:");
        Iniciativa iniciativa = iniciativaController.obtenerIniciativa(nombreIniciativa);

        if (iniciativa == null) {
            MenuVista.muestraMensaje("❌ Iniciativa no encontrada");
            return;
        }

        // 3. Crear la actividad
        Actividad actividad = MenuIniciativaActividad.pideDatosCrearActividad();
        actividad.setIniciativa(iniciativa.getNombre()); // Establecer relación

        // 4. Añadir a la iniciativa
        if (iniciativa.annadirList(actividad)) {
            // Actualizar el archivo de iniciativas
            iniciativaController.guardarIniciativas();

            // Actualizar el archivo de actividades (opcional, si lo necesitas)
            ArrayList<Actividad> todasActividades = new ArrayList<>();
            File archivo = new File("actividades.xml");
            if (archivo.exists()) {
                todasActividades = XMLManager.readXML(new ArrayList<>(), "actividades.xml");
            }
            todasActividades.add(actividad);
            XMLManager.writeXML(todasActividades, "actividades.xml");

            MenuVista.muestraMensaje("✅ Actividad creada correctamente en: " + iniciativa.getNombre());
        } else {
            MenuVista.muestraMensaje("❌ No se pudo crear la actividad (¿ya existe?)");
        }
    }


    /**
     * Método que elimina una actividad de la lista de actividades del usuario actual
     */
    public void eliminaActividad() {
        File archivo = new File("actividades.xml");
        ArrayList<Actividad> actividadesDesdeXML = new ArrayList<>();
        if (archivo.exists()) {
            actividadesDesdeXML = XMLManager.readXML(new ArrayList<>(), "actividades.xml");
        } else {
            XMLManager.writeXML(actividadesDesdeXML, "actividades.xml");
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
                XMLManager.writeXML(actividadesDesdeXML, "actividades.xml");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        } else {
            MenuVista.muestraMensaje("No se ha podido eliminar la actividad.");
        }
    }


    /**
     * Método que modifica una actividad de la lista de actividades del usuario actual
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
                XMLManager.writeXML(actividad, "actividades.xml");
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
            actividadesDesdeXML = XMLManager.readXML(new ArrayList<>(), "actividades.xml");
        } else {
            MenuVista.muestraMensaje("No existen actividades");
        }

        Creador creador = (Creador) usuarioActualController.getUsuario();
        for (Iniciativa iniciativa : creador.verIniciativas()) {
            for (Actividad actividad : iniciativa.obtenerTodos()) {
                MenuVista.muestraMensaje(actividad.getNombre());
            }
        }
    }


    public void actualizarEstado() {
        Voluntario voluntario = (Voluntario) usuarioActualController.getUsuario();
    }

    public void eliminarUsuario(){
        boolean eliminado = false;
        Creador creador= (Creador) usuarioActualController.getUsuario();
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


    public void annadirUsuario() {
        boolean annadirUsuario = false;
        Creador creador = (Creador) usuarioActualController.getUsuario();
        String nombreVoluntario = Utilidades.pideString("Introduce el nombre del voluntario");
        String nombreIniciativa = Utilidades.pideString("Introduce el nombre la iniciativa");
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
                                        if (usuario.getUsuario().equals(nombreVoluntario)) {
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
        if (!annadirUsuario) {
            MenuVista.muestraMensaje("No se a podido añadir el usuario");
        }


    }


}