package se.lexicon.g51todoapi.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.lexicon.g51todoapi.domain.dto.EmailDTO;

@Service
public class EmailService {
    //2. Setup RestTemplate, URL
    private final RestTemplate restTemplate = new RestTemplate();
    private final String EMAIL_SERVICE_SEND_URL = "http://localhost:9090/api/v1/email";

    public HttpStatusCode sendRegistrationEmail(String registeredEmail) {
        //1. What is the email content
        EmailDTO dto = EmailDTO.builder()
                .to(registeredEmail)
                .subject("Welcome, you are now registered to the TODO application")
                .html("<p style='color: blue; font-size: 36px; text-align: center; background-color: lightgray; padding: 20px;'>Hello and welcome to our application. Please confirm your email.</p>")
                .build();
        //3. Send HTTP Request
        ResponseEntity<EmailDTO> responseEntity = sendEmail(dto);
        return responseEntity.getStatusCode();
    }

    public ResponseEntity<EmailDTO> sendEmail(EmailDTO emailDTO) {
        return restTemplate.exchange(EMAIL_SERVICE_SEND_URL, HttpMethod.POST, new HttpEntity<>(emailDTO), EmailDTO.class);
    }
}
