package co.edu.uco.asisteuco.application.interactor.sesion.registrarsesion.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uco.asisteuco.application.interactor.sesion.registrarsesion.RegistrarSesionInteractor;
import co.edu.uco.asisteuco.application.interactor.sesion.registrarsesion.dto.request.RegistrarSesionRequestDTO;
import co.edu.uco.asisteuco.application.interactor.sesion.registrarsesion.dto.response.RegistrarSesionResponseDTO;
import co.edu.uco.asisteuco.application.outputport.entity.GrupoEntity;
import co.edu.uco.asisteuco.application.outputport.entity.SesionEntity;
import co.edu.uco.asisteuco.application.outputport.repository.GrupoRepository;
import co.edu.uco.asisteuco.application.outputport.repository.SesionRepository;
import co.edu.uco.asisteuco.crosscutting.exception.AsisteUcoException;

@Service
public class RegistrarSesionInteractorImpl implements RegistrarSesionInteractor {

    private final SesionRepository sesionRepo;
    private final GrupoRepository grupoRepo;

    public RegistrarSesionInteractorImpl(SesionRepository sesionRepo, GrupoRepository grupoRepo) {
        this.sesionRepo  = sesionRepo;
        this.grupoRepo   = grupoRepo;
    }

    @Override
    @Transactional
    public RegistrarSesionResponseDTO ejecutar(RegistrarSesionRequestDTO req) {
        GrupoEntity grupo = grupoRepo.findById(req.getGrupoId())
            .orElseThrow(() -> new AsisteUcoException("Grupo no encontrado: " + req.getGrupoId()));

        SesionEntity entidad = new SesionEntity();
        entidad.setGrupo(grupo);
        entidad.setFecha(req.getFecha());
        entidad.setFechaHoraInicio(req.getFechaHoraInicio());
        entidad.setFechaHoraFin   (req.getFechaHoraFin());
        entidad.setAula           (req.getAula());
        entidad.setEstado         (req.getEstado());

        SesionEntity guardada = sesionRepo.save(entidad);
        return new RegistrarSesionResponseDTO(guardada.getId());
    }
}
