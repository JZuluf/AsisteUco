package co.edu.uco.asisteuco.application.interactor.sesion.consultarsesion.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import co.edu.uco.asisteuco.application.interactor.sesion.consultarsesion.ConsultarSesionInteractor;
import co.edu.uco.asisteuco.application.interactor.sesion.consultarsesion.dto.request.ConsultarSesionRequestDTO;
import co.edu.uco.asisteuco.application.interactor.sesion.consultarsesion.dto.response.ConsultarSesionResponseDTO;
import co.edu.uco.asisteuco.application.outputport.dto.SesionDTO;
import co.edu.uco.asisteuco.application.outputport.entity.SesionEntity;
import co.edu.uco.asisteuco.application.outputport.repository.SesionRepository;

@Service
public class ConsultarSesionInteractorImpl implements ConsultarSesionInteractor {

    private final SesionRepository repo;

    public ConsultarSesionInteractorImpl(SesionRepository repo) {
        this.repo = repo;
    }

    @Override
    public ConsultarSesionResponseDTO<SesionDTO> ejecutar(ConsultarSesionRequestDTO req) {
        List<SesionEntity> entidades;

        if (req.getId() != null) {
            entidades = repo.findById(req.getId()).map(List::of).orElse(List.of());
        } else if (req.getGrupoId() != null) {
            entidades = repo.findByGrupo_Id(req.getGrupoId());
        } else {
            entidades = repo.findAll();
        }

        if (entidades.isEmpty()) {
            return ConsultarSesionResponseDTO.fallido("No se encontraron sesiones.");
        }

        List<SesionDTO> dtos = entidades.stream().map(e -> {
            var d = new SesionDTO();
            d.setId               (e.getId());
            d.setGrupoId          (e.getGrupo().getId());
            d.setFechaHoraInicio  (e.getFechaHoraInicio());
            d.setFechaHoraFin     (e.getFechaHoraFin());
            d.setAula             (e.getAula());
            d.setEstado           (e.getEstado());
            return d;
        }).collect(Collectors.toList());

        return ConsultarSesionResponseDTO.exitoso("Sesiones consultadas correctamente.", dtos);
    }
}
