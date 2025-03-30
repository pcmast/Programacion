package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuario {
    @XmlElement
    private String nombre;

    @XmlElement
    private String usuario;

    @XmlElement
    private String contrasenna;

    @XmlElement
    private String correo;

    //Constructor vacío
    public Usuario() {
    }
    public Usuario(String nombre, String usuario, String contrasenna, String correo) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasenna = hashPassword(contrasenna); // Se almacena el hash;
        this.correo = correo;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    // No lleva getter porque BCrypt.hashpw() ya lo devuelve en formato de String

    // Método para establecer la contraseña (almacena el hash)
    public void setContrasenna(String contrasenna) {
        this.contrasenna = hashPassword(contrasenna);
    }
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Método para verificar la contraseña ingresada
    public boolean verificarContrasenna(String contrasennaIngresada) {
        return BCrypt.checkpw(contrasennaIngresada, this.contrasenna);
    }

    // Método privado para hashear la contraseña con BCrypt
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12)); // Esto de las rondas es la cantidad de veces que BCrypt
                                                                        // va a repetir el proceso de cifrado interno
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(contrasenna, usuario.contrasenna) && Objects.equals(correo, usuario.correo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contrasenna, correo);
    }
}