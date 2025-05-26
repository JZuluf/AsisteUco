package co.edu.uco.asisteuco.application.interactor.materia.registrarmateria.dto.request;

public final class RegistrarMateriaRequestDTO {
    
	private String clave;
    private String nombre;
    
    public String getClave() { 
    	return clave;
    	}
    
    public void setClave(String clave) {
    	this.clave = clave; 
    	}
    
    public String getNombre() { 
    	return nombre;
    	}
    
    public void setNombre(String nombre) {
    	this.nombre = nombre; 
    	}
}