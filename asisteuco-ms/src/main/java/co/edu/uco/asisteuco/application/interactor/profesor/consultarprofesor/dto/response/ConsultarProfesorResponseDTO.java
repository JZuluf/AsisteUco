package co.edu.uco.asisteuco.application.interactor.profesor.consultarprofesor.dto.response;

import java.util.List;

public final class ConsultarProfesorResponseDTO<T> {
    private boolean transaccionExitosa;
    private List<String> mensajes;
    private List<T> resultados;

    public ConsultarProfesorResponseDTO() { }

    public ConsultarProfesorResponseDTO(
        boolean transaccionExitosa,
        List<String> mensajes,
        List<T> resultados
    ) {
        this.transaccionExitosa = transaccionExitosa;
        this.mensajes = mensajes;
        this.resultados = resultados;
    }

    // Métodos de fábrica:
    public static <T> ConsultarProfesorResponseDTO<T> exitoso(
        List<String> mensajes,
        List<T> resultados
    ) {
        return new ConsultarProfesorResponseDTO<>(true, mensajes, resultados);
    }

    public static <T> ConsultarProfesorResponseDTO<T> fallido(
        List<String> mensajes
    ) {
        return new ConsultarProfesorResponseDTO<>(false, mensajes, List.of());
    }

    // Getters / setters
    public boolean isTransaccionExitosa() { return transaccionExitosa; }
    public void setTransaccionExitosa(boolean transaccionExitosa) {
        this.transaccionExitosa = transaccionExitosa;
    }

    public List<String> getMensajes() { return mensajes; }
    public void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }

    public List<T> getResultados() { return resultados; }
    public void setResultados(List<T> resultados) {
        this.resultados = resultados;
    }
}
