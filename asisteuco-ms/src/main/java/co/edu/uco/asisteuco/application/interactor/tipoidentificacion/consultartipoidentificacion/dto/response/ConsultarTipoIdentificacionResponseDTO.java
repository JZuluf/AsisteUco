package co.edu.uco.asisteuco.application.interactor.tipoidentificacion.consultartipoidentificacion.dto.response;

import java.util.List;

public final class ConsultarTipoIdentificacionResponseDTO<T> {

    private boolean transaccionExitosa;
    private List<String> mensajes;
    private List<T> resultados;

    public ConsultarTipoIdentificacionResponseDTO() {
        super();
    }

    public ConsultarTipoIdentificacionResponseDTO(boolean transaccionExitosa, List<String> mensajes, List<T> resultados) {
        this.transaccionExitosa = transaccionExitosa;
        this.mensajes = mensajes;
        this.resultados = resultados;
    }

    public static <T> ConsultarTipoIdentificacionResponseDTO<T> exitoso(String mensaje, List<T> resultados) {
        return new ConsultarTipoIdentificacionResponseDTO<>(true, List.of(mensaje), resultados);
    }

    public static <T> ConsultarTipoIdentificacionResponseDTO<T> exitoso(List<String> mensajes, List<T> resultados) {
        return new ConsultarTipoIdentificacionResponseDTO<>(true, mensajes, resultados);
    }

    public static <T> ConsultarTipoIdentificacionResponseDTO<T> fallido(String mensaje) {
        return new ConsultarTipoIdentificacionResponseDTO<>(false, List.of(mensaje), List.of());
    }

    public static <T> ConsultarTipoIdentificacionResponseDTO<T> fallido(List<String> mensajes) {
        return new ConsultarTipoIdentificacionResponseDTO<>(false, mensajes, List.of());
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

    public List<T> getResultados() {
        return resultados;
    }

    public void setResultados(List<T> resultados) {
        this.resultados = resultados;
    }
}

