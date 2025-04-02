package controller;

import dataAcces.IniciativasContenedor;
import dataAcces.XMLManager;
import model.*;
import utils.Utilidades;
import view.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class IniciativaController {
    private UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();
    private ArrayList<Iniciativa> iniciativas = new ArrayList<>();

    public IniciativaController() {
        cargarIniciativas();
    }

    public void creaIniciativa() {
        if (!(usuarioActualController.getUsuario() instanceof Creador creador)) {
            MenuVista.muestraMensaje("Error: Solo creadores pueden crear iniciativas");
            return;
        }

        Iniciativa iniciativa = MenuIniciativaActividad.pideDatosCrearIniciativa(creador);

        if (iniciativaExiste(iniciativa.getNombre())) {
            MenuVista.muestraMensaje("Error: Iniciativa ya existe");
            return;
        }

        if (creador.crearIniciativa(iniciativa)) {
            iniciativas.add(iniciativa);
            guardarIniciativas();
            MenuVista.muestraMensaje("Iniciativa creada con éxito");
        } else {
            MenuVista.muestraMensaje("Error al crear iniciativa");
        }
    }

    public void eliminaIniciativa() {
        String nombre = Utilidades.pideString("Nombre de la iniciativa a eliminar:");
        boolean eliminada = false;

        if (usuarioActualController.getUsuario() instanceof Creador creador) {
            eliminada = creador.verIniciativas().removeIf(i -> i.getNombre().equals(nombre));

            if (eliminada) {
                iniciativas.removeIf(i -> i.getNombre().equals(nombre));
                guardarIniciativas();
                MenuVista.muestraMensaje("Iniciativa eliminada");
            } else {
                MenuVista.muestraMensaje("Iniciativa no encontrada");
            }
        } else {
            MenuVista.muestraMensaje("Error: Solo creadores pueden eliminar iniciativas");
        }
    }

    public void modificarIniciativa() {
        String nombreActual = Utilidades.pideString("Nombre de la iniciativa a modificar:");

        if (usuarioActualController.getUsuario() instanceof Creador creador) {
            Iniciativa iniciativa = obtenerIniciativa(nombreActual);

            if (iniciativa != null) {
                Iniciativa nuevosDatos = MenuIniciativaActividad.pideDatosCrearIniciativa(creador);
                iniciativa.setNombre(nuevosDatos.getNombre());
                iniciativa.setDescripcion(nuevosDatos.getDescripcion());
                guardarIniciativas();
                MenuVista.muestraMensaje("Iniciativa modificada");
            } else {
                MenuVista.muestraMensaje("Iniciativa no encontrada");
            }
        } else {
            MenuVista.muestraMensaje("Error: Solo creadores pueden modificar iniciativas");
        }
    }

    public void muestraIniciativas() {
        if (iniciativas.isEmpty()) {
            MenuVista.muestraMensaje("No hay iniciativas registradas");
            return;
        }

        MenuVista.muestraMensaje("=== LISTADO DE INICIATIVAS ===");
        for (Iniciativa iniciativa : iniciativas) {
            MenuVista.muestraMensaje(iniciativa.toString());
        }
        MenuVista.muestraMensaje("=============================");
    }

    public void muestraIniciativasNombre() {
        if (iniciativas.isEmpty()) {
            MenuVista.muestraMensaje("No hay iniciativas registradas");
            return;
        }

        MenuVista.muestraMensaje("=== NOMBRES DE INICIATIVAS ===");
        for (Iniciativa iniciativa : iniciativas) {
            MenuVista.muestraMensaje("- " + iniciativa.getNombre());
        }
        MenuVista.muestraMensaje("==============================");
    }

    public void muestraIniciativasUsuario() {
        Usuario usuario = usuarioActualController.getUsuario();
        List<Iniciativa> iniciativasUsuario = new ArrayList<>();

        if (usuario instanceof Creador creador) {
            iniciativasUsuario = creador.verIniciativas();
        } else if (usuario instanceof Voluntario voluntario) {
            for (Actividad actividad : voluntario.getList()) {
                Iniciativa iniciativa = obtenerIniciativa(actividad.getIniciativa());
                if (iniciativa != null && !iniciativasUsuario.contains(iniciativa)) {
                    iniciativasUsuario.add(iniciativa);
                }
            }
        }

        if (iniciativasUsuario.isEmpty()) {
            MenuVista.muestraMensaje("No tienes iniciativas asignadas");
            return;
        }

        MenuVista.muestraMensaje("=== TUS INICIATIVAS ===");
        for (Iniciativa iniciativa : iniciativasUsuario) {
            MenuVista.muestraMensaje(iniciativa.toString());
        }
        MenuVista.muestraMensaje("=======================");
    }

    public void cargarIniciativas() {
        try {
            File file = new File("iniciativas.xml");
            if (file.exists()) {
                JAXBContext context = JAXBContext.newInstance(IniciativasContenedor.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();

                IniciativasContenedor wrapper = (IniciativasContenedor) unmarshaller.unmarshal(file);
                this.iniciativas = wrapper.getIniciativas();
            }
        } catch (JAXBException e) {
            System.err.println("Error al cargar iniciativas: " + e.getMessage());
            this.iniciativas = new ArrayList<>();
        }
    }

    public void guardarIniciativas() {
        try {
            JAXBContext context = JAXBContext.newInstance(Iniciativa.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Guardar cada iniciativa individualmente
            for (Iniciativa iniciativa : iniciativas) {
                String filename = "iniciativa_" + iniciativa.getNombre().replace(" ", "_") + ".xml";
                marshaller.marshal(iniciativa, new File(filename));
            }
        } catch (JAXBException e) {
            System.err.println("Error al guardar iniciativas: " + e.getMessage());
        }
    }

    private boolean iniciativaExiste(String nombre) {
        boolean existe = false;
        for (Iniciativa iniciativa : iniciativas) {
            if (iniciativa.getNombre().equalsIgnoreCase(nombre)) {
                existe = true;
            }
        }
        return existe;
    }

    private Iniciativa obtenerIniciativa(String nombre) {
        for (Iniciativa iniciativa : iniciativas) {
            if (iniciativa.getNombre().equalsIgnoreCase(nombre)) {
                return iniciativa;
            }
        }
        return null;
    }
}