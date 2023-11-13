package com.assignment.controller;

import com.assignment.dto.EmailDetails;
import com.assignment.services.EmailSenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class EmailController {

    private final EmailSenderService emailSenderService;


    public EmailController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/email")
    public ResponseEntity triggerMail(@RequestBody EmailDetails details) throws MessagingException {
        System.out.println("calling triggerMail");

        emailSenderService.sendSimpleEmail(details);

        return ResponseEntity.ok().build();

    }
}
