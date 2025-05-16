package co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.dto.request.RegistrarAsistenciaRequestDTO.Estudiante;
import co.edu.uco.asisteuco.application.outputport.repository.AsistenciaRepository;
import co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.RegistrarAsistenciaUseCase;
import co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain.Asistencia;
import co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain.RegistrarAsistenciaResponseVO;
import co.edu.uco.asisteuco.application.usecase.estudiante.validator.ValidarQueEstudianteExista;
import co.edu.uco.asisteuco.application.usecase.sesion.validator.ValidarQueSesionExista;

@Service
public class RegistrarAsistenciaUseCaseImpl implements RegistrarAsistenciaUseCase {
	
	private ValidarQueEstudianteExista estudianteExiste;
	private ValidarQueSesionExista sesionExiste;
	private AsistenciaRepository asistenciaRepository;
	private RegistrarAsistenciaResponseVO resultado;
	
	public RegistrarAsistenciaUseCaseImpl(AsistenciaRepository asistenciaRepository, 
			ValidarQueEstudianteExista estudianteExiste, ValidarQueSesionExista sesionExiste) {
		this.asistenciaRepository = asistenciaRepository;
		this.estudianteExiste = estudianteExiste;
		this.sesionExiste = sesionExiste;
		resultado = new RegistrarAsistenciaResponseVO();
	}
	
	
	@Override
	public RegistrarAsistenciaResponseVO ejecutar(Asistencia dominio) {
		
		
		
		// 1. Validar integridad del objeto a nivel de tipo de datos, longitud, 
		// obligatoriedad, formato, rango, etc.}
		
		// 2. Las sesion debe existir
		if (resultado.isValidacionCorrecta()) {
			resultado.agregarMensajes(sesionExiste.validate(dominio.getSesion().getId()).getMensajes());
		}		
		
		// 3. El profesor debe existir
		if (resultado.isValidacionCorrecta()) {
		}		
		
		// 4. El grupo debe estar activo
		if (resultado.isValidacionCorrecta()) {
		}		
		
		// 5. El profesor debe estar asignado al grupo
		if (resultado.isValidacionCorrecta()) {
		}		
		
		// 6. No se puede tener una asistencia ya registrada para la misma sesion
		if (resultado.isValidacionCorrecta()) {
		}		
		
		// 7. La asistencia se debe registrar entre los plazos de tiempo establecidos
		if (resultado.isValidacionCorrecta()) {
		}		
		
		// 8. Validar que estudiantes sean consistentes para el registro de asistencia
		
		if (resultado.isValidacionCorrecta()) {
			registrarAsistenciaEstudiantes(dominio.getEstudiantes());
		}		
		
		//9. Retornar Resultado de la validacion
		return resultado;
	}
	
	private void registrarAsistenciaEstudiantes(List<Estudiante> estudiantes) {	
		
		for (var estudiante : estudiantes) {
			
			var registrarAsistenciaResponseEstudianteVO = new RegistrarAsistenciaResponseVO();
			
			//1. Validar que el estudiante exista
			ValidarQueEstudianteExista(estudiante.getId());
			
			//2. Validar que el estudiante este registrado en el grupo
			
			if (registrarAsistenciaResponseEstudianteVO.isValidacionCorrecta()) {
				//Validar que el estudiante este registrado en el grupo
			}
			
			//3. validar que el estudiante no tenga la materia cancelada por alguna novedad
			
			if (registrarAsistenciaResponseEstudianteVO.isValidacionCorrecta()) {
				//Validar que el estudiante no tenga la materia cancelada por alguna novedad
			}
			
			// 4. registrar la asistencia por cada estudiante
			if (registrarAsistenciaResponseEstudianteVO.isValidacionCorrecta()) {
				registrarAsistenciaEstudiante(estudiante);
			}	
			
			//5. Regiastrar la asistencia en la base de datos
			resultado.agregarMensajes(registrarAsistenciaResponseEstudianteVO.getMensajes());
			
		}
		
	}

	private void ValidarQueEstudianteExista(UUID idEstudiante) {	
		resultado.agregarMensajes(estudianteExiste.validate(idEstudiante).getMensajes());	
	}
	
	
	private void registrarAsistenciaEstudiante(Estudiante estudiante) {
		//1. Registrar asistencia
		
		if (!estudiante.isAsistio()) {
			//2. Enviar correo al estudiante que no asistió
		}
	}
}
