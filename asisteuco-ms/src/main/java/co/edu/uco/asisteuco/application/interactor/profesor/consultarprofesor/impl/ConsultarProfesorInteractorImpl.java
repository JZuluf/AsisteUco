package co.edu.uco.asisteuco.application.interactor.profesor.consultarprofesor.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import co.edu.uco.asisteuco.application.interactor.profesor.consultarprofesor.ConsultarProfesorInteractor;
import co.edu.uco.asisteuco.application.interactor.profesor.consultarprofesor.dto.request.ConsultarProfesorRequestDTO;
import co.edu.uco.asisteuco.application.interactor.profesor.consultarprofesor.dto.response.ConsultarProfesorResponseDTO;
import co.edu.uco.asisteuco.application.outputport.dto.ProfesorDTO;
import co.edu.uco.asisteuco.application.outputport.entity.ProfesorEntity;
import co.edu.uco.asisteuco.application.outputport.repository.ProfesorRepository;

@Service
public class ConsultarProfesorInteractorImpl implements ConsultarProfesorInteractor {

    private final ProfesorRepository repository;

    public ConsultarProfesorInteractorImpl(ProfesorRepository repository) {
        this.repository = repository;
    }

    @Override
    public ConsultarProfesorResponseDTO<ProfesorDTO> ejecutar(ConsultarProfesorRequestDTO request) {
        List<ProfesorEntity> entities;
        UUID id = request.getId();
        UUID tipoId = request.getTipoIdentificacionId();
        String numero = request.getNumeroIdentificacion();
        String nombres = request.getNombresCompletos();

        if (id != null) {
            entities = repository.findById(id)
                .map(List::of)
                .orElseGet(List::of);
        } else {
            entities = repository.findAll().stream()
                .filter(e -> {
                    boolean ok = true;
                    if (tipoId != null) {
                        ok &= tipoId.equals(e.getTipoIdentificacion().getId());
                    }
                    if (numero != null && !numero.isBlank()) {
                        ok &= numero.equals(e.getNumeroIdentificacion());
                    }
                    if (nombres != null && !nombres.isBlank()) {
                        ok &= e.getNombresCompletos().toLowerCase()
                                  .contains(nombres.toLowerCase());
                    }
                    return ok;
                })
                .collect(Collectors.toList());
        }

        if (entities.isEmpty()) {
            return ConsultarProfesorResponseDTO.fallido(
                List.of("No se encontraron profesores con esos criterios."));
        }

        List<ProfesorDTO> dtos = entities.stream()
            .map(e -> {
                ProfesorDTO dto = new ProfesorDTO();
                dto.setId(e.getId());
                dto.setTipoIdentificacionId(e.getTipoIdentificacion().getId());
                dto.setNumeroIdentificacion(e.getNumeroIdentificacion());
                dto.setNombresCompletos(e.getNombresCompletos());
                return dto;
            })
            .collect(Collectors.toList());

        return ConsultarProfesorResponseDTO.exitoso(
            List.of("Consulta de profesores realizada exitosamente."),
            dtos
        );
    }
}