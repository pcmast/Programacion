package model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "Creador")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Creador")
public class Creador extends Usuario {

    @XmlElement
    private String ongPertenece; // ONG a la que pertenece el creador

    @XmlElement(name = "iniciativa", type = Iniciativa.class) // Lista de iniciativas asociadas a este creador
    private ArrayList<Iniciativa> list = new ArrayList<>();

    public Creador() {

    }

    // Constructor con todos los parámetros para inicializar los datos del creador
    public Creador(String nombre, String usuario, String contrasenna, String correo, String ong) {
        super(nombre, usuario, contrasenna, correo,"creador"); // Llama al constructor de la clase padre (Usuario)
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
     *
     * @param iniciativa la iniciativa que va a crear
     * @return devuelve true si la pudo añadir, false si ya existe en la lista
     */
    public boolean crearIniciativa(Iniciativa iniciativa) {
        boolean creada = false;
        if (!list.contains(iniciativa)) { // Verifica si la iniciativa no está en la lista
            list.add(iniciativa); // Añade la nueva iniciativa
            creada = true; // Indica que la iniciativa fue creada
        }
        return creada;
    }

    /**
     * Elimina una iniciativa por su nombre y nombre de creador
     *
     * @param nombre        el nombre de la iniciativa
     * @param nombreCreador nombre del creador de esa iniciativa
     * @return devuelve true si se ha podido eliminar, false si no se pudo encontrar
     */
    public boolean eliminarIniciativa(String nombre, String nombreCreador) {
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
     *
     * @param actividad        la actividad que va a crear
     * @param nombreIniciativa nombre de la iniciativa donde se añadirá la actividad
     * @return devuelve true si la actividad fue añadida correctamente, false si no se pudo añadir
     */
    /**
     * Crea una actividad y la añade a la iniciativa especificada
     * @param actividad La actividad a crear
     * @param nombreIniciativa Nombre de la iniciativa destino
     * @return true si se añadió correctamente, false si no
     */
    public boolean crearActividad(Actividad actividad, String nombreIniciativa) {
        boolean resultado = false;

        if (actividad != null && nombreIniciativa != null && !nombreIniciativa.isBlank()) {
            for (Iniciativa iniciativa : list) {
                if (iniciativa.getNombre().equalsIgnoreCase(nombreIniciativa)) {
                    boolean existeDuplicado = false;

                    // Verificar duplicados
                    for (Actividad actExistente : iniciativa.getList()) {
                        if (actExistente.getNombre().equalsIgnoreCase(actividad.getNombre())) {
                            existeDuplicado = true;
                            break;
                        }
                    }

                    // Si no es duplicado, añadir
                    if (!existeDuplicado) {
                        if (iniciativa.annadirList(actividad)) {
                            actividad.setIniciativa(nombreIniciativa);
                            resultado = true;
                        }
                    }
                    break; // Salir del for al encontrar la iniciativa
                }
            }
        }

        return resultado;
    }

    /**
     * El creador elimina una actividad por su nombre y nombre de la iniciativa a la que pertenece
     *
     * @param nombreActividad  nombre de la actividad que quiere eliminar
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
     *
     * @return una lista de las iniciativas del creador
     */
    public ArrayList<Iniciativa> verIniciativas() {
        return getList();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("══════════════════════════════════════════\n");
        sb.append("           🧑💼 PERFIL DE CREADOR       \n");
        sb.append("══════════════════════════════════════════\n");
        sb.append("👤 Nombre: ").append(getNombre() != null ? getNombre() : "N/A").append("\n");
        sb.append("📧 Correo: ").append(getCorreo() != null ? getCorreo() : "N/A").append("\n");
        sb.append("🏛️ ONG: ").append(ongPertenece != null ? ongPertenece : "N/A").append("\n");
        sb.append("══════════════════════════════════════════\n");
        sb.append("🌱 INICIATIVAS (").append(list.size()).append(")\n");
        sb.append("══════════════════════════════════════════\n");

        if (list.isEmpty()) {
            sb.append("No hay iniciativas creadas.\n");
        } else {
            for (Iniciativa iniciativa : list) {
                sb.append("🔹 ").append(iniciativa.getNombre()).append("\n");
                sb.append("   📝 ").append(iniciativa.getDescripcion() != null ?
                        (iniciativa.getDescripcion().length() > 50 ?
                                iniciativa.getDescripcion().substring(0, 47) + "..." :
                                iniciativa.getDescripcion()) :
                        "Sin descripción").append("\n");
                sb.append("   📅 Actividades: ").append(iniciativa.getList().size()).append("\n");
                sb.append("   ──────────────────────────────────────\n");
            }
        }
        sb.append("══════════════════════════════════════════");
        return sb.toString();
    }
}
