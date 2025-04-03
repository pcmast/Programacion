package dataAcces;

import model.Actividad;
import model.Iniciativa;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLManagerIniciativas {
    private static final String FILE_NAME = "iniciativas.xml";
    private static ArrayList<Iniciativa> cacheIniciativas;

    private static ArrayList<Iniciativa> obtenerIniciativas() {
        if (cacheIniciativas == null) {
            cacheIniciativas = cargarDesdeXML();
        }
        return cacheIniciativas;
    }

    public static void guardarIniciativas(List<Iniciativa> iniciativas) {
        guardarEnXML(new ArrayList<>(iniciativas));
    }

    public static void agregarIniciativa(Iniciativa iniciativa) {
        obtenerIniciativas().add(iniciativa);
        guardarIniciativas(obtenerIniciativas());
    }

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

    public static List<Iniciativa> obtenerTodasIniciativas() {
        return new ArrayList<Iniciativa>(obtenerIniciativas());
    }

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

    public static List<Actividad> obtenerActividades(Iniciativa iniciativa) {
        ArrayList<Iniciativa> lista = obtenerIniciativas();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).equals(iniciativa)) {
                return new ArrayList<Actividad>(lista.get(i).getList());
            }
        }
        return new ArrayList<Actividad>();
    }

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
