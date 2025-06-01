package co.edu.uco.asisteuco.application.interactor.grupo.consultargrupo.impl;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uco.asisteuco.application.interactor.grupo.consultargrupo.ConsultarGrupoInteractor;
import co.edu.uco.asisteuco.application.interactor.grupo.consultargrupo.dto.request.ConsultarGrupoRequestDTO;
import co.edu.uco.asisteuco.application.interactor.grupo.consultargrupo.dto.response.ConsultarGrupoResponseDTO;
import co.edu.uco.asisteuco.application.outputport.dto.GrupoDTO;
import co.edu.uco.asisteuco.application.outputport.entity.GrupoEntity;
import co.edu.uco.asisteuco.application.outputport.repository.GrupoRepository;

@Service
public class ConsultarGrupoInteractorImpl implements ConsultarGrupoInteractor {

    private final GrupoRepository repository;

    @Autowired
    public ConsultarGrupoInteractorImpl(GrupoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ConsultarGrupoResponseDTO<GrupoDTO> ejecutar(ConsultarGrupoRequestDTO request) {
        try {
            List<GrupoEntity> entities;

            UUID id         = request.getId();
            UUID profId     = request.getProfesorId();
            UUID materiaId  = request.getMateriaId();

            if (id != null) {
                entities = repository.findById(id)
                             .map(Collections::singletonList)
                             .orElseGet(Collections::emptyList);
            } else if (profId != null && materiaId != null) {
                entities = repository.findByProfesor_IdAndMateria_Id(profId, materiaId);
            } else if (profId != null) {
                entities = repository.findByProfesor_Id(profId);
            } else if (materiaId != null) {
                entities = repository.findByMateria_Id(materiaId);
            } else {
                entities = repository.findAll();
            }

            if (entities.isEmpty()) {
                return ConsultarGrupoResponseDTO.fallido("No se encontraron grupos.");
            }

            var resultados = entities.stream().map(e -> {
                var dto = new GrupoDTO();
                dto.setId(e.getId());
                dto.setProfesorId(e.getProfesor().getId());
                dto.setMateriaId(e.getMateria().getId());
                dto.setCantidadEstudiantes(e.getCantidadEstudiantes());
                return dto;
            }).toList();

            return ConsultarGrupoResponseDTO.exitoso(
                List.of("Consulta de grupos exitosa"), 
                resultados
            );
        } catch (Exception ex) {
            return ConsultarGrupoResponseDTO.fallido("Error al consultar grupos: " + ex.getMessage());
        }
    }
}
