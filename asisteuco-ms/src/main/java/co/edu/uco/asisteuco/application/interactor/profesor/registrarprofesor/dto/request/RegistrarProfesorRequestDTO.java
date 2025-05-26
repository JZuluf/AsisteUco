package co.edu.uco.asisteuco.application.interactor.profesor.registrarprofesor.dto.request;

import java.util.UUID;

public final class RegistrarProfesorRequestDTO {
    private UUID tipoIdentificacionId;
    private String numeroIdentificacion;
    private String nombresCompletos;

    public UUID getTipoIdentificacionId() { return tipoIdentificacionId; }
    public void setTipoIdentificacionId(UUID tipoIdentificacionId) {
        this.tipoIdentificacionId = tipoIdentificacionId;
    }

    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombresCompletos() { return nombresCompletos; }
    public void setNombresCompletos(String nombresCompletos) {
        this.nombresCompletos = nombresCompletos;
    }
}