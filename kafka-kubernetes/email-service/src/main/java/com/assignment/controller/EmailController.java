package com.assignment.controller;

import com.assignment.dto.EmailDetails;
import com.assignment.services.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/email")
public class EmailController {

    private final EmailSenderService emailSenderService;

    @PostMapping("/email")
    public ResponseEntity triggerMail(@RequestBody EmailDetails details) throws MessagingException {
        System.out.println("calling triggerMail");

        emailSenderService.sendSimpleEmail(details);

        return ResponseEntity.ok().build();

    }

    @GetMapping("/")
    public void test(){
        System.out.println("working");
    }

    @GetMapping("/version")
    public ResponseEntity<String> version() {
        return ResponseEntity.ok("v1");
    }
}
