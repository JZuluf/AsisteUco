package co.edu.uco.asisteuco.application.usecase.sesion.validator;

import java.util.UUID;

import org.springframework.stereotype.Service;

import co.edu.uco.asisteuco.application.outputport.repository.SesionRepository;
import co.edu.uco.asisteuco.application.usecase.validator.ValidationResultVO;
import co.edu.uco.asisteuco.application.usecase.validator.Validator;

@Service
public class ValidarQueSesionExista implements Validator<UUID, ValidationResultVO> {
    
    private final SesionRepository sesionRepository;
    
    // Constructor corregido para inyectar correctamente sesionRepository
    public ValidarQueSesionExista(SesionRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }
    
    @Override
    public ValidationResultVO validate(UUID id) {
        var resultadoValidacion = new ValidationResultVO();
        
        if (!sesionRepository.existsById(id)) {
            // TODO: Los mensajes deberían obtenerse de un catálogo, no estar hardcodeados
            resultadoValidacion.agregarMensaje("No existe una sesión con el id: " + id);
        }
        
        return resultadoValidacion;
    }
}
