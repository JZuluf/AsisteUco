package co.edu.uco.asisteuco.application.interactor.grupo.registrargrupo.dto.response;

import java.util.UUID;

public final class RegistrarGrupoResponseDTO {
    private final UUID id;

    private RegistrarGrupoResponseDTO(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public static RegistrarGrupoResponseDTO exitoso(UUID id) {
        return new RegistrarGrupoResponseDTO(id);
    }
}
