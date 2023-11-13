package com.assignment.producer;

import com.assignment.properties.KafkaDemoProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducer {

    @Autowired
    private final KafkaDemoProperties properties;

    @Autowired
    private final KafkaTemplate kafkaTemplate;

//    public SendResult sendMessage(String topic, String message) throws Exception {
//        System.out.println("Sending message to kafka " + topic + message);
//         final ProducerRecord<String, Object> record = new ProducerRecord<>(properties.getOutboundTopic(), topic, message);
//        //kafkaTemplate.send(topic, message);
//        return (SendResult) kafkaTemplate.send(record).get();
//    }
    public SendResult sendMessage(String key, Object payload) throws Exception {
        final ProducerRecord<String, Object> record = new ProducerRecord<>(properties.getOutboundTopic(), key, payload);
        return (SendResult) kafkaTemplate.send(record).get();
    }

    public void sendMessage(String topic, String message) throws Exception {
        System.out.println("Sending message to kafka " + topic + message);
        final ProducerRecord<String, Object> record = new ProducerRecord<>(properties.getOutboundTopic(), topic, message);
        kafkaTemplate.send(topic, message);
       // return (SendResult) kafkaTemplate.send(record).get();
    }

}

