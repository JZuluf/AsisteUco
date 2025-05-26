package co.edu.uco.asisteuco.application.interactor.estudiantegrupo.registrarestudiantegrupo.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import co.edu.uco.asisteuco.application.interactor.estudiantegrupo.registrarestudiantegrupo.RegistrarEstudianteGrupoInteractor;
import co.edu.uco.asisteuco.application.interactor.estudiantegrupo.registrarestudiantegrupo.dto.request.RegistrarEstudianteGrupoRequestDTO;
import co.edu.uco.asisteuco.application.interactor.estudiantegrupo.registrarestudiantegrupo.dto.response.RegistrarEstudianteGrupoResponseDTO;
import co.edu.uco.asisteuco.application.outputport.entity.EstudianteEntity;
import co.edu.uco.asisteuco.application.outputport.entity.EstudianteGrupoEntity;
import co.edu.uco.asisteuco.application.outputport.entity.GrupoEntity;
import co.edu.uco.asisteuco.application.outputport.repository.EstudianteGrupoRepository;
import co.edu.uco.asisteuco.application.outputport.repository.EstudianteRepository;
import co.edu.uco.asisteuco.application.outputport.repository.GrupoRepository;


@Service
public class RegistrarEstudianteGrupoInteractorImpl implements RegistrarEstudianteGrupoInteractor {

    private final EstudianteGrupoRepository egRepo;
    private final GrupoRepository grupoRepo;
    private final EstudianteRepository estudianteRepo;

    public RegistrarEstudianteGrupoInteractorImpl(
        EstudianteGrupoRepository egRepo,
        GrupoRepository grupoRepo,
        EstudianteRepository estudianteRepo
    ) {
        this.egRepo = egRepo;
        this.grupoRepo = grupoRepo;
        this.estudianteRepo = estudianteRepo;
    }

    @Override
    public RegistrarEstudianteGrupoResponseDTO ejecutar(RegistrarEstudianteGrupoRequestDTO request) {
        UUID grupoId = request.getGrupoId();
        UUID estudianteId = request.getEstudianteId();

        GrupoEntity grupo = grupoRepo.findById(grupoId)
            .orElseThrow(() -> 
                new IllegalArgumentException("Grupo no encontrado: " + grupoId));
        EstudianteEntity estudiante = estudianteRepo.findById(estudianteId)
            .orElseThrow(() -> 
                new IllegalArgumentException("Estudiante no encontrado: " + estudianteId));

        if (egRepo.existsByGrupo_IdAndEstudiante_Id(grupoId, estudianteId)) {
            throw new IllegalArgumentException(
                "El estudiante ya está asignado a ese grupo.");
        }

        EstudianteGrupoEntity asignacion = new EstudianteGrupoEntity(grupo, estudiante);
        EstudianteGrupoEntity saved = egRepo.save(asignacion);

        return new RegistrarEstudianteGrupoResponseDTO(saved.getId());
    }
}
