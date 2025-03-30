package utils;

import exceptions.ValidacionException;
import view.MenuVista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilidades {
    // Instancia del Scanner para leer la entrada del usuario
    public static Scanner sc = new Scanner(System.in);

    /**
     * Lee un número entero desde la consola con validación.
     * Si el usuario introduce un valor no válido, se muestra un mensaje de error y se solicita nuevamente el número.
     *
     * @param mensaje El mensaje que se muestra al usuario antes de pedir el número.
     * @return El número entero introducido por el usuario.
     */
    public static int leeEntero(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        int numero = 0;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.print(mensaje);
                numero = Integer.parseInt(scanner.nextLine());  // Intenta convertir la entrada en un número entero.
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número entero válido.");
            }
        }
        return numero;
    }

    /**
     * Lee un número decimal desde la consola con validación.
     * Si el usuario introduce un valor no válido, se muestra un mensaje de error y se solicita nuevamente el número.
     *
     * @param mensaje El mensaje que se muestra al usuario antes de pedir el número.
     * @return El número decimal (double) introducido por el usuario.
     */
    public static double leeDouble(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        double numero = 0;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.print(mensaje);
                numero = Double.parseDouble(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido.");  // Muestra mensaje de error si la entrada no es un número válido.
            }
        }
        return numero;
    }

    /**
     * Valida un correo electrónico asegurándose de que tenga el formato estándar:
     * - Parte local (antes del '@') con letras, números y ciertos caracteres especiales.
     * - Parte del dominio (después del '@') con letras, números, guiones y al menos un punto.
     * - Extensión del dominio de 2 a 7 caracteres alfabéticos.
     *
     * @param correo El correo a validar.
     * @return true si el correo es válido, false si no lo es.
     */
    public static boolean validarCorreo(String correo) throws ValidacionException {
        boolean valido = false;
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!correo.matches(regex)) {
            throw new ValidacionException("El correo proporcionado (" + correo + ") no tiene un formato válido.");
        }
        valido = true;
        return valido;
    }

    /**
     * Valida un número de teléfono con el formato estándar.
     * La expresión regular comprueba lo siguiente:
     * - El número debe comenzar con un prefijo de país opcional (+ seguido de 1 a 3 dígitos) o un número local que comience con un dígito.
     * - Puede tener espacios o guiones como separadores, pero no debe haber más de un espacio consecutivo.
     * - El número debe tener entre 10 y 15 dígitos en total.
     *
     * @param telefono El número de teléfono a validar.
     * @return true si el número de teléfono es válido según el formato, false en caso contrario.
     */
    public static boolean validarTelefono(String telefono) {
        boolean valido = false;
        try {
            if (!telefono.matches("^\\+?[1-9]\\d{1,2}[\\s\\-]?\\(?\\d{1,4}\\)?[\\s\\-]?\\d{1,4}[\\s\\-]?\\d{1,4}$")) {
                throw new ValidacionException("El número de teléfono (" + telefono + ") no tiene un formato válido.");
            }
            valido = true;
        } catch (ValidacionException e) {
            System.out.println("Error de validación: " + e.getMessage());
            valido = false;
        }
        return valido;
    }

    /**
     * Pide una cadena de texto al usuario.
     *
     * @param msn : el mensaje que se muestra al usuario.
     * @return la cadena introducida por el usuario.
     */
    public static String pideString(String msn) {
        MenuVista.muestraMensaje(msn);
        String cadena = null;
        cadena = sc.next(); // Lee una palabra
        sc.nextLine(); // Consume el salto de línea pendiente
        return cadena;
    }

    /**
     * Pide al usuario que introduzca una fecha por texto con el siguiente formato: (dd/MM/yyyy)
     *
     * @param msn mensaje que vamos a mostrar al usuario
     * @return fecha introducida por el usuario en formato LocalDate, o null si no se ha introducido una fecha correcta.
     */
    public static LocalDate pideFecha(String msn) {
        LocalDate fecha = null;
        boolean fechaCorrecta = false;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (!fechaCorrecta) {
            MenuVista.muestraMensaje(msn);
            String fechaString = sc.nextLine();

            try {
                fecha = LocalDate.parse(fechaString, formatoFecha);
                fechaCorrecta = true;
                MenuVista.muestraMensaje("fecha introducida: " + fecha);
            } catch (DateTimeParseException e) {
                MenuVista.muestraMensaje("Formato de fecha incorrecto. Debe ser dd/MM/yyyy");
            }
        }
        return fecha;
    }
}
