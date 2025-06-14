package co.edu.uco.asisteuco.application.interactor.estudiante.registrarestudiante.dto.request;

import java.util.UUID;

public final class RegistrarEstudianteRequestDTO {

    private UUID uuid;
    private UUID tipo_identificacion;
    private String numero_identificacion;
    private String nombres_completos;

    // Constructor vacío
    public RegistrarEstudianteRequestDTO() {
    	super();
    }

    // Constructor con parámetros
    public RegistrarEstudianteRequestDTO(UUID uuid, UUID tipo_identificacion, String numero_identificacion, String nombres_completos) {
        this.uuid = uuid;
        this.tipo_identificacion = tipo_identificacion;
        this.numero_identificacion = numero_identificacion;
        this.nombres_completos = nombres_completos;
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

    public void setTipoIdentificacion(UUID tipo_identificacion) {
        this.tipo_identificacion = tipo_identificacion;
    }

    public String getNumeroIdentificacion() {
        return numero_identificacion;
    }

    public void setNumeroIdentificacion(String numero_identificacion) {
        this.numero_identificacion = numero_identificacion;
    }

    public String getNombresCompletos() {
        return nombres_completos;
    }

    public void setNombresCompletos(String nombres_completos) {
        this.nombres_completos = nombres_completos;
    }

}
