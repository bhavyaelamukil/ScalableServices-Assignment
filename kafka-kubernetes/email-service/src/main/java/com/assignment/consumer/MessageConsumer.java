package com.assignment.consumer;


import com.assignment.dto.DispatchDetails;
import com.assignment.dto.EmailDetails;
import com.assignment.services.EmailSenderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageConsumer {

    @Autowired
    final EmailSenderService emailSenderService;

    @KafkaListener(
            topics = "dispatch",
            groupId = "demo-consumer-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(String dispatchDetails) {
        System.out.println("dispatch details -> " + dispatchDetails);
        log.debug("Received message - event: " + dispatchDetails);
        try {
            ObjectMapper mapper = new ObjectMapper();
            DispatchDetails details = mapper.readValue( dispatchDetails, DispatchDetails.class);
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setEmail(details.getEmail());
            emailDetails.setUserId(details.getUserId());
            emailSenderService.sendSimpleEmail(emailDetails);

        } catch (Exception e) {
            log.error("Error processing message: " + e.getMessage());
        }
    }
}
