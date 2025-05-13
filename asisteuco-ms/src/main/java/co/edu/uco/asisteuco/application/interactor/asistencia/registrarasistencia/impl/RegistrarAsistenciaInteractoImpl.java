package co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.impl;

import org.springframework.stereotype.Service;

import co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.RegistrarAsistenciaInteractor;
import co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.dto.request.RegistrarAsistenciaRequestDTO;
import co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.dto.response.RegistrarAsistenciaResponseDTO;
import co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain.Asistencia;

@Service
public class RegistrarAsistenciaInteractoImpl implements RegistrarAsistenciaInteractor {
	
	@Override
	public RegistrarAsistenciaResponseDTO ejecutar(final RegistrarAsistenciaRequestDTO dto) {
			
		Asistencia asistencia = null; //Obtener asistencia con un maper desde el dto
		
		//Llamar al caso de uso
		
		// TODO Auto-generated method stub
		return null;
		}
}

