package co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.dto.response;

import java.util.List;
import java.util.UUID;

public class RegistrarAsistenciaResponseDTO {
    private List<UUID> ids;

    public RegistrarAsistenciaResponseDTO(List<UUID> ids) {
        this.ids = ids;
    }
    public List<UUID> getIds() { return ids; }
}