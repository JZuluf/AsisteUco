package co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.dto.request;

import java.util.UUID;

public class AsistenciaItemDTO {
    private UUID estudianteGrupoId;
    private Boolean asistio;

    public UUID getEstudianteGrupoId() {
        return estudianteGrupoId;
    }
    public void setEstudianteGrupoId(UUID estudianteGrupoId) {
        this.estudianteGrupoId = estudianteGrupoId;
    }

    public Boolean getAsistio() {
        return asistio;
    }
    public void setAsistio(Boolean asistio) {
        this.asistio = asistio;
    }
}
