package co.edu.uco.asisteuco.application.interactor.estudiante.consultarestudiante.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uco.asisteuco.application.interactor.estudiante.consultarestudiante.ConsultarEstudianteInteractor;
import co.edu.uco.asisteuco.application.interactor.estudiante.consultarestudiante.dto.request.ConsultarEstudianteRequestDTO;
import co.edu.uco.asisteuco.application.interactor.estudiante.consultarestudiante.dto.response.ConsultarEstudianteResponseDTO;
import co.edu.uco.asisteuco.application.outputport.dto.EstudianteDTO;
import co.edu.uco.asisteuco.application.outputport.entity.EstudianteEntity;
import co.edu.uco.asisteuco.application.outputport.repository.EstudianteRepository;

/**
 * Interactor para consultar estudiantes por distintos filtros.
 * Dado que el repositorio solo expone findAll() y findById(),
 * utilizamos filtrado en memoria para el resto de criterios.
 */
@Service
public class ConsultarEstudianteInteractorImpl implements ConsultarEstudianteInteractor {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public ConsultarEstudianteInteractorImpl(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public ConsultarEstudianteResponseDTO<EstudianteDTO> ejecutar(ConsultarEstudianteRequestDTO request) {
        try {
            UUID id = request.getId();
            UUID tipoId = request.getTipoIdentificacion();
            String numero = request.getNumeroIdentificacion();
            String nombres = request.getNombresCompletos();

            List<EstudianteEntity> entities;

            // 1) Búsqueda por ID
            if (id != null) {
                entities = estudianteRepository.findById(id)
                    .map(List::of)
                    .orElseGet(List::of);

            // 2) Para los demás criterios, obtenemos todos y filtramos en memoria
            } else {
                List<EstudianteEntity> todos = estudianteRepository.findAll();

                entities = todos.stream()
                    .filter(e -> {
                        boolean matches = true;
                        if (tipoId != null) {
                            matches &= tipoId.equals(e.getTipoIdentificacion().getId());
                        }
                        if (numero != null && !numero.isBlank()) {
                            matches &= numero.equals(e.getNumeroIdentificacion());
                        }
                        if (nombres != null && !nombres.isBlank()) {
                            matches &= e.getNombresCompletos() != null &&
                                       e.getNombresCompletos().toLowerCase()
                                         .contains(nombres.toLowerCase());
                        }
                        return matches;
                    })
                    .collect(Collectors.toList());
            }

            if (entities.isEmpty()) {
                return ConsultarEstudianteResponseDTO.fallido(
                    "No se encontraron estudiantes con esos criterios.");
            }

            List<EstudianteDTO> resultadosDTO = entities.stream()
                .map(e -> {
                    EstudianteDTO dto = new EstudianteDTO();
                    dto.setUuid(e.getId());
                    dto.setTipoIdentificacion(e.getTipoIdentificacion().getId());
                    dto.setNumeroIdentificacion(e.getNumeroIdentificacion());
                    dto.setNombresCompletos(e.getNombresCompletos());
                    return dto;
                })
                .collect(Collectors.toList());

            return ConsultarEstudianteResponseDTO.exitoso(
                List.of("Consulta de estudiantes realizada exitosamente."),
                resultadosDTO
            );
        } catch (Exception ex) {
            return ConsultarEstudianteResponseDTO.fallido(
                "Error al consultar estudiantes: " + ex.getMessage()
            );
        }
    }
}