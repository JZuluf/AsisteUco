package co.edu.uco.asisteuco.application.outputport.dto;

import java.util.UUID;

public final class ProfesorDTO {
    private UUID id;
    private UUID tipoIdentificacionId;
    private String numeroIdentificacion;
    private String nombresCompletos;

    public ProfesorDTO() {
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTipoIdentificacionId() {
        return tipoIdentificacionId;
    }
    public void setTipoIdentificacionId(UUID tipoIdentificacionId) {
        this.tipoIdentificacionId = tipoIdentificacionId;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombresCompletos() {
        return nombresCompletos;
    }
    public void setNombresCompletos(String nombresCompletos) {
        this.nombresCompletos = nombresCompletos;
    }
}