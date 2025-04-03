package utils;

import model.Usuario;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que funciona como contenedor para una lista de usuarios. Se utiliza serialización y deserialización de la lista de los usuarios
 */

//la clase puede ser el elemento raiz de un documento XML
@XmlRootElement(name = "usuarios")
public class UsuariosContenedor {
    private List<Usuario> usuarios;

    /**
     * constructor vacio para inicializar la lista de los usuarios
     */
    public UsuariosContenedor() {
        this.usuarios = new ArrayList<>();
    }

    //indicamos que cada usuario se va a representar como un elemento <usuario> en el XML
    @XmlElement(name = "usuario")
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
//comentario para commit

