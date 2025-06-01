package co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain;

import java.util.ArrayList;
import java.util.List;

public class RegistrarAsistenciaResponseVO {
	
    private List<String> mensajes;
	
	public RegistrarAsistenciaResponseVO() {
		this.mensajes = new ArrayList<>();
	}

	public List<String> getMensajes() {
		return mensajes;
	}

	private void setMensajes(List<String> mensajes) {
		if (mensajes == null) {
			this.mensajes = new ArrayList<>();
		} else {
			this.mensajes = mensajes;
		}
	}
	
	public void agregarMensajes(List<String> mensajes) {
		if (mensajes != null && !mensajes.isEmpty()) {
			getMensajes().addAll(mensajes);
		}
	}
	
	public void agregarMensaje(String mensaje) {
		if (mensaje != null && !mensaje.trim().isEmpty()) {
			getMensajes().add(mensaje);
		}
	}

	public boolean isValidacionCorrecta() {
		return getMensajes().isEmpty();
	}
	
}
