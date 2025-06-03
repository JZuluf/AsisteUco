package co.edu.uco.asisteuco.infrastructure.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Servicio sencillo que envía correos de texto plano usando JavaMailSender.
 * Se comporta exactamente como en el video: inyecta JavaMailSender y construye un SimpleMailMessage.
 */
@Service
public class SimpleEmailSender {

    private final JavaMailSender mailSender;

    @Autowired
    public SimpleEmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Envía un correo de texto plano.
     *
     * @param from    la dirección de correo remitente (por ejemplo, tu cuenta de Gmail)
     * @param to      el destinatario
     * @param subject el asunto del mensaje
     * @param body    el contenido de texto plano
     */
    public void sendSimpleEmail(String from, String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
