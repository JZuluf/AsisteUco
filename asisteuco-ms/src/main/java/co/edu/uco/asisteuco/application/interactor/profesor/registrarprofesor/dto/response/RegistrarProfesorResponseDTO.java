package co.edu.uco.asisteuco.application.interactor.profesor.registrarprofesor.dto.response;

import java.util.List;

public final class RegistrarProfesorResponseDTO {
    private boolean transaccionExitosa;
    private List<String> mensajes;

    public boolean isTransaccionExitosa() { return transaccionExitosa; }
    public void setTransaccionExitosa(boolean transaccionExitosa) {
        this.transaccionExitosa = transaccionExitosa;
    }
    public List<String> getMensajes() { return mensajes; }
    public void setMensajes(List<String> mensajes) { this.mensajes = mensajes; }
}