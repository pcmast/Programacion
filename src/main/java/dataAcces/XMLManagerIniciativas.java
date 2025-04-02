package dataAcces;

import model.Iniciativa;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;

public class XMLManagerIniciativas {
    private static final String FILE_NAME = "iniciativas.xml";

    public static void guardarIniciativas(ArrayList<Iniciativa> iniciativas) {
        try {
            IniciativasContenedor contenedor = new IniciativasContenedor();
            contenedor.setIniciativas(iniciativas);

            JAXBContext context = JAXBContext.newInstance(IniciativasContenedor.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(contenedor, new File(FILE_NAME));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Iniciativa> cargarIniciativas() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                return new ArrayList<>();
            }

            JAXBContext context = JAXBContext.newInstance(IniciativasContenedor.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            IniciativasContenedor contenedor = (IniciativasContenedor) unmarshaller.unmarshal(file);
            return contenedor.getIniciativas();
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}