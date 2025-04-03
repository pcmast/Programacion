package dataAcces;

import model.Actividad;
import model.Iniciativa;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

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

