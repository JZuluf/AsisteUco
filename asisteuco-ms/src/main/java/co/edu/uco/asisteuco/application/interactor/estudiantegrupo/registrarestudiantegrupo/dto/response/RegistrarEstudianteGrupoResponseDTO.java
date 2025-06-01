package co.edu.uco.asisteuco.application.interactor.estudiantegrupo.registrarestudiantegrupo.dto.response;

import java.util.UUID;

public final class RegistrarEstudianteGrupoResponseDTO {
    private UUID id;

    public RegistrarEstudianteGrupoResponseDTO() { }

    public RegistrarEstudianteGrupoResponseDTO(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
}
