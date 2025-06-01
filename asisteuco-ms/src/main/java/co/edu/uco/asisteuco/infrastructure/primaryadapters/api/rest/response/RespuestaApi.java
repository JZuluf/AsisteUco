package co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase es un wrapper genérico para todas las respuestas de la API.
 * Proporciona una estructura consistente con:
 *  - un indicador de éxito
 *  - una lista de mensajes
 *  - una lista de datos
 *
 * @param <T> El tipo de dato del objeto (DTO) que se incluirá en la respuesta.
 */
public final class RespuestaApi<T> {

    private boolean transaccionExitosa;
    private List<String> mensajes;
    private List<T> datos;

    public RespuestaApi() {
        this.transaccionExitosa = true;
        this.mensajes = new ArrayList<>();
        this.datos = new ArrayList<>();
    }

    // --- transaccionExitosa ---
    public boolean isTransaccionExitosa() {
        return transaccionExitosa;
    }

    public void setTransaccionExitosa(boolean transaccionExitosa) {
        this.transaccionExitosa = transaccionExitosa;
    }

    // --- mensajes ---
    public List<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }

    // --- datos ---
    public List<T> getDatos() {
        return datos;
    }

    public void setDatos(List<T> datos) {
        this.datos = datos;
    }
}
