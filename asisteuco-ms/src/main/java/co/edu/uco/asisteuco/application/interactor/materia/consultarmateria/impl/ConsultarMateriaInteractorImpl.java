package co.edu.uco.asisteuco.application.interactor.materia.consultarmateria.impl;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uco.asisteuco.application.interactor.materia.consultarmateria.ConsultarMateriaInteractor;
import co.edu.uco.asisteuco.application.interactor.materia.consultarmateria.dto.request.ConsultarMateriaRequestDTO;
import co.edu.uco.asisteuco.application.interactor.materia.consultarmateria.dto.response.ConsultarMateriaResponseDTO;
import co.edu.uco.asisteuco.application.outputport.dto.MateriaDTO;
import co.edu.uco.asisteuco.application.outputport.entity.MateriaEntity;
import co.edu.uco.asisteuco.application.outputport.repository.MateriaRepository;


@Service
public class ConsultarMateriaInteractorImpl implements ConsultarMateriaInteractor {

    private final MateriaRepository repository;

    @Autowired
    public ConsultarMateriaInteractorImpl(MateriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public ConsultarMateriaResponseDTO<MateriaDTO> ejecutar(ConsultarMateriaRequestDTO request) {
        try {
            List<MateriaEntity> entidades;

            UUID id     = request.getId();
            String clave  = request.getClave();
            String nombre = request.getNombre();

            if (id != null) {
                // si existe, envolvemos en una lista; si no, lista vacía
                entidades = repository.findById(id)
                    .map(e -> Collections.singletonList(e))
                    .orElseGet(Collections::emptyList);
            } else if (clave != null && nombre != null) {
                entidades = repository.findByClaveAndNombre(clave, nombre);
            } else if (clave != null) {
                entidades = repository.findByClave(clave);
            } else if (nombre != null) {
                entidades = repository.findByNombreContainingIgnoreCase(nombre);
            } else {
                entidades = repository.findAll();
            }

            if (entidades.isEmpty()) {
                return ConsultarMateriaResponseDTO.fallido("No se encontraron materias.");
            }

            List<MateriaDTO> dtos = entidades.stream()
                .map(e -> {
                    MateriaDTO dto = new MateriaDTO();
                    dto.setId(e.getId());
                    dto.setClave(e.getClave());
                    dto.setNombre(e.getNombre());
                    return dto;
                })
                .collect(Collectors.toList());

            return ConsultarMateriaResponseDTO.exitoso("Consulta de materias exitosa.", dtos);
        } catch (Exception ex) {
            return ConsultarMateriaResponseDTO.fallido("Error al consultar materias: " + ex.getMessage());
        }
    }
}

