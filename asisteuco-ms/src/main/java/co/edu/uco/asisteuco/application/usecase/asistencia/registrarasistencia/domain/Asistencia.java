package co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain;

import java.util.List;

public final class Asistencia {

	private Sesion sesion;
	private Profesor profesor;
	private List<Estudiante> estudiantes;
	
	public Asistencia(final Sesion sesion, final Profesor profesor, final List<Estudiante> estudiantes) {
		setSesion(sesion);
		setProfesor(profesor);
		setEstudiantes(estudiantes);
	}

	public Sesion getSesion() {
		return sesion;
	}

	private void setSesion(final Sesion sesion) {
		this.sesion = sesion;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	private void setProfesor(final Profesor profesor) {
		this.profesor = profesor;
	}

	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	private void setEstudiantes(final List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}
	
	
	
}
