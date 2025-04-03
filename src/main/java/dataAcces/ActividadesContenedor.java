package dataAcces;

import model.Actividad;
import model.Iniciativa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
@XmlRootElement(name = "actividades")
@XmlAccessorType(XmlAccessType.FIELD)
public class ActividadesContenedor {
    @XmlElement(name = "actividad")
    private ArrayList<Actividad> actividades;

    public ActividadesContenedor() {
        this.actividades = new ArrayList<>();
    }

    // Getters y setters
    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(ArrayList<Actividad> actividades) {
        this.actividades = actividades;
    }
}

