package co.edu.uco.asisteuco.application.usecase.validator;

import java.util.ArrayList;
import java.util.List;

public class ValidationResultVO {

    private List<String> mensajes;

    public ValidationResultVO() {
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
        if (mensajes != null) {
            this.mensajes.addAll(mensajes);
        }
    }

    public void agregarMensaje(String mensaje) {
        // Asegurar que no sea nulo o vacío
        if (mensaje != null && !mensaje.trim().isEmpty()) {
            this.mensajes.add(mensaje);
        }
    }

    public boolean isValidacionCorrecta() {
        return mensajes.isEmpty();
    }
}
