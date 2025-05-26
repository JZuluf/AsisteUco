package co.edu.uco.asisteuco.application.interactor.materia.registrarmateria.dto.response;

import java.util.UUID;

public final class RegistrarMateriaResponseDTO {
    
	private UUID id;
	
    public RegistrarMateriaResponseDTO(UUID id) {
    	this.id = id;
    	}
    
    public UUID getId() { 
    	return id; 
    	}
    
}