package co.edu.uco.asisteuco.application.interactor.estudiante.registrarestudiante.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uco.asisteuco.application.interactor.estudiante.registrarestudiante.RegistrarEstudianteInteractor;
import co.edu.uco.asisteuco.application.interactor.estudiante.registrarestudiante.dto.request.RegistrarEstudianteRequestDTO;
import co.edu.uco.asisteuco.application.interactor.estudiante.registrarestudiante.dto.response.RegistrarEstudianteResponseDTO;
import co.edu.uco.asisteuco.application.outputport.entity.EstudianteEntity;
import co.edu.uco.asisteuco.application.outputport.entity.TipoIdentificacionEntity;
import co.edu.uco.asisteuco.application.outputport.repository.EstudianteRepository;



@Service
public class RegistrarEstudianteInteractorImpl implements RegistrarEstudianteInteractor {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public RegistrarEstudianteInteractorImpl(
        EstudianteRepository estudianteRepository
    ) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public RegistrarEstudianteResponseDTO ejecutar(RegistrarEstudianteRequestDTO request) {
        // Convertir el DTO de request a entidad
        EstudianteEntity entity = new EstudianteEntity();
        
        // Asignar el tipo de identificación como FK
        TipoIdentificacionEntity tipo = new TipoIdentificacionEntity();
        tipo.setId(request.getTipoIdentificacion());
        entity.setTipoIdentificacion(tipo);

        // Asignar demás campos
        entity.setNumeroIdentificacion(request.getNumeroIdentificacion());
        entity.setNombresCompletos(request.getNombresCompletos());
        entity.setEmail(request.getEmail());

        // Guardar la entidad en la base de datos
        EstudianteEntity savedEntity = estudianteRepository.save(entity);

        // Crear response DTO y devolver
        RegistrarEstudianteResponseDTO response = new RegistrarEstudianteResponseDTO();
        response.setTransaccionExitosa(true);
        response.setMensajes(List.of("Estudiante registrado exitosamente."));

        return response;
    }
}