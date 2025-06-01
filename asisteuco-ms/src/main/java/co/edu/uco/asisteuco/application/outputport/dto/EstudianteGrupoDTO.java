package co.edu.uco.asisteuco.application.outputport.dto;

import java.util.UUID;

public class EstudianteGrupoDTO {
    private UUID id;
    private UUID estudianteId;
    private UUID grupoId;

    public EstudianteGrupoDTO() {}

    public EstudianteGrupoDTO(UUID id, UUID estudianteId, UUID grupoId) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.grupoId = grupoId;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getEstudianteId() { return estudianteId; }
    public void setEstudianteId(UUID estudianteId) { this.estudianteId = estudianteId; }

    public UUID getGrupoId() { return grupoId; }
    public void setGrupoId(UUID grupoId) { this.grupoId = grupoId; }
}
