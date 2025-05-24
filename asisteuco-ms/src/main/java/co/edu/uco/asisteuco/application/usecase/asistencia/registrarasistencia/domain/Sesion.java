package co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain;

import java.util.UUID;

public final class Sesion {
    
    private UUID id;

    public Sesion(UUID id) {
        this.setId(id);
    }
    
    public UUID getId() {
        return id;
    }

    private void setId(final UUID id) {
        this.id = id; 
    }
}