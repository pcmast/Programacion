package model;

import interfaces.CRUDGenerico;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "Iniciativas")
@XmlAccessorType(XmlAccessType.FIELD)
public class Iniciativa implements CRUDGenerico {
    @XmlElement
    private String nombre;
    @XmlElement
    private String descripcion;
    @XmlElement
    private String creadorIniciativa;
    @XmlElement(name = "iniciativa", type = Iniciativa.class)
    private ArrayList<Actividad> list = new ArrayList<>();

    public Iniciativa() {}

    //Constructor full equip
    public Iniciativa(String nombre, String descripcion, String creadorIniciativa) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creadorIniciativa = creadorIniciativa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCreadorIniciativa() {
        return creadorIniciativa;
    }

    public void setCreadorIniciativa(String creadorIniciativa) {
        this.creadorIniciativa = creadorIniciativa;
    }

    public ArrayList<Actividad> getList() {
        return list;
    }

    public void setList(ArrayList<Actividad> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Iniciativa that = (Iniciativa) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(creadorIniciativa, that.creadorIniciativa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, creadorIniciativa);
    }

    /**
     * Metodo que añade un objeto si no lo contiene a una lista
     * @param o el objeto que se va a añadir
     * @return devuelve true si se pudo añadir o false si no pudo
     */
    public boolean annadirList(Object o) {
        boolean annadido = false;
        if (!list.contains(o)){
            list.add((Actividad) o);
            annadido = true;
        }

        return annadido;
    }

    /**
     * Metodo que elimina un objeto por su nombre
     * @param nombre El nombre de ese objeto
     * @return devuelve true si se pudo eliminar y false si no pudo
     */
    public boolean eliminarList(String nombre) {
        boolean eliminado = false;

        for (Actividad actividad:list){
            if (actividad.getNombre().equals(nombre)){
                list.remove(actividad);
                eliminado = true;
            }
        }
        return eliminado;
    }

    /**
     * Metodo que modifica un objeto pasado como parametro elimina el objeto anterior añadiendo el nuevo
     * @param o objeto que se modificara
     * @return devuelve true si se pudo modificar y false si no pudo
     */
    public boolean modificar(Object o) {
        boolean actualizado = false;

        for (Actividad actividad:list){
            if (actividad.equals(o)){
                list.remove(actividad);
                list.add((Actividad) o);
                actualizado = true;
            }

        }
        return actualizado;
    }

    /**
     * Metodo que devuelve una copia de la lista de actividades
     * @return devuelve la copia de la lista
     */
    public ArrayList<Actividad> obtenerTodos() {
        return getList();
    }




}