package model;

import interfaces.CRUDGenerico;

import java.util.ArrayList;
import java.util.List;

public class Iniciativa implements CRUDGenerico {

    private String nombre;
    private String descripcion;
    private String creadorIniciativa;
    private ArrayList<Actividad> list = new ArrayList<>();

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

    public boolean annadirList(Object o) {
        boolean annadido = false;
        if (!list.contains(o)){
            list.add((Actividad) o);
            annadido = true;
        }

        return annadido;
    }

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


    public boolean modificar(Object o) {
        return false;
    }


    public List obtenerTodos() {
        return getList();
    }
}