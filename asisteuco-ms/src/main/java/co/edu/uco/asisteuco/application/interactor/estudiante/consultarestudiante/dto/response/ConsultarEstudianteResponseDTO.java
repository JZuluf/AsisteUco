package co.edu.uco.asisteuco.application.interactor.estudiante.consultarestudiante.dto.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ConsultarEstudianteResponseDTO<T> {

    private boolean transaccionExitosa;
    private List<String> mensajes;
    private List<T> resultados;

    public ConsultarEstudianteResponseDTO() {
        this.transaccionExitosa = false;
        this.mensajes = new ArrayList<>();
        this.resultados = new ArrayList<>();
    }

    public ConsultarEstudianteResponseDTO(
        final boolean transaccionExitosa,
        final List<String> mensajes,
        final List<T> resultados
    ) {
        this.transaccionExitosa = transaccionExitosa;
        this.mensajes = mensajes != null ? mensajes : new ArrayList<>();
        this.resultados = resultados != null ? resultados : new ArrayList<>();
    }

    public static <T> ConsultarEstudianteResponseDTO<T> exitoso(
        final String mensaje,
        final List<T> resultados
    ) {
        Objects.requireNonNull(mensaje, "El mensaje no puede ser null");
        return new ConsultarEstudianteResponseDTO<>(
            true,
            List.of(mensaje),
            resultados
        );
    }

    /**
     * Crea una respuesta exitosa con múltiples mensajes y resultados.
     */
    public static <T> ConsultarEstudianteResponseDTO<T> exitoso(
        final List<String> mensajes,
        final List<T> resultados
    ) {
        Objects.requireNonNull(mensajes, "Los mensajes no pueden ser null");
        return new ConsultarEstudianteResponseDTO<>(
            true,
            mensajes,
            resultados
        );
    }

    /**
     * Crea una respuesta fallida con un único mensaje de error.
     */
    public static <T> ConsultarEstudianteResponseDTO<T> fallido(
        final String mensaje
    ) {
        Objects.requireNonNull(mensaje, "El mensaje no puede ser null");
        return new ConsultarEstudianteResponseDTO<>(
            false,
            List.of(mensaje),
            new ArrayList<>()
        );
    }

    /**
     * Crea una respuesta fallida con múltiples mensajes de error.
     */
    public static <T> ConsultarEstudianteResponseDTO<T> fallido(
        final List<String> mensajes
    ) {
        Objects.requireNonNull(mensajes, "Los mensajes no pueden ser null");
        return new ConsultarEstudianteResponseDTO<>(
            false,
            mensajes,
            new ArrayList<>()
        );
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
        this.mensajes = mensajes != null ? mensajes : new ArrayList<>();
    }

    public List<T> getResultados() {
        return resultados;
    }

    public void setResultados(final List<T> resultados) {
        this.resultados = resultados != null ? resultados : new ArrayList<>();
    }
}
