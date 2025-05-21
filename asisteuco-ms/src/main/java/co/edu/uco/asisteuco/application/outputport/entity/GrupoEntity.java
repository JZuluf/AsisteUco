package co.edu.uco.asisteuco.application.outputport.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Grupo")

public class GrupoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID DEFAULT gen_random_uuid()")
	private UUID id;
	@ManyToOne
	@JoinColumn(name = "profesor_id")
	private ProfesorEntity profesor;
	@JoinColumn(name = "materia_id")
	private String materia;
	@Column(name = "cantidad_estudiantes")
	private String cantidadEstudiantes;
	
	public UUID getId() {
		return id;
	}
	
	public void setId(final UUID id) {
		this.id = id;
	}
	
	public void setDefaultId() {
		this.id = UUID.randomUUID();
		setId(id);
	}
	
	public ProfesorEntity getProfesor() {
		return profesor;
	}
	
	public void setProfesor(final ProfesorEntity profesor) {
		this.profesor = profesor;
	}
	
	public void setDefaultProfesor() {
		this.profesor = new ProfesorEntity();
		setProfesor(profesor);
	}
	
	public String getMateria() {
		return materia;
	}
	
	public void setMateria(final String materia) {
		this.materia = materia;
	}
	
	public void setDefaultMateria() {
		this.materia = "";
		setMateria(materia);
	}
	
	public String getCantidadEstudiantes() {
		return cantidadEstudiantes;
	}
	
	public void setCantidadEstudiantes(final String cantidadEstudiantes) {
		this.cantidadEstudiantes = cantidadEstudiantes;
	}
	
	public void setDefaultCantidadEstudiantes() {
		this.cantidadEstudiantes = "";
		setCantidadEstudiantes(cantidadEstudiantes);
	}
	
	
}
