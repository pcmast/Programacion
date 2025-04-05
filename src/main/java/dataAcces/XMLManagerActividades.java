package dataAcces;

import model.Actividad;
import utils.ActividadesContenedor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLManagerActividades {

    /** Nombre del archivo XML donde se almacenan las actividades */
    private static final String FILE_NAME = "actividades.xml";

    /** Caché de actividades cargadas desde el XML */
    private static ArrayList<Actividad> cacheActividades;

    /**
     * Obtiene la lista de actividades, cargándolas del XML si es necesario.
     * @return Lista de actividades cargadas.
     */
    private static ArrayList<Actividad> obtenerActividades() {
        if (cacheActividades == null) {
            cacheActividades = cargarDesdeXML();
        }
        return cacheActividades;
    }

    /**
     * Guarda las actividades en el archivo XML.
     * @param actividades Lista de actividades a guardar.
     */
    public static void guardarActividades(List<Actividad> actividades) {
        guardarEnXML((ArrayList<Actividad>) actividades);
    }

    /**
     * Agrega una nueva actividad a la lista y la guarda en el archivo XML.
     * @param actividad Actividad a agregar.
     */
    public static void agregarActividad(Actividad actividad) {
        obtenerActividades().add(actividad);
        guardarActividades(obtenerActividades());
    }

    /**
     * Elimina una actividad de la lista y guarda la lista actualizada en el archivo XML.
     * @param actividad Actividad a eliminar.
     */
    public static void eliminarActividad(Actividad actividad) {
        ArrayList<Actividad> lista = obtenerActividades();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).equals(actividad)) {
                lista.remove(i);
                break;
            }
        }
        guardarActividades(obtenerActividades());
    }

    /**
     * Devuelve todas las actividades almacenadas en el archivo XML.
     * @return Lista de actividades.
     */
    public static List<Actividad> obtenerTodasActividades() {
        List<Actividad> list = obtenerActividades();
        return list;
    }

    /**
     * Carga las actividades desde el archivo XML.
     * @return Lista de actividades cargadas.
     */
    private static ArrayList<Actividad> cargarDesdeXML() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return new ArrayList<>(); // Si el archivo no existe, devolver lista vacía

            JAXBContext context = JAXBContext.newInstance(ActividadesContenedor.class, Actividad.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ActividadesContenedor contenedor = (ActividadesContenedor) unmarshaller.unmarshal(file);
            System.out.println("Número de actividades cargadas: " + contenedor.getActividades().size());
            return contenedor.getActividades();
        } catch (JAXBException e) {
            System.err.println("Error al cargar actividades: " + e.getMessage());
            return new ArrayList<>(); // En caso de error, devolver lista vacía
        }
    }

    /**
     * Guarda las actividades en un archivo XML.
     * @param actividades Lista de actividades a guardar.
     */
    private static void guardarEnXML(ArrayList<Actividad> actividades) {
        try {
            ActividadesContenedor contenedor = new ActividadesContenedor();
            contenedor.setActividades(actividades);

            JAXBContext context = JAXBContext.newInstance(ActividadesContenedor.class, Actividad.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            File archivoTemp = new File(FILE_NAME + ".tmp");
            File archivoFinal = new File(FILE_NAME);

            marshaller.marshal(contenedor, archivoTemp);

            if (archivoFinal.exists()) archivoFinal.delete();
            if (!archivoTemp.renameTo(archivoFinal)) {
                throw new JAXBException("No se pudo renombrar archivo temporal");
            }
        } catch (JAXBException e) {
            System.err.println("Error crítico al guardar: " + e.getMessage());
            throw new RuntimeException("Error al guardar actividades", e);
        }
    }
}
