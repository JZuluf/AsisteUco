package co.edu.uco.asisteuco.application.service;

import co.edu.uco.asisteuco.application.outputport.dto.EmailRequestDTO;

public interface EmailSenderService {
    void sendEmail(EmailRequestDTO emailRequest) throws Exception;
}