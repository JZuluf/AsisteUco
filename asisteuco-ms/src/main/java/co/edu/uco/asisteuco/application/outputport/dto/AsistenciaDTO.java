package co.edu.uco.asisteuco.application.outputport.dto;

import java.util.UUID;

public final class AsistenciaDTO {

    private UUID id;
    private UUID estudianteId;
    private UUID sesionId;
    private boolean asistio;

    public AsistenciaDTO() {
    }

    public AsistenciaDTO(final UUID id,final UUID estudianteId,final UUID sesionId,final boolean asistio) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.sesionId = sesionId;
        this.asistio = asistio;
    }

    // Getters y Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(UUID estudianteId) {
        this.estudianteId = estudianteId;
    }

    public UUID getSesionId() {
        return sesionId;
    }

    public void setSesionId(UUID sesionId) {
        this.sesionId = sesionId;
    }

    public boolean isAsistio() {
        return asistio;
    }

    public void setAsistio(boolean asistio) {
        this.asistio = asistio;
    }
}