package model;

import javax.xml.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Creador.class, Voluntario.class})
public class Usuario {
    // Atributos del usuario
    @XmlElement
    private String nombre;

    @XmlElement
    private String usuario;

    @XmlElement
    private String contrasenna;

    @XmlElement
    private String correo;

    @XmlElement
    private String tipo;  // "creador" o "voluntario"

    /**
     * Constructor vacío para la serialización XML.
      */
    public Usuario() {
    }

    /**
     * Constructor que inicializa el usuario con los datos proporcionados y almacena el hash de la contraseña.
     *
     * @param nombre El nombre del usuario.
     * @param usuario El nombre de usuario.
     * @param contrasenna La contraseña del usuario.
     * @param correo El correo electrónico del usuario.
     * @param tipo El tipo de usuario, puede ser "creador" o "voluntario".
     */
    public Usuario(String nombre, String usuario, String contrasenna, String correo, String tipo) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasenna = hashPassword(contrasenna); // Se almacena el hash de la contraseña
        this.correo = correo;
        this.tipo = tipo;
    }

    // Métodos de acceso (getters y setters)

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

    // El getter para la contraseña no es necesario, porque va haseada y encriptada.

    /**
     * Establece la contraseña del usuario, almacenando su hash.
     *
     * @param contrasenna La contraseña en texto plano que será hasheada.
     */
    public void setContrasenna(String contrasenna) {
        this.contrasenna = hashPassword(contrasenna);
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Verifica si la contraseña ingresada coincide con la almacenada (hasheada).
     *
     * @param contrasennaIngresada La contraseña ingresada por el usuario para verificar.
     * @return true si las contraseñas coinciden, false en caso contrario.
     */
    public boolean verificarContrasenna(String contrasennaIngresada) {
        return BCrypt.checkpw(contrasennaIngresada, this.contrasenna);
    }

    /**
     * Hashea la contraseña usando el algoritmo BCrypt.
     *
     * @param password La contraseña en texto plano que se quiere hashear.
     * @return La contraseña hasheada.
     */
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12)); // El número de rondas (12) define la complejidad del hash
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(contrasenna, usuario.contrasenna) &&
                Objects.equals(correo, usuario.correo) &&
                Objects.equals(tipo, usuario.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contrasenna, correo, tipo);
    }
}
