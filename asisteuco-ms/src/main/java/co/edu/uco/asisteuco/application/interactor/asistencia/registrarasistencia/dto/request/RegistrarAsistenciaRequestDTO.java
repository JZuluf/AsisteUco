package co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.dto.request;

import java.util.List;
import java.util.UUID;

public class RegistrarAsistenciaRequestDTO {
    private UUID sesionId;
    private List<DetalleAsistenciaDTO> asistencias;

    public UUID getSesionId() {
        return sesionId;
    }
    public void setSesionId(UUID sesionId) {
        this.sesionId = sesionId;
    }

    public List<DetalleAsistenciaDTO> getAsistencias() {
        return asistencias;
    }
    public void setAsistencias(List<DetalleAsistenciaDTO> asistencias) {
        this.asistencias = asistencias;
    }
}

