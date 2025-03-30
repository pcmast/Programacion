package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "Creadores") // Define el nombre del elemento XML raíz para la clase
@XmlAccessorType(XmlAccessType.FIELD) // Especifica que los campos de la clase serán utilizados para el mapeo XML
public class Creador extends Usuario {

    @XmlElement
    private String ongPertenece; // ONG a la que pertenece el creador

    @XmlElement(name = "iniciativa", type = Iniciativa.class) // Lista de iniciativas asociadas a este creador
    private ArrayList<Iniciativa> list = new ArrayList<>();

    public Creador() {

    }

    // Constructor con todos los parámetros para inicializar los datos del creador
    public Creador(String nombre, String usuario, String contrasenna, String correo, String ong) {
        super(nombre, usuario, contrasenna, correo); // Llama al constructor de la clase padre (Usuario)
        this.ongPertenece = ong; // Asigna la ONG a la que pertenece el creador
    }

    /**
     * Métodos getter y setter para obtener y establecer los valores de los atributos
      */
    public String getOngPertenece() {
        return ongPertenece;
    }

    public void setOngPertenece(String ongPertenece) {
        this.ongPertenece = ongPertenece;
    }

    public ArrayList<Iniciativa> getList() {
        return list;
    }

    public void setList(ArrayList<Iniciativa> list) {
        this.list = list;
    }

    /**
     * Crea una iniciativa y si la lista de este creador no contiene esa iniciativa, la añade
     * @param iniciativa la iniciativa que va a crear
     * @return devuelve true si la pudo añadir, false si ya existe en la lista
     */
    public boolean crearIniciativa(Iniciativa iniciativa){
        boolean creada = false;
        if (!list.contains(iniciativa)) { // Verifica si la iniciativa no está en la lista
            list.add(iniciativa); // Añade la nueva iniciativa
            creada = true; // Indica que la iniciativa fue creada
        }
        return creada;
    }

    /**
     * Elimina una iniciativa por su nombre y nombre de creador
     * @param nombre el nombre de la iniciativa
     * @param nombreCreador nombre del creador de esa iniciativa
     * @return devuelve true si se ha podido eliminar, false si no se pudo encontrar
     */
    public boolean eliminarIniciativa(String nombre, String nombreCreador){
        boolean eliminada = false;
        // Recorre la lista de iniciativas
        for (Iniciativa iniciativa : list) {
            // Si encuentra la iniciativa con el nombre y creador correctos, la elimina
            if (iniciativa.getNombre().equals(nombre) && iniciativa.getCreadorIniciativa().equals(nombreCreador)) {
                list.remove(iniciativa); // Elimina la iniciativa de la lista
                eliminada = true; // Indica que la iniciativa fue eliminada
            }
        }
        return eliminada;
    }

    /**
     * El creador crea una actividad y la añade a una iniciativa específica
     * @param actividad la actividad que va a crear
     * @param nombreIniciativa nombre de la iniciativa donde se añadirá la actividad
     * @return devuelve true si la actividad fue añadida correctamente, false si no se pudo añadir
     */
    public boolean crearActividad(Actividad actividad, String nombreIniciativa) {
        boolean creada = false;
        // Busca la iniciativa correspondiente por nombre
        for (Iniciativa iniciativa : list) {
            if (iniciativa.getNombre().equals(nombreIniciativa)) {
                iniciativa.annadirList(actividad); // Añade la actividad a la lista de actividades de la iniciativa
                creada = true; // Indica que la actividad fue creada
            }
        }
        return creada;
    }

    /**
     * El creador elimina una actividad por su nombre y nombre de la iniciativa a la que pertenece
     * @param nombreActividad nombre de la actividad que quiere eliminar
     * @param nombreIniciativa nombre de la iniciativa que tiene esa actividad
     * @return devuelve true si la actividad fue eliminada, false si no se pudo encontrar
     */
    public boolean eliminarActividad(String nombreActividad, String nombreIniciativa) {
        boolean eliminada = false;
        for (Iniciativa iniciativa : list) {
            if (iniciativa.getNombre().equals(nombreIniciativa)) {
                ArrayList<Actividad> lista1 = iniciativa.getList();
                for (Actividad actividad : lista1) {
                    if (actividad.getNombre().equals(nombreActividad)) {
                        lista1.remove(actividad);
                        eliminada = true;
                    }
                }
            }
        }
        return eliminada;
    }

    /**
     * Devuelve una copia de la lista de iniciativas del creador
     * @return una lista de las iniciativas del creador
     */
    public ArrayList<Iniciativa> verIniciativas() {
        return getList(); // Retorna la lista de iniciativas
    }
}
