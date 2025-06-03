package co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    private final JavaMailSender mailSender;

    public RegistrarAsistenciaInteractorImpl(
            AsistenciaRepository asistenciaRepo,
            SesionRepository sesionRepo,
            EstudianteGrupoRepository egRepo,
            JavaMailSender mailSender
    ) {
        this.asistenciaRepo = asistenciaRepo;
        this.sesionRepo = sesionRepo;
        this.egRepo = egRepo;
        this.mailSender = mailSender;
    }

    @Override
    @Transactional
    public RegistrarAsistenciaResponseDTO ejecutar(RegistrarAsistenciaRequestDTO request) {
        // 1) Obtener la sesión
        SesionEntity sesion = sesionRepo.findById(request.getSesionId())
            .orElseThrow(() -> new AsisteUcoException("Sesión no encontrada: " + request.getSesionId()));

        // 2) Validar que existan detalles
        List<DetalleAsistenciaDTO> detalles = request.getAsistencias();
        if (detalles == null || detalles.isEmpty()) {
            throw new AsisteUcoException("Debe enviar al menos un registro de asistencia.");
        }

        // 3) Mapear a entidades AsistenciaEntity
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

        // 4) Persistir todas las asistencias
        asistenciaRepo.saveAll(registros);

        // 5) Enviar correo a quienes NO asistieron
        for (DetalleAsistenciaDTO det : detalles) {
            if (det.getAsistio() != null && !det.getAsistio()) {
                EstudianteGrupoEntity eg = egRepo.findById(det.getEstudianteGrupoId())
                    .orElseThrow(() -> new AsisteUcoException(
                        "No existe asignación estudiante-grupo al enviar correo: " + det.getEstudianteGrupoId()));

                String nombreEstudiante = eg.getEstudiante().getNombresCompletos();
                String correoDestinatario = eg.getEstudiante().getEmail();
                String asunto = "Faltaste a la sesión: " + sesion.getFecha();
                String cuerpo = String.format(
                    "Hola %s,%n%nHemos visto que no asististe a la sesión \"%s\".%n" +
                    "Si tienes alguna duda, por favor contáctanos.%n%nSaludos,%nEquipo AsisteUco",
                    nombreEstudiante, sesion.getFecha()
                );

                SimpleMailMessage message = new SimpleMailMessage();
                // 'from' debe coincidir con spring.mail.username en application.properties
                message.setFrom("tu.cuenta@gmail.com");
                message.setTo(correoDestinatario);
                message.setSubject(asunto);
                message.setText(cuerpo);

                try {
                    mailSender.send(message);
                } catch (Exception e) {
                    // Solo registramos el fallo; la transacción de guardado ya se completó
                    System.err.println("Error enviando correo a " + correoDestinatario + ": " + e.getMessage());
                }
            }
        }

        // 6) Devolver los IDs generados
        List<UUID> ids = registros.stream()
                                  .map(AsistenciaEntity::getId)
                                  .collect(Collectors.toList());

        return new RegistrarAsistenciaResponseDTO(ids);
    }
}
