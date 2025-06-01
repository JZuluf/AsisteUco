package co.edu.uco.asisteuco.application.interactor.sesion.registrarsesion.dto.response;

import java.util.UUID;

public class RegistrarSesionResponseDTO {
   
	private UUID id;
    
    public RegistrarSesionResponseDTO(UUID id) { this.id = id; }
   
    public UUID getId() { return id; }
	
    public void setId(UUID id) {
		this.id = id;
	}
    
    
}