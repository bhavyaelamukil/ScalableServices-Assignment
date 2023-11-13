//package com.assignment.consumer;
//
//import com.assignment.dto.DispatchDetails;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.annotation.RetryableTopic;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.retry.annotation.Backoff;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MessageConsumer {
//
//
//    @RetryableTopic(attempts = "3", backoff = @Backoff(delay = 5000, multiplier = 3.0))
//    @KafkaListener(topics = "register",
//            groupId = "group_id",
//            containerFactory = "myConsumerFactory")
//    public void consume(String msg, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
//
//        System.out.println(msg + " from register service" + topic);
//
//        // int x = Integer.parseInt(msg);
//    }
//
//    }
