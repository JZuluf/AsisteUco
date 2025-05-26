package co.edu.uco.asisteuco.application.interactor.materia.registrarmateria.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uco.asisteuco.crosscutting.exception.AsisteUcoException;
import co.edu.uco.asisteuco.application.interactor.materia.registrarmateria.RegistrarMateriaInteractor;
import co.edu.uco.asisteuco.application.interactor.materia.registrarmateria.dto.request.RegistrarMateriaRequestDTO;
import co.edu.uco.asisteuco.application.interactor.materia.registrarmateria.dto.response.RegistrarMateriaResponseDTO;
import co.edu.uco.asisteuco.application.outputport.entity.MateriaEntity;
import co.edu.uco.asisteuco.application.outputport.repository.MateriaRepository;

@Service
public class RegistrarMateriaInteractorImpl implements RegistrarMateriaInteractor {

    private final MateriaRepository repository;

    @Autowired
    public RegistrarMateriaInteractorImpl(MateriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public RegistrarMateriaResponseDTO ejecutar(RegistrarMateriaRequestDTO request) {
        if (repository.existsByClave(request.getClave())) {
            throw new AsisteUcoException("Ya existe una materia con clave: " + request.getClave());
        }
        var entity = new MateriaEntity();
        entity.setClave(request.getClave());
        entity.setNombre(request.getNombre());
        entity = repository.save(entity);
        return new RegistrarMateriaResponseDTO(entity.getId());
    }
}

