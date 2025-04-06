package model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Voluntario")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Voluntario")
public class Voluntario extends Usuario{
    @XmlElement
    private int puntos;
    @XmlElement(name = "actividad", type = Actividad.class)
    private ArrayList<Actividad> list = new ArrayList<>();
    @XmlElement(name = "premio", type = Premio.class)
    private ArrayList<Premio> premiosObtenidos = new ArrayList<>();

    /**
     * Constructor vacío
     */
    public Voluntario() {}

    /**
     * Constructor con parámetros
     * @param nombre Nombre del voluntario
     * @param usuario Nombre de usuario
     * @param contrasenna Contraseña del voluntario
     * @param correo Correo electrónico del voluntario
     */
    public Voluntario(String nombre, String usuario, String contrasenna, String correo) {
        super(nombre, usuario, contrasenna, correo, "voluntario");
    }

    /**
     * Verifica si el voluntario ha alcanzado los puntos necesarios para obtener un premio
     * y lo añade a la lista de premios obtenidos si corresponde.
     */
    public void verificarPremios() {
        ArrayList<Premio> premiosDisponibles = new ArrayList<>();
        premiosDisponibles.add(new Premio(">> Palmadita en la espalda", 200));
        premiosDisponibles.add(new Premio(">> Palmadita de regalo", 1000));
        premiosDisponibles.add(new Premio(">> Rango Superior", 2000));

        for (Premio premio : premiosDisponibles) {
            if (puntos >= premio.getPuntosRequeridos() && !premiosObtenidos.contains(premio)) {
                premiosObtenidos.add(premio);
                System.out.println("¡Felicidades! Has obtenido el premio: " + premio.getNombre());
            }
        }
    }

    /**
     * Asigna los puntos al voluntario y verifica si ha ganado premios
     * @param puntos Puntos a asignar
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
        verificarPremios();
    }

    /**
     * Obtiene la cantidad de puntos del voluntario
     * @return Cantidad de puntos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Obtiene la lista de actividades del voluntario
     * @return Lista de actividades
     */
    public ArrayList<Actividad> getList() {
        return list;
    }

    /**
     * Asigna una lista de actividades al voluntario
     * @param list Lista de actividades
     */
    public void setList(ArrayList<Actividad> list) {
        this.list = list;
    }

    /**
     * Otorga puntos al voluntario por completar actividades
     */
    public void otorgarPuntos() {
        this.puntos += 100;
        verificarPremios(); // Cada vez que se suman puntos, se revisa si hay nuevos premios
    }

    /**
     * Devuelve la lista de actividades del voluntario
     * @return Lista de actividades
     */
    public ArrayList<Actividad> verActividades(){
        return getList();
    }

    /**
     * Permite al voluntario unirse a una actividad si no está en su lista
     * @param actividad Actividad a la que se desea unir
     * @return true si se unió correctamente, false si ya estaba en la lista
     */
    public boolean unirseActividad(Actividad actividad){
        boolean unido = false;

        if (!list.contains(actividad)){
            list.add(actividad);
            unido = true;
        }
        return unido;
    }

    /**
     * Marca una actividad como completada si coincide con el nombre proporcionado,
     * otorgando puntos al voluntario.
     * @param nombre Nombre de la actividad a completar
     * @return true si la actividad fue completada, false si no se encontró
     */
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

    /**
     * Obtiene la lista de iniciativas creadas por un creador
     * @param creador Creador del que se desean ver iniciativas
     * @return Lista de iniciativas creadas
     */
    public ArrayList<Iniciativa> verIniciativas(Creador creador){
        return creador.verIniciativas();
    }

    /**
     * Obtiene la lista de actividades disponibles en una iniciativa
     * @param iniciativa Iniciativa de la que se desean ver actividades
     * @return Lista de actividades disponibles
     */
    public ArrayList<Actividad> verActividadesDisponibles(Iniciativa iniciativa){
        return iniciativa.obtenerTodos();
    }

    /**
     * Obtiene la lista de premios obtenidos por el voluntario
     * @return Lista de premios obtenidos
     */
    public ArrayList<Premio> verPremiosObtenidos() {
        return premiosObtenidos;
    }

    @Override
    public String toString() {
        //return super.getNombre();
        return "Voluntario: " + super.getNombre() + " (Puntos: " + puntos + ")";
    }
}
