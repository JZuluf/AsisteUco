package co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.email;
import co.edu.uco.asisteuco.infrastructure.email.SimpleEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailTestController {

    private final SimpleEmailSender emailSender;

    @Autowired
    public EmailTestController(SimpleEmailSender emailSender) {
        this.emailSender = emailSender;
    }

    /**
     * Endpoint de prueba: /api/test-email?to=destino@ejemplo.com
     * Envía un correo de prueba “tal cual” en el video.
     */
    @GetMapping("/api/test-email")
    public ResponseEntity<String> testEmail(@RequestParam("to") String to) {
        try {
            String from = "tu.cuenta@gmail.com";   // Debe coincidir con spring.mail.username
            String subject = "Correo de prueba directo";
            String body = "¡Hola!\n\nEste correo se envía directamente con JavaMailSender, te queria preguntar si le hablamos a farid para que nos revise el proyecto si puede.";

            emailSender.sendSimpleEmail(from, to, subject, body);

            return ResponseEntity.ok("Correo enviado correctamente a " + to);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body("Error al enviar correo: " + e.getMessage());
        }
    }
}
