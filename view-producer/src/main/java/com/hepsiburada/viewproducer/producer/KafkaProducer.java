package com.hepsiburada.viewproducer.producer;


import com.hepsiburada.viewproducer.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, Event> kafkaTemplate;

    public void send(String topic, Event payload) {
        LOGGER.info("sending payload='{}' to topic='{}'", payload, topic);
        payload.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
        kafkaTemplate.send(topic, payload);
    }

}