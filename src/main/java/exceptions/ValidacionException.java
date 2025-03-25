package exceptions;

/**
 * Excepción para errores de validación de datos
 */
public class ValidacionException extends Exception {
    public ValidacionException(String mensaje) {
        super(mensaje);
    }
}

