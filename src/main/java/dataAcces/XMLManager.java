package dataAcces;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLManager {
    public static <T> boolean writeXML(T objeto, String fileName) {
        boolean result = false;
        try {
            JAXBContext context = JAXBContext.newInstance(objeto.getClass());

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(objeto, new File(fileName));
            result = true;

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static <T> T readXML(T objeto, String fileName) {
        T result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(objeto.getClass());

            Unmarshaller unmarshaller = context.createUnmarshaller();
            result = (T) unmarshaller.unmarshal(new File(fileName));
            //Se hace un casteo, porque todas las clases herendan de objet.
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
