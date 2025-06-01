package co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.RegistrarAsistenciaInteractor;
import co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.dto.request.DetalleAsistenciaDTO;
import co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.dto.request.RegistrarAsistenciaRequestDTO;
import co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.dto.response.RegistrarAsistenciaResponseDTO;
import co.edu.uco.asisteuco.application.outputport.entity.AsistenciaEntity;
import co.edu.uco.asisteuco.application.outputport.entity.EstudianteGrupoEntity;
import co.edu.uco.asisteuco.application.outputport.entity.SesionEntity;
import co.edu.uco.asisteuco.application.outputport.repository.AsistenciaRepository;
import co.edu.uco.asisteuco.application.outputport.repository.EstudianteGrupoRepository;
import co.edu.uco.asisteuco.application.outputport.repository.SesionRepository;
import co.edu.uco.asisteuco.crosscutting.exception.AsisteUcoException;

@Service
public class RegistrarAsistenciaInteractorImpl implements RegistrarAsistenciaInteractor {

    private final AsistenciaRepository asistenciaRepo;
    private final SesionRepository sesionRepo;
    private final EstudianteGrupoRepository egRepo;

    public RegistrarAsistenciaInteractorImpl(
            AsistenciaRepository asistenciaRepo,
            SesionRepository sesionRepo,
            EstudianteGrupoRepository egRepo) {
        this.asistenciaRepo = asistenciaRepo;
        this.sesionRepo = sesionRepo;
        this.egRepo = egRepo;
    }

    @Override
    @Transactional
    public RegistrarAsistenciaResponseDTO ejecutar(RegistrarAsistenciaRequestDTO request) {
        // 1) Busco la sesión
        SesionEntity sesion = sesionRepo.findById(request.getSesionId())
            .orElseThrow(() -> new AsisteUcoException("Sesión no encontrada: " + request.getSesionId()));

        // 2) Valido que haya detalles
        List<DetalleAsistenciaDTO> detalles = request.getAsistencias();
        if (detalles == null || detalles.isEmpty()) {
            throw new AsisteUcoException("Debe enviar al menos un registro de asistencia.");
        }

        // 3) Por cada detalle, obtengo el EstudianteGrupo y creo la entidad
        List<AsistenciaEntity> registros = detalles.stream()
            .map(det -> {
                EstudianteGrupoEntity eg = egRepo.findById(det.getEstudianteGrupoId())
                    .orElseThrow(() -> new AsisteUcoException(
                        "No existe asignación estudiante-grupo: " + det.getEstudianteGrupoId()));

                AsistenciaEntity a = new AsistenciaEntity();
                a.setSesion(sesion);
                a.setEstudianteGrupo(eg);
                a.setAsistio(det.getAsistio());
                return a;
            })
            .collect(Collectors.toList());

        // 4) Persisto todos en una sola transacción (INSERTs)
        asistenciaRepo.saveAll(registros);

        // 5) Devuelvo los IDs generados
        List<UUID> ids = registros.stream()
                                  .map(AsistenciaEntity::getId)
                                  .collect(Collectors.toList());

        return new RegistrarAsistenciaResponseDTO(ids);
    }
}
