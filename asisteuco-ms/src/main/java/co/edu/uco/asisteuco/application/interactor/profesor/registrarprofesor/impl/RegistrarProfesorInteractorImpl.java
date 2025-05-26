package co.edu.uco.asisteuco.application.interactor.profesor.registrarprofesor.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.uco.asisteuco.application.interactor.profesor.registrarprofesor.RegistrarProfesorInteractor;
import co.edu.uco.asisteuco.application.interactor.profesor.registrarprofesor.dto.request.RegistrarProfesorRequestDTO;
import co.edu.uco.asisteuco.application.interactor.profesor.registrarprofesor.dto.response.RegistrarProfesorResponseDTO;
import co.edu.uco.asisteuco.application.outputport.entity.ProfesorEntity;
import co.edu.uco.asisteuco.application.outputport.repository.ProfesorRepository;
import co.edu.uco.asisteuco.application.outputport.repository.TipoIdentificacionRepository;

@Service
public class RegistrarProfesorInteractorImpl implements RegistrarProfesorInteractor {

    private final ProfesorRepository repo;
    private final TipoIdentificacionRepository tipoRepo;

    public RegistrarProfesorInteractorImpl(
        ProfesorRepository repo,
        TipoIdentificacionRepository tipoRepo
    ) {
        this.repo = repo;
        this.tipoRepo = tipoRepo;
    }

    @Override
    public RegistrarProfesorResponseDTO ejecutar(RegistrarProfesorRequestDTO request) {
        var tipo = tipoRepo.findById(request.getTipoIdentificacionId())
            .orElseThrow(() -> new IllegalArgumentException(
                "Tipo de identificación no encontrado: " + request.getTipoIdentificacionId()));

        if (repo.existsByTipoIdentificacionIdAndNumeroIdentificacion(
                request.getTipoIdentificacionId(),
                request.getNumeroIdentificacion())) {
            throw new IllegalArgumentException(
                "Ya existe un profesor con ese tipo y número de identificación.");
        }

        ProfesorEntity entidad = new ProfesorEntity(
            tipo,
            request.getNumeroIdentificacion(),
            request.getNombresCompletos()
        );
        repo.save(entidad);

        var response = new RegistrarProfesorResponseDTO();
        response.setTransaccionExitosa(true);
        response.setMensajes(List.of("Profesor registrado exitosamente."));
        return response;
    }
}