package co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.asistencia;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.RegistrarAsistenciaInteractor;
import co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.dto.request.RegistrarAsistenciaRequestDTO;

@RestController
@RequestMapping("/api/v1/asistencias")
public class AsistenciaController {

	private RegistrarAsistenciaInteractor registrarAsistenciaInteractor;
	
	public AsistenciaController(RegistrarAsistenciaInteractor registrarAsistenciaInteractor) {
		this.registrarAsistenciaInteractor = registrarAsistenciaInteractor;
	}
	
	//Quien define que retorna y como es tarea de todos
	//valide objeto response entity
	
	@PostMapping 
	public String registrarAsistencia(@RequestBody RegistrarAsistenciaRequestDTO dto) {
		registrarAsistenciaInteractor.ejecutar(dto);
		return "Exito";//TODO arreglar esta chambonada
	} 
	
}
