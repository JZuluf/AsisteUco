package co.edu.uco.asisteuco.application.usecase.estudiante.validator;

import java.util.UUID;

import org.springframework.stereotype.Service;

import co.edu.uco.asisteuco.application.outputport.repository.EstudianteRepository;
import co.edu.uco.asisteuco.application.usecase.validator.ValidationResultVO;
import co.edu.uco.asisteuco.application.usecase.validator.Validator;


@Service
public class ValidarQueEstudianteExista implements Validator<UUID,  ValidationResultVO>{
	
	private EstudianteRepository estudianteRepository;
	
	public ValidarQueEstudianteExista(EstudianteRepository estudianteRepository) {
		this.estudianteRepository = estudianteRepository;
	}
	
	@Override
	public ValidationResultVO validate(UUID id) {
		
		var resultadoValidacion = new ValidationResultVO();
		
		if (!estudianteRepository.existsById(id)) {
			//TODO: No se pueden quemar los mensajes. Deben estar en el catalogo de mensajes.
			resultadoValidacion.agregarMensaje("No existe un estudiante con el id: " + id);
		}
		
		return resultadoValidacion;
	}

}
