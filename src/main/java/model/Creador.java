package model;

import interfaces.InterfazUsuario;

import java.util.ArrayList;

public class Creador extends Usuario {
    private String ongPertenece;
    private ArrayList<Iniciativa> list = new ArrayList<>();

    public Creador(String nombre, String usuario, String contrasenna, String correo,String ongPertenece) {
        super(nombre, usuario, contrasenna, correo);
        this.ongPertenece = ongPertenece;
    }

    /**
     * Crea una iniciativa y si la lista de este creador no contiene esa iniciativa la añade
     * @param iniciativa la iniciativa que va a crear
     * @return devuelve true si la pudo añadir y false si no pudo
     */
    public boolean crearIniciativa(Iniciativa iniciativa){
        boolean creada = false;
        if (!list.contains(iniciativa)){
            list.add(iniciativa);
            creada = true;
        }
    return creada;
    }

    /**
     * Elimina una iniciativa por su nombre y nombre de creador
     * @param nombre el nombre de la iniciativa
     * @param nombreCreador nombre del creador de esa iniciativa
     * @return devuelve true si se a podido eliminar y false si no se pudo
     */
    public boolean eliminarIniciativa(String nombre, String nombreCreador){
        boolean eliminada = false;
        for (Iniciativa iniciativa1:list){
            if (iniciativa1.getNombre().equals(nombre) && iniciativa1.getCreadorIniciativa().equals(nombreCreador)){
                list.remove(iniciativa1);
                eliminada = true;
            }
        }
        return eliminada;
    }

    /**
     * El creador crea una actividad y la añade a una iniciativa que quiera
     * @param actividad La actividad que va a crear
     * @param nombreIniciativa nombre de la iniciativa donde va a estar esa actividad
     * @return devuelve true si la a creado y false si no ha podido
     */
    public boolean crearActividad(Actividad actividad,String nombreIniciativa){
        boolean creada = false;
        for (Iniciativa iniciativa:list){
            if (iniciativa.getNombre().equals(nombreIniciativa)){
                iniciativa.annadirList(actividad);
                creada = true;
            }
        }
        return creada;
    }

    /**
     * El creador elimina una actividad por su nombre y nombre dela iniciativa
     * @param nombreActividad nombre de la actividad que quiere eliminar
     * @param nombreInicativa nombre de la iniciativa que tiene esa actividad
     * @return devuelve true si la pudo eliminar y false si no pudo
     */
    public boolean eliminarActividad(String nombreActividad, String nombreInicativa){
        boolean eliminada = false;
        for (Iniciativa iniciativa:list){
        if (iniciativa.getNombre().equals(nombreInicativa)){
            ArrayList<Actividad> lista1 = iniciativa.getList();
            for (Actividad actividad:lista1){
                if (actividad.getNombre().equals(nombreActividad)){
                    lista1.remove(actividad);
                    eliminada = true;
                }
            }//POSIBLES MEJORAS
        }
        }
        return eliminada;
    }




}