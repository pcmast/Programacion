package model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "Creador")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Creador")
public class Creador extends Usuario {

    @XmlElement
    private String ongPertenece;

    @XmlElement(name = "iniciativa", type = Iniciativa.class)
    private ArrayList<Iniciativa> list = new ArrayList<>();

    public Creador() {
    }

    public Creador(String nombre, String usuario, String contrasenna, String correo, String ong) {
        super(nombre, usuario, contrasenna, correo, "creador");
        this.ongPertenece = ong;
    }

    /**
     * Obtiene la ONG a la que pertenece el creador.
     *
     * @return El nombre de la ONG.
     */
    public String getOngPertenece() {
        return ongPertenece;
    }

    /**
     * Establece la ONG a la que pertenece el creador.
     *
     * @param ongPertenece El nombre de la ONG.
     */
    public void setOngPertenece(String ongPertenece) {
        this.ongPertenece = ongPertenece;
    }

    /**
     * Obtiene la lista de iniciativas asociadas al creador.
     *
     * @return La lista de iniciativas.
     */
    public ArrayList<Iniciativa> getList() {
        return list;
    }

    /**
     * Establece la lista de iniciativas asociadas al creador.
     *
     * @param list La lista de iniciativas.
     */
    public void setList(ArrayList<Iniciativa> list) {
        this.list = list;
    }

    /**
     * Crea una nueva iniciativa y la añade a la lista de iniciativas del creador si no existe.
     *
     * @param iniciativa La iniciativa a añadir.
     * @return true si la iniciativa fue añadida con éxito, false si ya existía en la lista.
     */
    public boolean crearIniciativa(Iniciativa iniciativa) {
        boolean creada = false;
        if (!list.contains(iniciativa)) {
            list.add(iniciativa);
            creada = true;
        }
        return creada;
    }

    /**
     * Elimina una iniciativa de la lista del creador usando el nombre y el nombre del creador.
     *
     * @param nombre        El nombre de la iniciativa.
     * @param nombreCreador El nombre del creador de la iniciativa.
     * @return true si la iniciativa fue eliminada correctamente, false si no se encontró.
     */
    public boolean eliminarIniciativa(String nombre, String nombreCreador) {
        boolean eliminada = false;
        for (Iniciativa iniciativa : list) {
            if (iniciativa.getNombre().equals(nombre) && iniciativa.getCreadorIniciativa().equals(nombreCreador)) {
                list.remove(iniciativa);
                eliminada = true;
            }
        }
        return eliminada;
    }

    /**
     * Crea una actividad y la añade a una iniciativa específica.
     *
     * @param actividad        La actividad a crear.
     * @param nombreIniciativa El nombre de la iniciativa a la que se añadirá la actividad.
     * @return true si la actividad fue añadida correctamente, false si ya existe.
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
                        }
                    }

                    // Si no es duplicado, añadir
                    if (!existeDuplicado) {
                        if (iniciativa.annadirList(actividad)) {
                            actividad.setIniciativa(nombreIniciativa);
                            resultado = true;
                        }
                    }
                }
            }
        }

        return resultado;
    }

    /**
     * Elimina una actividad de una iniciativa específica.
     *
     * @param nombreActividad  El nombre de la actividad a eliminar.
     * @param nombreIniciativa El nombre de la iniciativa a la que pertenece la actividad.
     * @return true si la actividad fue eliminada, false si no se pudo encontrar.
     */
    public boolean eliminarActividad(String nombreActividad, String nombreIniciativa) {
        boolean eliminada = false;
        for (Iniciativa iniciativa : list) {
            if (iniciativa.getNombre().equals(nombreIniciativa)) {
                ArrayList<Actividad> lista1 = iniciativa.getList();
                for (Actividad actividad : lista1) {
                    if (actividad.getNombre().equals(nombreActividad)) {
                        lista1.remove(actividad); // Elimina la actividad
                        eliminada = true;
                    }
                }
            }
        }
        return eliminada;
    }

    /**
     * Devuelve la lista de iniciativas del creador.
     *
     * @return Una lista con las iniciativas del creador.
     */
    public ArrayList<Iniciativa> verIniciativas() {
        return getList();
    }

    /**
     * Método toString que muestra el perfil del creador con sus iniciativas.
     *
     * @return Una cadena con el perfil del creador.
     */
    @Override
    public String toString() {
        System.out.println("══════════════════════════════════════════");
        System.out.println("           🧑💼 PERFIL DE CREADOR       ");
        System.out.println("══════════════════════════════════════════");

        System.out.println("👤 Nombre: " + (getNombre() == null ? "N/A" : getNombre()));
        System.out.println("📧 Correo: " + (getCorreo() == null ? "N/A" : getCorreo()));
        System.out.println("🏛️ ONG: " + (ongPertenece == null ? "N/A" : ongPertenece));

        System.out.println("══════════════════════════════════════════");
        System.out.println("🌱 INICIATIVAS (" + list.size() + ")");
        System.out.println("══════════════════════════════════════════");

        if (list.isEmpty()) {
            System.out.println("No hay iniciativas creadas.");
        } else {
            for (Iniciativa iniciativa : list) {
                System.out.println("🔹 " + iniciativa.getNombre());
                String descripcion = iniciativa.getDescripcion();
                if (descripcion == null) {
                    System.out.println("   📝 Sin descripción");
                } else if (descripcion.length() > 50) {
                    System.out.println("   📝 " + descripcion.substring(0, 47) + "...");
                } else {
                    System.out.println("   📝 " + descripcion);
                }
                System.out.println("   📅 Actividades: " + iniciativa.getList().size());
                System.out.println("   ──────────────────────────────────────");
            }
        }

        System.out.println("══════════════════════════════════════════");
        return "";
    }

    //Aquí hemos implementado una nueva metodología para los toStrings, para poder jugar con ellos, es decir, código más limpio, y una salida más limpia.
    //Sabemos perfectamente lo que quiere decir la interrogación y para que se utiliza. Y en que casos.
}
