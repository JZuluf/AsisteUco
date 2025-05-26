package co.edu.uco.asisteuco.application.outputport.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "materia")
public class MateriaEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 50, unique = true)
    private String clave;

    @Column(nullable = false, length = 100)
    private String nombre;

    // Getters / setters
    
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