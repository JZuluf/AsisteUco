package co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain;

import java.util.UUID;

public final class Profesor {
	
	private UUID id;

	public Profesor (UUID id) {
		setId(id);
	}
	
	public UUID getId() {
		return id;
	}

	private void setId(final UUID id) {
		setId(id);
	}
	
}
