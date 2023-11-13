package com.assignment.services;

import com.assignment.dto.DispatchDetails;
import com.assignment.dto.EmailDetails;
import com.assignment.producer.MessageProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterService {

    @Autowired
    private final MessageProducer kafkaProducer;
    @Autowired
    private final ObjectMapper objectMapper;

    /**
     * Sends the requested number of events.
     */
    public void process(DispatchDetails details) throws Exception {
            sendEvent(details);
    }

    /**
     * Send an event choosing one of three keys randomly.
     */
    private void sendEvent(DispatchDetails details) throws Exception {
        String dispatchAsMessage = objectMapper.writeValueAsString(details);
        System.out.println("outbound Event " + dispatchAsMessage);
        kafkaProducer.sendMessage("dispatch", dispatchAsMessage);
        kafkaProducer.sendMessage("email", dispatchAsMessage);
        log.debug("Sent message with key {}.", dispatchAsMessage);
    }



}
