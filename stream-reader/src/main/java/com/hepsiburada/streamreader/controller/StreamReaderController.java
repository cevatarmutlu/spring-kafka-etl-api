package com.hepsiburada.streamreader.controller;

import com.hepsiburada.streamreader.consumer.KafkaConsumer;
import com.hepsiburada.streamreader.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consumer")
public class StreamReaderController {

    @Autowired
    KafkaConsumer kafkaConsumer;

    @GetMapping("aa")
    public Event consume() {
        return kafkaConsumer.getPayload();
    }

}
