package interfaces;

public interface InterfazUsuario {

    public boolean iniciarSesion(String correo, String contrasenna);
    public void registrarse(String nombre, String usuario, String correo, String contrasenna);
    public boolean cerrarSesion();



}
