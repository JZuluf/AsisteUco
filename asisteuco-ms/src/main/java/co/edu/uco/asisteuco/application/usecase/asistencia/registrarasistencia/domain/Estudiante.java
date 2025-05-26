package co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain;

import java.util.UUID;

public final class Estudiante {

    private final UUID id;
    private String nombresCompletos;
    private boolean asistio;

    public Estudiante(final UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del estudiante no puede ser nulo");
        }
        this.id = id;
        this.nombresCompletos = "";
        this.asistio = false;
    }

    public Estudiante(final UUID id, final String nombresCompletos, final boolean asistio) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del estudiante no puede ser nulo");
        }
        this.id = id;
        this.nombresCompletos = nombresCompletos != null ? nombresCompletos : "";
        this.asistio = asistio;
    }

    public UUID getId() {
        return id;
    }

    public String getNombresCompletos() {
        return nombresCompletos;
    }

    public void setNombresCompletos(String nombresCompletos) {
        this.nombresCompletos = nombresCompletos;
    }

    public boolean isAsistio() {
        return asistio;
    }

    public void setAsistio(boolean asistio) {
        this.asistio = asistio;
    }
}
