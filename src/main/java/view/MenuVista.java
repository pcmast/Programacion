package view;

import model.Usuario;

import java.util.HashMap;
import java.util.Map;

public class MenuVista {
    private HashMap<String, Usuario> usuarios;


    public MenuVista(){
        this.usuarios = new HashMap<>();
    }


    /**
     *
     * @param usuario que recibe el metodo,
     *                el metodo comprueba que no exista ya,
     *                si existe devuelve false.
     * @return
     */
    public boolean registrarUsuario (Usuario usuario){
        boolean registrado = true;
        if (usuarios.containsKey(usuario.getUsuario())){
            registrado = false;
        }

        return registrado;

    }

    /**
     *
     * @param usuario
     * @param contrasenna
     *
     * Se busca el usuario en el HashMap usuarios usando el nombre de usuario de clave.
     * El resultado de la busqueda se almacena en la variable Usuario u.
     * Comporbamos que el usuario no este nulo y que las contraseñas coincidan
     * Si se cumple el boolean cambia su valor a true
     * @return
     */
    public boolean iniciarSesion(String usuario, String contrasenna){
        Usuario u = usuarios.get(usuario);
        boolean correcto = false;

        if (u != null && u.getContrasenna().equals(contrasenna)){
            correcto = true;
        }
        return correcto;
    }
}
