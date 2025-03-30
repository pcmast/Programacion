package controller;

import dataAcces.XMLManager;
import model.Usuario;
import utils.Utilidades;
import view.MenuVista;

import java.util.ArrayList;

import static view.MenuVista.menuCreador;
import static view.MenuVista.menuVoluntarios;

public class UsuarioController {
    private ArrayList<Usuario> list = new ArrayList<>();

    public ArrayList<Usuario> getList() {
        return list;
    }

    /**
     * Método que muestra en pantalla el menú de registro de usuario,crea el usuario con los datos del registro y lo añade a la lista de usuarios registrados.
     */
    public void registrarUsuario(Usuario usuarioNuevo) {
        try{
            ArrayList<Usuario> usuariosDeXML = XMLManager.readXML(new ArrayList<Usuario>(), "usuarios.xml");
            if (usuariosDeXML != null) {
                list = usuariosDeXML;
            }
        } catch (RuntimeException e){
            MenuVista.muestraMensaje("❌ Error al obtener el usuario");
        }
        if (list.contains(usuarioNuevo)) {
            MenuVista.muestraMensaje("¡El usuario ya existe!");
        } else if (list.add(usuarioNuevo)) {
            MenuVista.muestraMensaje(">> ✅ ¡El usuario se ha registrado correctamente!");
            try {
                boolean exito = XMLManager.writeXML(list, "usuarios.xml");
                if (exito) {
                    MenuVista.muestraMensaje(">> ✅ Datos guardados correctamente en XML");
                } else {
                    MenuVista.muestraMensaje(">> ❌ Ocurrió un error al guardar los datos en XML.");
                }
            } catch (RuntimeException e) {
                MenuVista.muestraMensaje("❌ ¡Error al guardar en XML: " + e.getMessage() + "!");
            }
        } else {
            MenuVista.muestraMensaje(">> ❌ ¡El usuario no ha sido registrado!");


            if (list.contains(usuarioNuevo)) {
                MenuVista.muestraMensaje(">> El usuario ya existe");
            } else if (list.add(usuarioNuevo)) {
                MenuVista.muestraMensaje(">> El usuario se ha registrado correctamente");
            } else {
                MenuVista.muestraMensaje(">> El usuario no ha sido registrado.");
            }

        }
    }

    /**
     * Método que muestra en pantalla el menú de inicio de sesión, y comprueba si el usuario existe en la lista y si la contraseña introducida es correcta. Si es así se establece ese usuario como el actual.
     */
    public void iniciarSesion(String correo, String contrasenna) {
        UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();


        for (Usuario usuario1: list){
            if (usuario1.getCorreo().equals(correo) && usuario1.getContrasenna().equals(contrasenna)){
                usuarioActualController.setUsuario(usuario1);
            }else {
                MenuVista.muestraMensaje("Usuario o contraseña incorrectos.");
            }

        }

    }

    public int tipoUsuario() {
        MenuVista.muestraMenuCreadorOVoluntario();
        int opcion = Utilidades.leeEntero("Opción:");
        switch (opcion) {
            case 1:
                MenuVista.menuCreador();
                break;
            case 2:
                MenuVista.menuVoluntarios();
                break;
            default:
                MenuVista.muestraMensaje("Opción no válida");
        }
        return opcion;
    }



}