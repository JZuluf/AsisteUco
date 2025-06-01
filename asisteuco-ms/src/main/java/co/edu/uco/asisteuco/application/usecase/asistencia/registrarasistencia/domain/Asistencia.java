package co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Asistencia {

    private final Sesion sesion;
    private final Profesor profesor;
    private final List<Estudiante> estudiantes;

    public Asistencia(final Sesion sesion, final Profesor profesor, final List<Estudiante> estudiantes) {
        this.sesion = Objects.requireNonNull(sesion, "La sesión no puede ser nula");
        this.profesor = Objects.requireNonNull(profesor, "El profesor no puede ser nulo");
        this.estudiantes = estudiantes != null ? List.copyOf(estudiantes) : Collections.emptyList();
    }

    public Sesion getSesion() {
        return sesion;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public List<Estudiante> getEstudiantes() {
        return Collections.unmodifiableList(estudiantes);
    }
}