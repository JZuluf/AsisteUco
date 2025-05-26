package co.edu.uco.asisteuco.application.interactor.materia.consultarmateria.dto.response;

import java.util.Collections;
import java.util.List;

public final class ConsultarMateriaResponseDTO<T> {
    private boolean transaccionExitosa;
    private List<String> mensajes;
    private List<T> resultados;

    public ConsultarMateriaResponseDTO() { }
    public ConsultarMateriaResponseDTO(boolean exito, List<String> msgs, List<T> res) {
        this.transaccionExitosa = exito;
        this.mensajes = msgs;
        this.resultados = res;
    }
    public static <T> ConsultarMateriaResponseDTO<T> exitoso(String msg, List<T> res) {
        return new ConsultarMateriaResponseDTO<>(true, List.of(msg), res);
    }
    public static <T> ConsultarMateriaResponseDTO<T> fallido(String msg) {
        return new ConsultarMateriaResponseDTO<>(false, List.of(msg), Collections.emptyList());
    }
	public boolean isTransaccionExitosa() {
		return transaccionExitosa;
	}
	public void setTransaccionExitosa(boolean transaccionExitosa) {
		this.transaccionExitosa = transaccionExitosa;
	}
	public List<String> getMensajes() {
		return mensajes;
	}
	public void setMensajes(List<String> mensajes) {
		this.mensajes = mensajes;
	}
	public List<T> getResultados() {
		return resultados;
	}
	public void setResultados(List<T> resultados) {
		this.resultados = resultados;
	}


    
}

