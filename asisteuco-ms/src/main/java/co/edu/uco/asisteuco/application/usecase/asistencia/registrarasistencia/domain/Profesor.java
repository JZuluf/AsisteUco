package co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain;

import java.util.UUID;

public final class Profesor {
    
    private UUID id;
    private String nombresCompletos;
    private String numeroIdentificacion;
    private UUID tipoIdentificacion;

    public Profesor(UUID id, String nombresCompletos, String numeroIdentificacion, UUID tipoIdentificacion) {
        setId(id);
        setNombresCompletos(nombresCompletos);
        setNumeroIdentificacion(numeroIdentificacion);
        setTipoIdentificacion(tipoIdentificacion);
    }
    
    public UUID getId() {
        return id;
    }

    private void setId(final UUID id) {
        this.id = id;
    }

    public String getNombresCompletos() {
        return nombresCompletos;
    }

    public void setNombresCompletos(String nombresCompletos) {
        this.nombresCompletos = nombresCompletos;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public UUID getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(UUID tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }
}

