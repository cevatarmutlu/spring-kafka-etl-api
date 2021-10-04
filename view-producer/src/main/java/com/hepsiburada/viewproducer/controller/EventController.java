package com.hepsiburada.viewproducer.controller;

import com.hepsiburada.viewproducer.producer.KafkaProducer;
import com.hepsiburada.viewproducer.thread.EventThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/producer")
public class EventController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Value("${json.path}")
    private String jsonPath;

    @Value("${kafka.topic}")
    private String topic;

    @GetMapping("/start")
    public ResponseEntity<String> produce() {
        try {
            EventThread thread = EventThread.getInstance(kafkaProducer, topic, jsonPath);
            thread.start();
        } catch (Exception e) {
            return new ResponseEntity<>("Producer not working", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Producer Started", HttpStatus.OK);
    }

}
