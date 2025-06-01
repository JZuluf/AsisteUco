package co.edu.uco.asisteuco.application.interactor.estudiantegrupo.registrarestudiantegrupo.dto.request;

import java.util.UUID;

public final class RegistrarEstudianteGrupoRequestDTO {
    
	private UUID grupoId;
    private UUID estudianteId;

    public RegistrarEstudianteGrupoRequestDTO() { }

    public UUID getGrupoId() {
        return grupoId;
    }
    public void setGrupoId(UUID grupoId) {
        this.grupoId = grupoId;
    }

    public UUID getEstudianteId() {
        return estudianteId;
    }
    public void setEstudianteId(UUID estudianteId) {
        this.estudianteId = estudianteId;
    }
}