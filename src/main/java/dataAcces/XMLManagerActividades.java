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
    private static final String FILE_NAME = "actividades.xml";
    private static ArrayList<Actividad> cacheActividades;

    private static ArrayList<Actividad> obtenerActividades() {
        if (cacheActividades == null) {
            cacheActividades = cargarDesdeXML();
        }
        return cacheActividades;
    }

    public static void guardarActividades(List<Actividad> actividades) {
        guardarEnXML((ArrayList<Actividad>) actividades);
    }

    public static void agregarActividad(Actividad actividad) {
        obtenerActividades().add(actividad);
        guardarActividades(obtenerActividades());
    }

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

    public static List<Actividad> obtenerTodasActividades() {
        return new ArrayList<>(obtenerActividades());
    }

    private static ArrayList<Actividad> cargarDesdeXML() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return new ArrayList<>();

            JAXBContext context = JAXBContext.newInstance(ActividadesContenedor.class, Actividad.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ActividadesContenedor contenedor = (ActividadesContenedor) unmarshaller.unmarshal(file);
            System.out.println("Número de actividades cargadas: " + contenedor.getActividades().size());
            return contenedor.getActividades();
        } catch (JAXBException e) {
            System.err.println("Error al cargar actividades: " + e.getMessage());
            return new ArrayList<>();
        }
    }

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