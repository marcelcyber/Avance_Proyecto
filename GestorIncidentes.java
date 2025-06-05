public class GestorIncidentes {
    public ColaIncidentes cola;

    public GestorIncidentes() {
        cola = new ColaIncidentes();
    }

    public void registrarIncidente(String fuente, String descripcion, String evidencia, String tipoAmenaza) {
        Incidente nuevo = new Incidente(fuente, descripcion, evidencia, tipoAmenaza);
        cola.encolar(nuevo);
        System.out.println("📥 Incidente registrado exitosamente.");
    }

    public ColaIncidentes getCola() {
        return cola;
    }

    public void mostrarTodos() {
        System.out.println("📋 Lista de incidentes:");
        for (Incidente i : cola.obtenerTodos()) {
            System.out.println(i);
        }
    }
}
