package exceptions;

/**
 * Excepción para errores relacionados con la autenticación de usuarios
 */
public class AutenticacionException extends Exception {
  public AutenticacionException(String mensaje) {
    super(mensaje);
  }
}
