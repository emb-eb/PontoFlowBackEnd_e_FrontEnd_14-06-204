package com.pontoflowatual.pfw.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pontoflowatual.pfw.models.EmailRequest;
import com.pontoflowatual.pfw.services.EmailService;

@RestController
public class EmailController {
    
    @Autowired
    private EmailService emailService;
    
    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.enviarEmails(emailRequest.getRecipientEmail(), emailRequest.getSubject(), emailRequest.getBody());
            return ResponseEntity.ok("E-mail sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send e-mail: " + e.getMessage());
        }
    }
}
