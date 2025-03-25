package model;

import interfaces.InterfazUsuario;

public class Usuario implements InterfazUsuario {
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
    public Usuario() {
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

    public boolean iniciarSesion(String correo, String contrasenna) {
        this.correo = correo;
        this.contrasenna = contrasenna;



        return false;
    }


    public void registrarse(String nombre, String usuario, String correo, String contrasenna) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.correo = correo;
        this.contrasenna = contrasenna;


    }

    public boolean cerrarSesion() {



        return false;
    }
}