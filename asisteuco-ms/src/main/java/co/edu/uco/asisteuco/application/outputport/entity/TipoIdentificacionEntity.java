package co.edu.uco.asisteuco.application.outputport.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//TODO:Cuidado con dejar quemado el literal
@Table(name = "TipoIdentificacion")
public final class TipoIdentificacionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID id;
	//TODO:Cuidado con dejar quemado el literal
	@Column(name = "clave")
	private String clave;
	//TODO:Cuidado con dejar quemado el literal
	@Column(name = "nombre")
	private String nombre;

	public TipoIdentificacionEntity() {
		setDefaultId();
		setDefaultClave();
		setDefaultNombre();
	}
	
	public TipoIdentificacionEntity(final UUID id) {
		setId(id);
		setDefaultClave();
		setDefaultNombre();
	}

	public TipoIdentificacionEntity(final UUID id,final String clave,final String nombre) {
		setId(id);
		setClave(clave);
		setNombre(nombre);
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}
	
	private void setDefaultId() {
		//TODO: Obtener el valor por defecto de la base de datos o algun parametro
		UUID defaultValue = null;
		setId(defaultValue);
	}

	public String getClave() {
		return clave;
	}

	public void setClave(final String clave) {
		//TODO:Cuidado con la limpieza de datos para evitar datos nulos
		this.clave = clave;
	}
	
	private void setDefaultClave() {
		//TODO: Obtener el valor por defecto de la base de datos o algun parametro
		var defaultValue = "";
		setClave(defaultValue);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		// TODO:Cuidado con la limpieza de datos para evitar datos nulos
		this.nombre = nombre;
	}

	private void setDefaultNombre() {
		//TODO: Obtener el valor por defecto de la base de datos o algun parametro
		var defaultValue = "";
		setNombre(defaultValue);
	}
}
