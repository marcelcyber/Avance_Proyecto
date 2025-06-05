import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Incidente {
    private String fuente;           // Correo o URL
    private String descripcion;
    private String evidencia;       // Puede ser ruta de archivo o texto
    private String tipoAmenaza;     // Tipo desde ComboBox
    private String fechaReporte;
    private String estado;          // Por defecto "Pendiente"

    public Incidente(String fuente, String descripcion, String evidencia, String tipoAmenaza) {
        this.fuente = fuente;
        this.descripcion = descripcion;
        this.evidencia = evidencia;
        this.tipoAmenaza = tipoAmenaza;
        this.estado = "Pendiente";
        this.fechaReporte = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // Getters
    public String getFuente() { return fuente; }
    public String getDescripcion() { return descripcion; }
    public String getEvidencia() { return evidencia; }
    public String getTipoAmenaza() { return tipoAmenaza; }
    public String getFechaReporte() { return fechaReporte; }
    public String getEstado() { return estado; }

    // Setters
    public void setEstado(String estado) { this.estado = estado; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setEvidencia(String evidencia) { this.evidencia = evidencia; }

    @Override
    public String toString() {
        return "Incidente{" +
                "fuente='" + fuente + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", evidencia='" + evidencia + '\'' +
                ", tipoAmenaza='" + tipoAmenaza + '\'' +
                ", fechaReporte='" + fechaReporte + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
