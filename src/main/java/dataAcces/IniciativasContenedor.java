package dataAcces;

import model.Iniciativa;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "iniciativas")
@XmlAccessorType(XmlAccessType.FIELD)
public class IniciativasContenedor {
    @XmlElement(name = "iniciativa")
    private ArrayList<Iniciativa> iniciativas;

    public IniciativasContenedor() {
        this.iniciativas = new ArrayList<>();
    }

    // Getters y setters
    public ArrayList<Iniciativa> getIniciativas() {
        return iniciativas;
    }

    public void setIniciativas(ArrayList<Iniciativa> iniciativas) {
        this.iniciativas = iniciativas;
    }
}