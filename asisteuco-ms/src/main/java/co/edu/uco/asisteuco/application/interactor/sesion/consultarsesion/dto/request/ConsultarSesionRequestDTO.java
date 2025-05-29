package co.edu.uco.asisteuco.application.interactor.sesion.consultarsesion.dto.request;

import java.util.UUID;

public class ConsultarSesionRequestDTO {
    
	private UUID id;
    private UUID grupoId;
	
    public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getGrupoId() {
		return grupoId;
	}
	public void setGrupoId(UUID grupoId) {
		this.grupoId = grupoId;
	}

    
}
