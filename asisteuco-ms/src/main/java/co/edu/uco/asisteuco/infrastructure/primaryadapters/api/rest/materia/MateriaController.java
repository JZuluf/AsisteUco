package co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.materia;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.asisteuco.application.interactor.materia.consultarmateria.ConsultarMateriaInteractor;
import co.edu.uco.asisteuco.application.interactor.materia.consultarmateria.dto.request.ConsultarMateriaRequestDTO;
import co.edu.uco.asisteuco.application.interactor.materia.registrarmateria.RegistrarMateriaInteractor;
import co.edu.uco.asisteuco.application.interactor.materia.registrarmateria.dto.request.RegistrarMateriaRequestDTO;
import co.edu.uco.asisteuco.application.interactor.materia.registrarmateria.dto.response.RegistrarMateriaResponseDTO;
import co.edu.uco.asisteuco.application.outputport.dto.MateriaDTO;
import co.edu.uco.asisteuco.crosscutting.exception.AsisteUcoException;
import co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.response.RespuestaApi;


@RestController
@RequestMapping("/api/v1/materia")
@CrossOrigin(origins = "*")
public class MateriaController {

    private final RegistrarMateriaInteractor registrar;
    private final ConsultarMateriaInteractor consultar;

    @Autowired
    public MateriaController(RegistrarMateriaInteractor registrar,
                             ConsultarMateriaInteractor consultar) {
        this.registrar = registrar;
        this.consultar = consultar;
    }

    @PostMapping
    public ResponseEntity<RespuestaApi<RegistrarMateriaResponseDTO>> registrar(
            @RequestBody RegistrarMateriaRequestDTO request) {
        var api    = new RespuestaApi<RegistrarMateriaResponseDTO>();
        HttpStatus status = HttpStatus.CREATED;         // <- Spring HttpStatus

        try {
            var data = registrar.ejecutar(request);
            api.getDatos().add(data);
            api.getMensajes().add("Materia registrada exitosamente.");
        } catch (AsisteUcoException ex) {
            status = HttpStatus.BAD_REQUEST;            // <- Spring HttpStatus
            api.getMensajes().add(ex.getMensajeUsuario());
        } catch (Exception ex) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;  // <- Spring HttpStatus
            api.getMensajes().add("Error inesperado. Contacte al administrador.");
        }

        return new ResponseEntity<>(api, status);
    }

    @GetMapping
    public ResponseEntity<RespuestaApi<MateriaDTO>> listar(
            @RequestParam(value = "id",     required = false) UUID id,
            @RequestParam(value = "clave",  required = false) String clave,
            @RequestParam(value = "nombre", required = false) String nombre) {

        var req  = new ConsultarMateriaRequestDTO();
        req.setId(id);
        req.setClave(clave);
        req.setNombre(nombre);

        var resp = consultar.ejecutar(req);

        // de nuevo, Spring HttpStatus
        HttpStatus status = resp.isTransaccionExitosa()
                         ? HttpStatus.OK
                         : HttpStatus.NOT_FOUND;

        var api = new RespuestaApi<MateriaDTO>();
        api.setTransaccionExitosa(resp.isTransaccionExitosa());
        api.setMensajes(resp.getMensajes());
        api.setDatos(resp.getResultados());

        return new ResponseEntity<>(api, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaApi<MateriaDTO>> obtenerPorId(@PathVariable UUID id) {
        var api = new RespuestaApi<MateriaDTO>();
        var req = new ConsultarMateriaRequestDTO();
        req.setId(id);

        var resp = consultar.ejecutar(req);
        if (resp.isTransaccionExitosa() && !resp.getResultados().isEmpty()) {
            api.setTransaccionExitosa(true);
            api.getDatos().add((MateriaDTO) resp.getResultados().get(0));
            api.getMensajes().add("Materia encontrada.");
            return ResponseEntity.ok(api);
        } else {
            api.setTransaccionExitosa(false);
            api.getMensajes().add("No se encontró materia con ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(api);
        }
    }
}
