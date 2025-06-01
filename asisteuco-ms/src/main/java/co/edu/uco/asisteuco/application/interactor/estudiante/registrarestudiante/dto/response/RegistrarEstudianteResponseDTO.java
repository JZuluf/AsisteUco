package co.edu.uco.asisteuco.application.interactor.estudiante.registrarestudiante.dto.response;

import java.util.List;

public final class RegistrarEstudianteResponseDTO {

    private boolean transaccionExitosa;
    private List<String> mensajes;

    public RegistrarEstudianteResponseDTO() {
        super();
    }

    public RegistrarEstudianteResponseDTO(boolean transaccionExitosa, List<String> mensajes) {
        this.transaccionExitosa = transaccionExitosa;
        this.mensajes = mensajes;
    }

    public static RegistrarEstudianteResponseDTO exitoso(String mensaje) {
        return new RegistrarEstudianteResponseDTO(true, List.of(mensaje));
    }

    public static RegistrarEstudianteResponseDTO exitoso(List<String> mensajes) {
        return new RegistrarEstudianteResponseDTO(true, mensajes);
    }

    public static RegistrarEstudianteResponseDTO fallido(String mensaje) {
        return new RegistrarEstudianteResponseDTO(false, List.of(mensaje));
    }

    public static RegistrarEstudianteResponseDTO fallido(List<String> mensajes) {
        return new RegistrarEstudianteResponseDTO(false, mensajes);
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
