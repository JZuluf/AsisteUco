package co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.asistencia;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.RegistrarAsistenciaInteractor;
import co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.dto.request.RegistrarAsistenciaRequestDTO;


import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/asistencias")
public class AsistenciaController {

    private final RegistrarAsistenciaInteractor registrarAsistenciaInteractor;

    public AsistenciaController(RegistrarAsistenciaInteractor registrarAsistenciaInteractor) {
        this.registrarAsistenciaInteractor = registrarAsistenciaInteractor;
    }

    @PostMapping
    public ResponseEntity<Object> registrarAsistencia(@Valid @RequestBody RegistrarAsistenciaRequestDTO dto, BindingResult bindingResult) {
        // 1. Manejo de errores de validación
        if (bindingResult.hasErrors()) {
            String errores = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining("; ")); // Forma alternativa y concisa de unir mensajes
 
            return ResponseEntity.badRequest().body("Errores en la solicitud: " + errores);
        }

        try {
            
            return ResponseEntity.status(HttpStatus.CREATED).body("Asistencia registrada con éxito");

        } catch (IllegalArgumentException e) {
           
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en los datos de la solicitud: " + e.getMessage());
        } catch (Exception e) {
           
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al procesar la solicitud de asistencia: " + e.getMessage());
        }
    }
}