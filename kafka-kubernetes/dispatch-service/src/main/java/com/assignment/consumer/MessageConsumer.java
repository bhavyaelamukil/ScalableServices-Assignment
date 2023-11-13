package com.assignment.consumer;


import com.assignment.dto.DispatchDetails;
import com.assignment.event.DemoInboundEvent;
import com.assignment.repo.DispatchRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.DataInput;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageConsumer {

    @Autowired
    DispatchRepository repository;


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
            repository.save(details);

        } catch (Exception e) {
            log.error("Error processing message: " + e.getMessage());
        }
    }
}
