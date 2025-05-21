package co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.impl;

import org.springframework.stereotype.Service;

import co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.RegistrarAsistenciaInteractor;
import co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.dto.request.RegistrarAsistenciaRequestDTO;
import co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.dto.response.RegistrarAsistenciaResponseDTO;
import co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.RegistrarAsistenciaUseCase;
import co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain.Asistencia;
import jakarta.transaction.Transactional;

@Service
public class RegistrarAsistenciaInteractorImpl implements RegistrarAsistenciaInteractor {
	
	private RegistrarAsistenciaUseCase registrarAsistenciaUseCase;
	
	public RegistrarAsistenciaInteractorImpl(RegistrarAsistenciaUseCase registrarAsistenciaUseCase) {
		this.registrarAsistenciaUseCase = registrarAsistenciaUseCase;
	}
	
	//TODO: hacer mapper para convertir el dto a la entidad
	//TODO: Asegurarse en tener los mappers.
	@Override
	@Transactional
	public RegistrarAsistenciaResponseDTO ejecutar(final RegistrarAsistenciaRequestDTO dto) {
			
		Asistencia asistencia = null; //Obtener asistencia con un maper desde el dto
		
		//Llamar al caso de uso
		var resultadoVo = registrarAsistenciaUseCase.ejecutar(asistencia); 
		
		RegistrarAsistenciaResponseDTO responseDto = null; //Obtener response dto a partir de el responseVO que retorna el caso de uso.
		
		return responseDto;
		}
	//TODO Esto es para la conexion a la base de datos DriverManager.connect("jdbc:mysql://localhost:3306/your_database", "username", "password");
		// TODO Auto-generated method stub
		
		//return null;
}

