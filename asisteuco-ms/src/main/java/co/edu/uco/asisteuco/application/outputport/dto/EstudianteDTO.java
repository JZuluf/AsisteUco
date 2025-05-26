package co.edu.uco.asisteuco.application.outputport.dto;
import java.util.UUID;

public class EstudianteDTO {

    private UUID uuid;
    private UUID tipo_identificacion;
    private String numero_identificacion;
    private String nombres_completos;

    // Constructor vacío
    public EstudianteDTO() {
    }

    // Constructor con campos
    public EstudianteDTO(UUID uuid, UUID tipoIdentificacion, String numeroIdentificacion, String nombresCompletos) {
        this.uuid = uuid;
        this.tipo_identificacion = tipoIdentificacion;
        this.numero_identificacion = numeroIdentificacion;
        this.nombres_completos = nombresCompletos;
    }

    // Getters y Setters
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getTipoIdentificacion() {
        return tipo_identificacion;
    }

    public void setTipoIdentificacion(UUID tipoIdentificacion) {
        this.tipo_identificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numero_identificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numero_identificacion = numeroIdentificacion;
    }

    public String getNombresCompletos() {
        return nombres_completos;
    }

    public void setNombresCompletos(String nombresCompletos) {
        this.nombres_completos = nombresCompletos;
    }

    // Para debugging (opcional)
    @Override
    public String toString() {
        return "EstudianteDTO{" +
                "uuid=" + uuid +
                ", tipoIdentificacion=" + tipo_identificacion +
                ", numeroIdentificacion='" + numero_identificacion + '\'' +
                ", nombresCompletos='" + nombres_completos + '\'' +
                '}';
    }
}
