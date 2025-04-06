package utils;

import model.Actividad;

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

    /**
     * Constructor por defecto que inicializa la lista de actividades.
     */
    public ActividadesContenedor() {
        this.actividades = new ArrayList<>();
    }

    // Getters y setters

    /**
     * Obtiene la lista de actividades.
     *
     * @return La lista de actividades.
     */
    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    /**
     * Establece la lista de actividades.
     *
     * @param actividades La lista de actividades a establecer.
     */
    public void setActividades(ArrayList<Actividad> actividades) {
        this.actividades = actividades;
    }
}
