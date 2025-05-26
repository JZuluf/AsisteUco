package co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.dto.request;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public final class RegistrarAsistenciaRequestDTO {

    @NotNull(message = "La sesión no puede ser nula")
    private UUID sesion;

    @NotNull(message = "El profesor no puede ser nulo")
    private UUID profesor;

    @NotEmpty(message = "Debe registrar al menos un estudiante")
    @Valid
    private List<Estudiante> estudiantes;

    public RegistrarAsistenciaRequestDTO() {
        super();
    }

    public UUID getSesion() {
        return sesion;
    }

    public void setSesion(final UUID sesion) {
        this.sesion = sesion;
    }

    public UUID getProfesor() {
        return profesor;
    }

    public void setProfesor(final UUID profesor) {
        this.profesor = profesor;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public static class Estudiante {

        @NotNull(message = "El ID del estudiante no puede ser nulo")
        private UUID id;

        private boolean asistio;

        public Estudiante() {
            super();
        }

        public Estudiante(final UUID id, final boolean asistio) {
            this.id = id;
            this.asistio = asistio;
        }

        public UUID getId() {
            return id;
        }

        public void setId(final UUID id) {
            this.id = id;
        }

        public boolean isAsistio() {
            return asistio;
        }

        public void setAsistio(final boolean asistio) {
            this.asistio = asistio;
        }
    }
}
