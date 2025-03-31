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

    /**
     * Constructor vacío
     */
    public Usuario() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param nombre      Nombre del usuario.
     * @param usuario     Nombre de usuario.
     * @param contrasenna Contraseña en texto plano que será cifrada.
     * @param correo      Correo electrónico del usuario.
     */
    public Usuario(String nombre, String usuario, String contrasenna, String correo) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasenna = hashPassword(contrasenna); // Se almacena el hash
        this.correo = correo;
    }

    /**
     * Constructor para inicio de sesion
     * @param usuario     Nombre de usuario.
     * @param contrasenna Contraseña en texto plano que será cifrada.
     */
    public Usuario (String usuario, String contrasenna){
        this.usuario = usuario;
        this.contrasenna = contrasenna;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return Nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre Nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return Nombre de usuario.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param usuario Nombre de usuario.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Establece la contraseña (almacena el hash en la variable interna).
     *
     * @param contrasenna Contraseña en texto plano.
     */
    public void setContrasenna(String contrasenna) {
        this.contrasenna = hashPassword(contrasenna);
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return Correo electrónico.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param correo Correo electrónico.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Verifica si la contraseña ingresada coincide con la almacenada.
     *
     * @param contrasennaIngresada Contraseña en texto plano a verificar.
     * @return true si la contraseña es correcta, false en caso contrario.
     */
    public boolean verificarContrasenna(String contrasennaIngresada) {
        return BCrypt.checkpw(contrasennaIngresada, this.contrasenna);
    }

    /**
     * Genera un hash de la contraseña utilizando BCrypt.
     *
     * @param password Contraseña en texto plano.
     * @return Hash de la contraseña.
     */
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12)); // Rondas de cifrado
    }


}