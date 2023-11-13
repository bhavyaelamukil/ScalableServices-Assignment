package com.assignment.services;

import com.assignment.dto.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    private final String fromEmail = "2022mt93171@wilp.bits-pilani.ac.in";
    private final String emailSubject = "Registered successfully!!";
    private final String emailMessage = "All the best!!!";

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(EmailDetails details) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(fromEmail);
        message.setTo(details.getToEmail());
        message.setText(emailMessage);
        message.setSubject(emailSubject);

        mailSender.send(message);
        System.out.println("Mail Send...");
    }

}
