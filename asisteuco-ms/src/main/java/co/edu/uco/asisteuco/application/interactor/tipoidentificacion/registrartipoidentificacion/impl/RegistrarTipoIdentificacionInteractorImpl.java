package co.edu.uco.asisteuco.application.interactor.tipoidentificacion.registrartipoidentificacion.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uco.asisteuco.application.interactor.tipoidentificacion.registrartipoidentificacion.RegistrarTipoIdentificacionInteractor;
import co.edu.uco.asisteuco.application.interactor.tipoidentificacion.registrartipoidentificacion.dto.request.RegistrarTipoIdentificacionRequestDTO;
import co.edu.uco.asisteuco.application.interactor.tipoidentificacion.registrartipoidentificacion.dto.response.RegistrarTipoIdentificacionResponseDTO;
import co.edu.uco.asisteuco.application.outputport.entity.TipoIdentificacionEntity;
import co.edu.uco.asisteuco.application.outputport.repository.TipoIdentificacionRepository;



@Service
public class RegistrarTipoIdentificacionInteractorImpl implements RegistrarTipoIdentificacionInteractor {

    private final TipoIdentificacionRepository tipoIdentificacionRepository;

    @Autowired
    public RegistrarTipoIdentificacionInteractorImpl(TipoIdentificacionRepository tipoIdentificacionRepository) {
        this.tipoIdentificacionRepository = tipoIdentificacionRepository;
    }

    @Override
    public RegistrarTipoIdentificacionResponseDTO ejecutar(RegistrarTipoIdentificacionRequestDTO request) {
        // Convertir el DTO de request a entidad
        TipoIdentificacionEntity entity = new TipoIdentificacionEntity();
        entity.setNombre(request.getNombre());
        entity.setClave(request.getClave());

        // Guardar la entidad en la base de datos
        TipoIdentificacionEntity savedEntity = tipoIdentificacionRepository.save(entity);

        // Crear response DTO y devolver
        RegistrarTipoIdentificacionResponseDTO response = new RegistrarTipoIdentificacionResponseDTO();
   	    response.setTransaccionExitosa(true);
        response.setMensajes(List.of("Tipo de identificación registrado exitosamente."));

        return response;
    }
    
}
