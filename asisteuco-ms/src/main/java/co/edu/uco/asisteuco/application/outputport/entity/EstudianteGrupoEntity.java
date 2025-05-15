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
@Table(name = "EstudianteGrupo")

public class EstudianteGrupoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID DEFAULT gen_random_uuid()")
	private UUID id;
	@ManyToOne
	@JoinColumn(name = "grupo_id")
	private grupoEntity grupo;
	@ManyToOne
	@JoinColumn(name = "estudiante_id")
	private EstudianteEntity estudiante;
	
	
	
	public EstudianteGrupoEntity() {
		setDefaultId();
		setDefaultGrupo();
		setDefaultEstudiante();
	}
	
	public EstudianteGrupoEntity(UUID id) {
		setId(id);
		setDefaultGrupo();
		setDefaultEstudiante();
	}

	public EstudianteGrupoEntity(final UUID id,final grupoEntity grupo,final EstudianteEntity estudiante) {
		setId(id);
		setGrupo(grupo);
		setEstudiante(estudiante);
	}

	private UUID getId() {
		return id;
	}
	
	private void setId(final UUID id) {
		this.id = id;
	}
	
	private void setDefaultId() {
		this.id = UUID.randomUUID();
		setId(id);
	}
	
	private grupoEntity getGrupo() {
		return grupo;
	}
	
	private void setGrupo(final grupoEntity grupo) {
		this.grupo = grupo;
	}
	
	private void setDefaultGrupo() {
		this.grupo = new grupoEntity();
		setGrupo(grupo);
	}
	
	private EstudianteEntity getEstudiante() {
		return estudiante;
	}
	
	private void setEstudiante(final EstudianteEntity estudiante) {
		this.estudiante = estudiante;
	}
	
	private void setDefaultEstudiante() {
		this.estudiante = new EstudianteEntity();
		setEstudiante(estudiante);
	}
}
