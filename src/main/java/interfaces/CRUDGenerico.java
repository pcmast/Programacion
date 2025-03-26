package interfaces;

import java.util.ArrayList;
import java.util.List;

public interface CRUDGenerico<T> {

    public boolean annadirList(T t);

    public boolean eliminarList(T t);

    public boolean modificar(T t);

    List<T> obtenerTodos();
}