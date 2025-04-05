package interfaces;

import java.util.List;

/**
 * Interfaz genérica para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre una lista de objetos de tipo T.
 *
 * @param <T> El tipo de objeto sobre el que se realizarán las operaciones CRUD.
 */
public interface CRUDGenerico<T> {

    /**
     * Añade un elemento a la lista.
     *
     * @param t El elemento a añadir.
     * @return true si el elemento fue añadido con éxito, false en caso contrario.
     */
    public boolean annadirList(T t);

    /**
     * Elimina un elemento de la lista basado en una cadena de texto.
     *
     * @param cadena La cadena que representa el elemento a eliminar.
     * @return true si el elemento fue eliminado con éxito, false si no se encontró.
     */
    public boolean eliminarList(String cadena);

    /**
     * Modifica un elemento de la lista.
     *
     * @param t El elemento con los nuevos datos.
     * @return true si el elemento fue modificado con éxito, false si no se pudo modificar.
     */
    public boolean modificar(T t);

    /**
     * Obtiene todos los elementos de la lista.
     *
     * @return Una lista con todos los elementos.
     */
    List<T> obtenerTodos();
}
