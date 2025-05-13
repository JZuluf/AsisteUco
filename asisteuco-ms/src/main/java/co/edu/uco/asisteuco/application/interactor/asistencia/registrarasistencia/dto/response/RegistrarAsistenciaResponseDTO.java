package co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.dto.response;

import java.util.List;

public final class RegistrarAsistenciaResponseDTO {

	private boolean transaccionExitosa;
	private List<String> mensajes;
	
	//TODO: Transaccion exitosa fue el valor por defecto o el colocado intencionalmente?
	//TODO: ¿Quien genera el response cliente o backend?
	
	public RegistrarAsistenciaResponseDTO() {
		super();
	}
	
	public boolean isTransaccionExitosa() {
		return transaccionExitosa;
	}
	public void setTransaccionExitosa(final boolean transaccionExitosa) {
		this.transaccionExitosa = transaccionExitosa;
	}
	public List<String> getMensajes() {
		return mensajes;
	}
	public void setMensajes(final List<String> mensajes) {
		this.mensajes = mensajes;
	}
	
	
}
