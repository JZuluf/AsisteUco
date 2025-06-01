package co.edu.uco.asisteuco.application.outputport.dto;

import java.util.UUID;

public class AsistenciaDTO {
    private UUID id;
    private UUID estudianteGrupoId;
    private UUID sesionId;
    private Boolean asistio;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getEstudianteGrupoId() { return estudianteGrupoId; }
    public void setEstudianteGrupoId(UUID egId) { this.estudianteGrupoId = egId; }
    public UUID getSesionId() { return sesionId; }
    public void setSesionId(UUID sId) { this.sesionId = sId; }
    public Boolean getAsistio() { return asistio; }
    public void setAsistio(Boolean a) { this.asistio = a; }
}