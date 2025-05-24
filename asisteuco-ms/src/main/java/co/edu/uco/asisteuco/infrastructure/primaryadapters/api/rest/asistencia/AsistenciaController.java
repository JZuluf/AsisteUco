package co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.asistencia;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.RegistrarAsistenciaInteractor;
import co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.dto.request.RegistrarAsistenciaRequestDTO;
import jakarta.validation.Valid;

@RestController

@RequestMapping("/api/v1/asistencia") 
public class AsistenciaController {

    private final RegistrarAsistenciaInteractor registrarAsistenciaInteractor;

    public AsistenciaController(RegistrarAsistenciaInteractor registrarAsistenciaInteractor) {
        this.registrarAsistenciaInteractor = registrarAsistenciaInteractor;
    }

    @PostMapping
    public ResponseEntity<Object> registrarAsistencia(@Valid @RequestBody RegistrarAsistenciaRequestDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errores = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining("; "));
            return ResponseEntity.badRequest().body("Errores en la solicitud: " + errores);
        }

        try {
        
            registrarAsistenciaInteractor.ejecutar(dto); 
            
            return ResponseEntity.status(HttpStatus.CREATED).body("Asistencia registrada con éxito");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en los datos de la solicitud: " + e.getMessage());
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al procesar la solicitud de asistencia: " + e.getMessage());
        }
    }
}


///Aqui lo malo para abajo es prueba

/*    private final RegistrarAsistenciaInteractor registrarAsistenciaInteractor;
    private final ObtenerAsistenciaInteractor obtenerAsistenciaInteractor;

    public AsistenciaController(
        RegistrarAsistenciaInteractor registrarAsistenciaInteractor,
        ObtenerAsistenciaInteractor obtenerAsistenciaInteractor,
    ) {
        this.registrarAsistenciaInteractor = registrarAsistenciaInteractor;
        this.obtenerAsistenciaInteractor = obtenerAsistenciaInteractor;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Object> registrarAsistencia(@Valid @RequestBody RegistrarAsistenciaRequestDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errores = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining("; "));
            return ResponseEntity.badRequest().body("Errores en la solicitud: " + errores);
        }

        try {
            registrarAsistenciaInteractor.ejecutar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Asistencia registrada con éxito");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en los datos: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
        }
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<AsistenciaDTO>> obtenerTodasLasAsistencias() {
        List<AsistenciaDTO> asistencias = obtenerAsistenciaInteractor.obtenerTodas();
        return ResponseEntity.ok(asistencias);
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerPorId(@PathVariable Long id) {
        try {
            AsistenciaDTO asistencia = obtenerAsistenciaInteractor.obtenerPorId(id);
            return ResponseEntity.ok(asistencia);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Asistencia no encontrada");
        }
    }

}*/
