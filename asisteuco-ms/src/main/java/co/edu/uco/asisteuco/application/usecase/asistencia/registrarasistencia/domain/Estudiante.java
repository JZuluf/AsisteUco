package co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain;

import java.util.UUID;

public final class Estudiante {

	private UUID id;
	private boolean asistio;
	
	public Estudiante(final UUID id,final boolean asistio) {
		setId(id);
		setAsistio(asistio);
	}
	
	public UUID getId() {
		return id;
	}
	
	private void setId(final UUID id) {
		this.id = id;
	}
	
	public boolean isAsistio() {
		return asistio;
	}
	
	private void setAsistio(final boolean asistio) {
		this.asistio = asistio;
	}
	
	
}
