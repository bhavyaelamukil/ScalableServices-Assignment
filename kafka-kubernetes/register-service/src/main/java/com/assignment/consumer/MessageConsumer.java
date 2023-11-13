//package com.assignment.consumer;
//
//
//import com.assignment.event.DemoInboundEvent;
//import com.assignment.services.RegisterService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class MessageConsumer {
//
//    final RegisterService registerService;
//
//    @KafkaListener(
//            topics = "#{'${kafka.inboundTopic}'}",
//            groupId = "demo-consumer-group",
//            containerFactory = "kafkaListenerContainerFactory")
//    public void listen(@Payload DemoInboundEvent event) {
//        log.info("Received message - event: " + event);
//        try {
//            registerService.process(event);
//        } catch (Exception e) {
//            log.error("Error processing message: " + e.getMessage());
//        }
//    }
//}
