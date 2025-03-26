package view;
import model.Actividad;
import model.EstadoActividad;
import model.Iniciativa;
import model.Usuario;
import utils.Utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class menuIniciativaActividad {
    /**
     * Metodo que pide los datos necesarios para crear una iniciativa.
     * @param usuario El usuario el cual crea la iniciativa del que cojemos su nombre.
     * @return la iniciativa nueva.
     */
    public Iniciativa pideDatosCrearIniciativa(Usuario usuario) {
        String nombre = Utilidades.pideString("Introduce el nombre de la iniciativa");
        String descripcion = Utilidades.pideString("Introduce una descripcion a la iniciativa");
        String creadorIniciativa = usuario.getNombre();

        Iniciativa iniciativaNueva = new Iniciativa(nombre, descripcion, creadorIniciativa);

        return iniciativaNueva;
    }

    /**
     * Metodo que pide los datos necesarios para crear una actividad.
     * @return la actividad nueva.
     */
//    public Actividad pideDatosCrearActividad(){
//        String nombre = Utilidades.pideString("Introduce el nombre de la actividad");
//        String descripcion = Utilidades.pideString("Introduce una descripcion a la actividad");
//
//        //FALTA AQUI LO DE LA FECHA DE INICIO
//
//        String fechaFin = Utilidades.pideString("Introduce la fecha de fin de la activiad");
//        boolean voluntario = false;
//        EstadoActividad estado = EstadoActividad.valueOf(Utilidades.pideString("Introduce el estado de la actividad (NO_INICIADA, EN_PROCESO, COMPLETADA)"));
//
//           Actividad actividadNueva = new Actividad(nombre,descripcion, fechaInicio,fechaFin,voluntario,estado);
//
//        return actividadNueva;
//    }

}
