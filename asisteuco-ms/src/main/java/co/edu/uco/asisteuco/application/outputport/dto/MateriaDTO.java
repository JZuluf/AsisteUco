package co.edu.uco.asisteuco.application.outputport.dto;

import java.util.UUID;

public class MateriaDTO {
    private UUID id;
    private String clave;
    private String nombre;
    
    public UUID getId() {
    	return id;
    	}
    
    public void setId(UUID id) {
    	this.id = id;
    	}
    
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