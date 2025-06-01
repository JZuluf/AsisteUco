package co.edu.uco.asisteuco.application.outputport.dto;

import java.util.UUID;

public final class GrupoDTO {
    
	private UUID id;
    private UUID profesorId;
    private UUID materiaId;
    private int cantidadEstudiantes;

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
    public int getCantidadEstudiantes() {
        return cantidadEstudiantes;
    }
    public void setCantidadEstudiantes(int cantidadEstudiantes) {
        this.cantidadEstudiantes = cantidadEstudiantes;
    }
}
