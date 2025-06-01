package co.edu.uco.asisteuco.application.interactor.tipoidentificacion.consultartipoidentificacion.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uco.asisteuco.application.interactor.tipoidentificacion.consultartipoidentificacion.ConsultarTipoIdentificacionInteractor;
import co.edu.uco.asisteuco.application.interactor.tipoidentificacion.consultartipoidentificacion.dto.request.ConsultarTipoIdentificacionRequestDTO;
import co.edu.uco.asisteuco.application.interactor.tipoidentificacion.consultartipoidentificacion.dto.response.ConsultarTipoIdentificacionResponseDTO;
import co.edu.uco.asisteuco.application.outputport.repository.TipoIdentificacionRepository;
import co.edu.uco.asisteuco.application.outputport.dto.TipoIdentificacionDTO;
import co.edu.uco.asisteuco.application.outputport.entity.TipoIdentificacionEntity;

@Service
public class ConsultarTipoIdentificacionInteractorImpl implements ConsultarTipoIdentificacionInteractor {

    private final TipoIdentificacionRepository repository;

    @Autowired
    public ConsultarTipoIdentificacionInteractorImpl(TipoIdentificacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public ConsultarTipoIdentificacionResponseDTO ejecutar(ConsultarTipoIdentificacionRequestDTO request) {
        try {
            List<TipoIdentificacionEntity> resultados = new ArrayList<>();

            UUID uuid = request.getUuid();
            String clave = request.getClave();
            String nombre = request.getNombre();

            if (uuid != null) {
                repository.findById(uuid).ifPresent(resultados::add);
            } else if (clave != null && nombre != null) {
                resultados = repository.findByClaveAndNombre(clave, nombre);
            } else if (clave != null) {
                resultados = repository.findByClave(clave);
            } else if (nombre != null) {
                resultados = repository.findByNombre(nombre);
            } else {
                resultados = repository.findAll(); // si quieres permitir búsquedas sin filtros
            }

            if (resultados.isEmpty()) {
                return ConsultarTipoIdentificacionResponseDTO.fallido("No se encontraron resultados.");
            }

            List<String> mensajes = List.of("Consulta realizada exitosamente");

         // Aquí debes mapear las entidades a DTOs antes de pasarlos como resultados
	         List<TipoIdentificacionDTO> resultadosDTO = resultados.stream()
	             .map(e -> {
	                 TipoIdentificacionDTO dto = new TipoIdentificacionDTO();
	                 dto.setUuid(e.getId());
	                 dto.setClave(e.getClave());
	                 dto.setNombre(e.getNombre());
	                 return dto;
	             })
             .toList();

         return ConsultarTipoIdentificacionResponseDTO.exitoso(mensajes, resultadosDTO);

        } catch (Exception e) {
            return ConsultarTipoIdentificacionResponseDTO.fallido("Ocurrió un error al consultar: " + e.getMessage());
        }
    }
}
