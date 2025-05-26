package co.edu.uco.asisteuco.application.interactor.tipoidentificacion.registrartipoidentificacion.dto.response;

import java.util.List;

public final class RegistrarTipoIdentificacionResponseDTO {

    private boolean transaccionExitosa;
    private List<String> mensajes;

    public RegistrarTipoIdentificacionResponseDTO() {
        super();
    }

    public RegistrarTipoIdentificacionResponseDTO(boolean transaccionExitosa, List<String> mensajes) {
        this.transaccionExitosa = transaccionExitosa;
        this.mensajes = mensajes;
    }

    public static RegistrarTipoIdentificacionResponseDTO exitoso(String mensaje) {
        return new RegistrarTipoIdentificacionResponseDTO(true, List.of(mensaje));
    }

    public static RegistrarTipoIdentificacionResponseDTO exitoso(List<String> mensajes) {
        return new RegistrarTipoIdentificacionResponseDTO(true, mensajes);
    }

    public static RegistrarTipoIdentificacionResponseDTO fallido(String mensaje) {
        return new RegistrarTipoIdentificacionResponseDTO(false, List.of(mensaje));
    }

    public static RegistrarTipoIdentificacionResponseDTO fallido(List<String> mensajes) {
        return new RegistrarTipoIdentificacionResponseDTO(false, mensajes);
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
