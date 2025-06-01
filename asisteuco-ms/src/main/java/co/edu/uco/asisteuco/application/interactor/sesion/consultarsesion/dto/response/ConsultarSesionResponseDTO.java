package co.edu.uco.asisteuco.application.interactor.sesion.consultarsesion.dto.response;

import java.util.List;

public class ConsultarSesionResponseDTO<T> {
    
	private boolean transaccionExitosa;
    private List<T> resultados;
    private List<String> mensajes;

    // fábrica para éxitos
    public static <T> ConsultarSesionResponseDTO<T> exitoso(String msg, List<T> dtos) {
        var r = new ConsultarSesionResponseDTO<T>();
        r.transaccionExitosa = true;
        r.resultados = dtos;
        r.mensajes = List.of(msg);
        return r;
    }
    // fábrica para fallos
    public static <T> ConsultarSesionResponseDTO<T> fallido(String msg) {
        var r = new ConsultarSesionResponseDTO<T>();
        r.transaccionExitosa = false;
        r.resultados = List.of();
        r.mensajes = List.of(msg);
        return r;
    }
    
	public boolean isTransaccionExitosa() {
		return transaccionExitosa;
	}
	public void setTransaccionExitosa(boolean transaccionExitosa) {
		this.transaccionExitosa = transaccionExitosa;
	}
	public List<T> getResultados() {
		return resultados;
	}
	public void setResultados(List<T> resultados) {
		this.resultados = resultados;
	}
	public List<String> getMensajes() {
		return mensajes;
	}
	public void setMensajes(List<String> mensajes) {
		this.mensajes = mensajes;
	}
    
    
}
