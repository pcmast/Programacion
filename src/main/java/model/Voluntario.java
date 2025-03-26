package model;

import java.util.ArrayList;
import java.util.List;

public class Voluntario extends Usuario{
    private int puntos;
    private ArrayList<Actividad> list = new ArrayList<>();
    private ArrayList<Premio> premiosObtenidos = new ArrayList<>();

    ///Constructor full equip
    public Voluntario(String nombre, String usuario, String contrasenna, String correo) {
        super(nombre, usuario, contrasenna, correo);
    }

    public void verificarPremios() {
        ArrayList<Premio> premiosDisponibles = new ArrayList<>();
        premiosDisponibles.add(new Premio(">> Palmadita en la espalda", 500));
        premiosDisponibles.add(new Premio(">> Palmadita de regalo", 1000));
        premiosDisponibles.add(new Premio(">> Rango Superior", 2000));

        for (Premio premio : premiosDisponibles) {
            if (puntos >= premio.getPuntosRequeridos() && !premiosObtenidos.contains(premio)) {
                premiosObtenidos.add(premio);
                System.out.println("¡Felicidades! Has obtenido el premio: " + premio.getNombre());
            }
        }
    }

    /// Revisa si hay premios nuevos al actualizar los puntos
    public void setPuntos(int puntos) {
        this.puntos = puntos;
        verificarPremios();
    }

    public int getPuntos() {
        return puntos;
    }

    public ArrayList<Actividad> getList() {
        return list;
    }

    public void setList(ArrayList<Actividad> list) {
        this.list = list;
    }

    public void otorgarPuntos() {
        this.puntos += 100;
        verificarPremios(); // Cada vez que se suman puntos, se revisa si hay nuevos premios
    }


    public ArrayList<Actividad> verActividades(){
        return getList();
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
                actividad.setEstado(EstadoActividad.COMPLETADA);
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

    public ArrayList<Premio> verPremiosObtenidos() {
        return premiosObtenidos;
    }


}