package co.edu.uco.asisteuco.application.interactor.grupo.consultargrupo.dto.response;

import java.util.List;

public final class ConsultarGrupoResponseDTO<T> {
    private boolean transaccionExitosa;
    private List<String> mensajes;
    private List<T> resultados;

    public ConsultarGrupoResponseDTO() { }

    private ConsultarGrupoResponseDTO(boolean ok, List<String> msgs, List<T> res) {
        this.transaccionExitosa = ok;
        this.mensajes = msgs;
        this.resultados = res;
    }

    public static <T> ConsultarGrupoResponseDTO<T> exitoso(List<String> msgs, List<T> res) {
        return new ConsultarGrupoResponseDTO<>(true, msgs, res);
    }

    public static <T> ConsultarGrupoResponseDTO<T> fallido(String msg) {
        return new ConsultarGrupoResponseDTO<>(false, List.of(msg), List.of());
    }

    public boolean isTransaccionExitosa() {
        return transaccionExitosa;
    }
    public List<String> getMensajes() {
        return mensajes;
    }
    public List<T> getResultados() {
        return resultados;
    }
}
