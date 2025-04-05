package dataAcces;

import model.Actividad;
import model.Iniciativa;
import utils.IniciativasContenedor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona las operaciones de acceso a datos relacionadas con las iniciativas y actividades en formato XML.
 */
public class XMLManagerIniciativas {
    private static final String FILE_NAME = "iniciativas.xml"; // Nombre del archivo XML donde se almacenan las iniciativas
    private static ArrayList<Iniciativa> cacheIniciativas; // Caché de iniciativas cargadas desde el XML

    /**
     * Obtiene la lista de iniciativas, cargándolas desde el XML si es necesario.
     *
     * @return una lista de iniciativas.
     */
    private static ArrayList<Iniciativa> obtenerIniciativas() {
        if (cacheIniciativas == null) {
            cacheIniciativas = cargarDesdeXML();
        }
        return cacheIniciativas;
    }

    /**
     * Guarda una lista de iniciativas en el archivo XML.
     *
     * @param iniciativas la lista de iniciativas a guardar.
     */
    public static void guardarIniciativas(List<Iniciativa> iniciativas) {
        guardarEnXML(new ArrayList<>(iniciativas));
    }

    /**
     * Agrega una nueva iniciativa a la lista y guarda los cambios en el XML.
     *
     * @param iniciativa la iniciativa a agregar.
     */
    public static void agregarIniciativa(Iniciativa iniciativa) {
        obtenerIniciativas().add(iniciativa);
        guardarIniciativas(obtenerIniciativas());
    }

    /**
     * Elimina una iniciativa de la lista y guarda los cambios en el XML.
     *
     * @param iniciativa la iniciativa a eliminar.
     */
    public static void eliminarIniciativa(Iniciativa iniciativa) {
        ArrayList<Iniciativa> lista = obtenerIniciativas();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).equals(iniciativa)) {
                lista.remove(i);
                break;
            }
        }
        guardarIniciativas(obtenerIniciativas());
    }

    /**
     * Obtiene todas las iniciativas disponibles.
     *
     * @return una lista de todas las iniciativas.
     */
    public static List<Iniciativa> obtenerTodasIniciativas() {
        return new ArrayList<Iniciativa>(obtenerIniciativas());
    }

    /**
     * Agrega una actividad a una iniciativa específica y guarda los cambios.
     *
     * @param iniciativa la iniciativa donde se agregará la actividad.
     * @param actividad  la actividad a agregar.
     */
    public static void agregarActividad(Iniciativa iniciativa, Actividad actividad) {
        ArrayList<Iniciativa> lista = obtenerIniciativas();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).equals(iniciativa)) {
                if (!lista.get(i).getList().contains(actividad)) {
                    lista.get(i).getList().add(actividad);
                    guardarIniciativas(obtenerIniciativas());
                }
                break;
            }
        }
    }

    /**
     * Elimina una actividad de una iniciativa específica y guarda los cambios.
     *
     * @param iniciativa la iniciativa de la cual se eliminará la actividad.
     * @param actividad  la actividad a eliminar.
     */
    public static void eliminarActividad(Iniciativa iniciativa, Actividad actividad) {
        ArrayList<Iniciativa> lista = obtenerIniciativas();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).equals(iniciativa)) {
                ArrayList<Actividad> actividades = lista.get(i).getList();
                for (int j = 0; j < actividades.size(); j++) {
                    if (actividades.get(j).equals(actividad)) {
                        actividades.remove(j);
                        guardarIniciativas(obtenerIniciativas());
                        return;
                    }
                }
                break;
            }
        }
    }

    /**
     * Obtiene todas las actividades asociadas a una iniciativa.
     *
     * @param iniciativa la iniciativa de la cual obtener las actividades.
     * @return una lista de actividades asociadas a la iniciativa.
     */
    public static List<Actividad> obtenerActividades(Iniciativa iniciativa) {
        ArrayList<Iniciativa> lista = obtenerIniciativas();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).equals(iniciativa)) {
                return new ArrayList<Actividad>(lista.get(i).getList());
            }
        }
        return new ArrayList<Actividad>();
    }

    /**
     * Carga las iniciativas desde el archivo XML.
     *
     * @return una lista de iniciativas cargadas desde el archivo XML.
     */
    private static ArrayList<Iniciativa> cargarDesdeXML() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return new ArrayList<Iniciativa>();

            JAXBContext context = JAXBContext.newInstance(
                    IniciativasContenedor.class,
                    Iniciativa.class,
                    Actividad.class
            );

            Unmarshaller unmarshaller = context.createUnmarshaller();
            IniciativasContenedor contenedor = (IniciativasContenedor) unmarshaller.unmarshal(file);
            return contenedor.getIniciativas();

        } catch (JAXBException e) {
            System.err.println("Error al cargar iniciativas: " + e.getMessage());
            return new ArrayList<Iniciativa>();
        }
    }

    /**
     * Guarda las iniciativas en el archivo XML.
     *
     * @param iniciativas la lista de iniciativas a guardar.
     */
    private static void guardarEnXML(ArrayList<Iniciativa> iniciativas) {
        try {
            IniciativasContenedor contenedor = new IniciativasContenedor();
            contenedor.setIniciativas(iniciativas);

            JAXBContext context = JAXBContext.newInstance(
                    IniciativasContenedor.class,
                    Iniciativa.class,
                    Actividad.class
            );

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
            throw new RuntimeException("Error al guardar iniciativas", e);
        }
    }
}
