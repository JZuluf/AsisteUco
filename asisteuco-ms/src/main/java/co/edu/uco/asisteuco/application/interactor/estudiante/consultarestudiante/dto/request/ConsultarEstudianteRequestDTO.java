package co.edu.uco.asisteuco.application.interactor.estudiante.consultarestudiante.dto.request;

import java.util.UUID;

public final class ConsultarEstudianteRequestDTO {

    private UUID id;
    private UUID tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombresCompletos;

    public ConsultarEstudianteRequestDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(UUID tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
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