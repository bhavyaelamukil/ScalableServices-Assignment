package com.assignment.consumer;

import com.assignment.dto.DispatchDetails;
import com.assignment.repo.DispatchRepository;
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
    DispatchRepository repository;
//
//    @KafkaListener(topics = "NewUserRegister", groupId = "MarathonRegister")
//    public void listen(String message) {
//        System.out.println("Received message: " + message);
//    }

    //@RetryableTopic(attempts = "3", backoff = @Backoff(delay = 5000, multiplier = 3.0))
    @KafkaListener(topics = "register",
            groupId = "group_id",
            containerFactory = "myConsumerFactory")
    public void consume(String msg, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws JsonProcessingException {

        System.out.println(msg + " from  dispatch service " + topic);

        ObjectMapper mapper = new ObjectMapper();
        DispatchDetails details = mapper.readValue(msg, DispatchDetails.class);
        repository.save(details);


    }

    }
