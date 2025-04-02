package dataAcces;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLManagerIniciativas {
    // Método para escribir listas directamente
    public static <T> boolean writeXML(List<T> list, String filename) {
        try {
            JAXBContext context = JAXBContext.newInstance(new Class[]{ArrayList.class, list.get(0).getClass()});
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Crear elemento wrapper temporal
            JAXBElement<List<T>> element = new JAXBElement<>(
                    new QName(filename.replace(".xml", "")),
                    (Class<List<T>>) list.getClass(),
                    list
            );

            marshaller.marshal(element, new File(filename));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para leer listas directamente
    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> readXML(ArrayList<T> defaultValue, String filename) {
        try {
            if (defaultValue.isEmpty()) {
                throw new RuntimeException("No se puede determinar el tipo de la lista");
            }

            JAXBContext context = JAXBContext.newInstance(new Class[]{ArrayList.class, defaultValue.get(0).getClass()});
            Unmarshaller unmarshaller = context.createUnmarshaller();

            File file = new File(filename);
            if (!file.exists()) {
                return new ArrayList<>();
            }

            JAXBElement<ArrayList<T>> element = (JAXBElement<ArrayList<T>>) unmarshaller.unmarshal(file);
            return element.getValue();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}