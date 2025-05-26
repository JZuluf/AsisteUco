package co.edu.uco.asisteuco.application.interactor.profesor.consultarprofesor.dto.request;

import java.util.UUID;

public final class ConsultarProfesorRequestDTO {
    
	private UUID id;
    private UUID tipoIdentificacionId;
    private String numeroIdentificacion;
    private String nombresCompletos;
	
    
    
    public ConsultarProfesorRequestDTO() {
		super();
	}
	public ConsultarProfesorRequestDTO(UUID id, UUID tipoIdentificacionId, String numeroIdentificacion,
			String nombresCompletos) {
		super();
		this.id = id;
		this.tipoIdentificacionId = tipoIdentificacionId;
		this.numeroIdentificacion = numeroIdentificacion;
		this.nombresCompletos = nombresCompletos;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getTipoIdentificacionId() {
		return tipoIdentificacionId;
	}
	public void setTipoIdentificacionId(UUID tipoIdentificacionId) {
		this.tipoIdentificacionId = tipoIdentificacionId;
	}
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
	public String getNombresCompletos() {
		return nombresCompletos;
	}
	public void setNombresCompletos(String nombresCompletos) {
		this.nombresCompletos = nombresCompletos;
	}

    
}