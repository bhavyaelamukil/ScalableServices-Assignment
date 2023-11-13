package com.assignment.consumer;

import com.assignment.dto.EmailDetails;
import com.assignment.services.EmailSenderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    @Autowired
    EmailSenderService emailSenderService;


   // @RetryableTopic(attempts = "3", backoff = @Backoff(delay = 5000, multiplier = 3.0))
    @KafkaListener(topics = "notify",
            groupId = "group_id",
            containerFactory = "myConsumerFactory")
    public void consume(String msg, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws JsonProcessingException {

        System.out.println(msg + " from email service" + topic);
        ObjectMapper mapper = new ObjectMapper();
        EmailDetails emailDetails = mapper.readValue(msg, EmailDetails.class);
        emailSenderService.sendSimpleEmail(emailDetails);


    }

    }
