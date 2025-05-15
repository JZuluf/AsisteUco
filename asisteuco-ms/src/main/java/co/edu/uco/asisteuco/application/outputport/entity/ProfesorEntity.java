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
@Table(name = "Profesor")

public class ProfesorEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID DEFAULT gen_random_uuid()")
	private UUID id;
	@ManyToOne
	@JoinColumn(name = "tipo_identificacion")
	private TipoIdentificacionEntity tipoIdentificacion;
	@Column(name = "numero_identificacion")
	private String numeroIdentificacion;
	@Column(name = "nombres_completos")
	private String nombresCompletos;
	
	public ProfesorEntity() {
		setDefaultId();
		setDefaultTipoIdentificacion();
		setDefaultNumeroIdentificacion();
		setDefaultNombresCompletos();
	}
	
	public void EstudianteEntity(final UUID id) {
		setId(id);
		setDefaultTipoIdentificacion();
		setDefaultNumeroIdentificacion();
		setDefaultNombresCompletos();
	}
	
	public ProfesorEntity(final UUID id,final TipoIdentificacionEntity tipoIdentificacion,final String numeroIdentificacion,final
			String nombresCompletos) {
		setId(id);
		setTipoIdentificacion(tipoIdentificacion);
		setNumeroIdentificacion(numeroIdentificacion);
		setNombresCompletos(nombresCompletos);
	}
	
	public UUID getId() {
		return id;
	}
	
	public void setId(final UUID id) {
		this.id = id;
	}
	
	private void setDefaultId() {
		this.id = UUID.randomUUID();
		setId(id);
	}
	
	public TipoIdentificacionEntity getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	
	public void setTipoIdentificacion(final TipoIdentificacionEntity tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	
	private void setDefaultTipoIdentificacion() {
		setTipoIdentificacion(new TipoIdentificacionEntity());
	}
	
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	
	public void setNumeroIdentificacion(final String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
	
	private void setDefaultNumeroIdentificacion() {
		setNumeroIdentificacion("");
	}
	
	public String getNombresCompletos() {
		return nombresCompletos;
	}
	
	public void setNombresCompletos(final String nombresCompletos) {
		this.nombresCompletos = nombresCompletos;
	}
	
	private void setDefaultNombresCompletos() {
		setNombresCompletos("");
	}

}
