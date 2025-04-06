package utils;

import model.Iniciativa;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "iniciativas")
@XmlAccessorType(XmlAccessType.FIELD)
public class IniciativasContenedor {
    @XmlElement(name = "iniciativa")
    private ArrayList<Iniciativa> iniciativas;

    /**
     * Constructor por defecto que inicializa la lista de iniciativas.
     */
    public IniciativasContenedor() {
        this.iniciativas = new ArrayList<>();
    }

    /**
     * Obtiene la lista de iniciativas.
     *
     * @return La lista de iniciativas.
     */
    public ArrayList<Iniciativa> getIniciativas() {
        return iniciativas;
    }

    /**
     * Establece la lista de iniciativas.
     *
     * @param iniciativas La lista de iniciativas a establecer.
     */
    public void setIniciativas(ArrayList<Iniciativa> iniciativas) {
        this.iniciativas = iniciativas;
    }
}
