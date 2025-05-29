package co.edu.uco.asisteuco.application.interactor.grupo.registrargrupo.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uco.asisteuco.crosscutting.exception.AsisteUcoException;
import co.edu.uco.asisteuco.application.interactor.grupo.registrargrupo.RegistrarGrupoInteractor;
import co.edu.uco.asisteuco.application.interactor.grupo.registrargrupo.dto.request.RegistrarGrupoRequestDTO;
import co.edu.uco.asisteuco.application.interactor.grupo.registrargrupo.dto.response.RegistrarGrupoResponseDTO;
import co.edu.uco.asisteuco.application.outputport.entity.GrupoEntity;
import co.edu.uco.asisteuco.application.outputport.repository.GrupoRepository;
import co.edu.uco.asisteuco.application.outputport.repository.ProfesorRepository;
import co.edu.uco.asisteuco.application.outputport.repository.MateriaRepository;

@Service
public class RegistrarGrupoInteractorImpl implements RegistrarGrupoInteractor {

    private final GrupoRepository    grupoRepo;
    private final ProfesorRepository profesorRepo;
    private final MateriaRepository  materiaRepo;

    @Autowired
    public RegistrarGrupoInteractorImpl(GrupoRepository grupoRepo,
                                        ProfesorRepository profesorRepo,
                                        MateriaRepository materiaRepo) {
        this.grupoRepo    = grupoRepo;
        this.profesorRepo = profesorRepo;
        this.materiaRepo  = materiaRepo;
    }

    @Override
    public RegistrarGrupoResponseDTO ejecutar(RegistrarGrupoRequestDTO request) {
        // 1) Validar existencia de profesor
        var profesor = profesorRepo.findById(request.getProfesorId())
            .orElseThrow(() -> new AsisteUcoException(
                "Profesor no encontrado: " + request.getProfesorId()));

        // 2) Validar existencia de materia
        var materia = materiaRepo.findById(request.getMateriaId())
            .orElseThrow(() -> new AsisteUcoException(
                "Materia no encontrada: " + request.getMateriaId()));

        // 3) Crear y guardar la entidad Grupo
        var entity = new GrupoEntity(
            UUID.randomUUID(),
            profesor,
            materia,
            request.getCantidadEstudiantes()
        );
        grupoRepo.save(entity);

        // 4) Devolver respuesta exitosa
        return RegistrarGrupoResponseDTO.exitoso(
            entity.getId()
        );
    }
}
