package model;

import java.util.ArrayList;
import java.util.List;

public class Voluntario extends Usuario{
    private int puntos;
    private ArrayList<Actividad> list = new ArrayList<>();
    private ArrayList<Actividad> listCompletada = new ArrayList<>();
    //Constructor full equip
    public Voluntario(String nombre, String usuario, String contrasenna, String correo) {
        super(nombre, usuario, contrasenna, correo);
    }
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    public int getPuntos() {
        return puntos;
    }

    public ArrayList<Actividad> getListCompletada() {
        return listCompletada;
    }

    public void setListCompletada(ArrayList<Actividad> listCompletada) {
        this.listCompletada = listCompletada;
    }

    public ArrayList<Actividad> getList() {
        return list;
    }

    public void setList(ArrayList<Actividad> list) {
        this.list = list;
    }

    public void otorgarPuntos(){
        this.puntos = this.puntos + 100;
    }

    public ArrayList<Actividad> verActividades(){
        return getList();
    }

    public ArrayList<Actividad> verActividadesCompletadas(){
        return getListCompletada();
    }

    public boolean unirseActividad(Actividad actividad){
        boolean unido = false;

        if (!list.contains(actividad)){
            list.add(actividad);
            unido = true;
        }
    return unido;
    }
    public boolean actividadCompletada(String nombre){
        boolean completada = false;
        for (Actividad actividad: list){
            if (actividad.getNombre().equals(nombre)){
                list.remove(actividad);
                listCompletada.add(actividad);
                completada = true;
                otorgarPuntos();
            }
        }
        return completada;
    }

    public ArrayList<Iniciativa> verIniciativas(Creador creador){
        return creador.getList();
    }



    public ArrayList<Actividad> verActividadesDisponibles(Iniciativa iniciativa){
        return iniciativa.getList();
    }




}