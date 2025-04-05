package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    /**
     * Formato de fecha para convertir entre LocalDate y String
      */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Convierte un String a un objeto LocalDate.
     *
     * @param v El valor en formato String a convertir.
     * @return El objeto LocalDate correspondiente a la fecha en el String.
     */
    @Override
    public LocalDate unmarshal(String v) {
        return (v != null) ? LocalDate.parse(v, FORMATTER) : null;
    }

    /**
     * Convierte un objeto LocalDate a un String.
     *
     * @param v El objeto LocalDate a convertir.
     * @return El String correspondiente a la fecha en formato ISO.
     */
    @Override
    public String marshal(LocalDate v) {
        return (v != null) ? v.format(FORMATTER) : null;
    }
}
