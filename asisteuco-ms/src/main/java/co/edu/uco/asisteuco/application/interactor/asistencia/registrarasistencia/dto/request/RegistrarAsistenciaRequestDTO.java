package co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.dto.request;

import java.util.List;
import java.util.UUID;

public final class RegistrarAsistenciaRequestDTO {

	private UUID sesion;
	private UUID profesor;
	private List<Estudiante> estudiantes;
	
	public RegistrarAsistenciaRequestDTO() {
		super();
	}
	
	private UUID getSesion() {
		return sesion;
	}

	private void setSesion(final UUID sesion) {
		this.sesion = sesion;
	}

	private UUID getProfesor() {
		return profesor;
	}

	private void setProfesor(final UUID profesor) {
		this.profesor = profesor;
	}

	private List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	private void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	
	public class Estudiante{
		private UUID id;
		private boolean asistio;
		
		
		public Estudiante() {
			setDefaultId();
			setDefaultAsistio();

		}
		
		public Estudiante(final UUID id,final boolean asistio) {
			setId(id);
			setAsistio(asistio);
		}
		
		
		public UUID getId() {
			return id;
		}
		
		public void setId(final UUID id) {
			this.id = id;
		}
		
		private void setDefaultId() {
			UUID defaultValue = null;
			setId(defaultValue);
		}
		
		public boolean isAsistio() {
			return asistio;
		}
		
		public void setAsistio(final boolean asistio) {
			this.asistio = asistio;
		}
		
		private void setDefaultAsistio() {
			boolean defaultValue = false;
			setAsistio(defaultValue);
		}
		
		//TODO: LLEgo la hora de la verdad: saber si efectivamente el asistio es por defecto o un valor que me enviaron
		
	}
	
	
}
