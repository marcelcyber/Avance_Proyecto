import java.util.LinkedList;
import java.util.Queue;

public class ColaIncidentes {
    public Queue<Incidente> cola;

    public ColaIncidentes() {
        cola = new LinkedList<>();
    }

    public void encolar(Incidente incidente) {
        cola.offer(incidente);
    }

    public Incidente desencolar() {
        return cola.poll(); // Devuelve y elimina el primero
    }

    public Incidente verPrimero() {
        return cola.peek(); // Solo devuelve el primero
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }

    public int tamaño() {
        return cola.size();
    }

    public Queue<Incidente> obtenerTodos() {
        return new LinkedList<>(cola); // Devolver copia para evitar errores externos
    }
}
