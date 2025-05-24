package co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase es un wrapper genérico para todas las respuestas de la API.
 * Proporciona una estructura consistente con una lista de mensajes y una lista de datos.
 * @param <T> El tipo de dato del objeto (DTO) que se incluirá en la respuesta.
 */
public final class RespuestaApi<T> {

    // Lista de mensajes para el cliente (ej: "Operación exitosa", "Error de validación", etc.)
    private List<String> mensajes;

    // Lista con los datos de la respuesta (ej: una lista de TipoIdentificacionDTO)
    private List<T> datos;

    public RespuestaApi() {
        // Se inicializan las listas para evitar NullPointerExceptions
        this.mensajes = new ArrayList<>();
        this.datos = new ArrayList<>();
    }
    
    // Getters y Setters públicos para que Jackson (el serializador de JSON) pueda acceder a ellos.

    public List<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }

    public List<T> getDatos() {
        return datos;
    }

    public void setDatos(List<T> datos) {
        this.datos = datos;
    }
}