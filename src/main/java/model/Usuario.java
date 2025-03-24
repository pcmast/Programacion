package model;

public class Usuario {
    private String nombre;
    private String usuario;
    private String contrasenna;
    private String correo;

    public Usuario(String nombre, String usuario, String contrasenna, String correo) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasenna = contrasenna;
        this.correo = correo;
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

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}