package co.edu.uco.asisteuco.application.interactor.grupo.consultargrupo.dto.request;

import java.util.UUID;

public final class ConsultarGrupoRequestDTO {
    private UUID id;
    private UUID profesorId;
    private UUID materiaId;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getProfesorId() {
        return profesorId;
    }
    public void setProfesorId(UUID profesorId) {
        this.profesorId = profesorId;
    }
    public UUID getMateriaId() {
        return materiaId;
    }
    public void setMateriaId(UUID materiaId) {
        this.materiaId = materiaId;
    }
}
