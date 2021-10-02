package com.hepsiburada.streamreader.consumer;

import com.hepsiburada.streamreader.model.Event;
//import com.hepsiburada.streamreader.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class KafkaConsumer {

//    private final ProductService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "${kafka.topic}", id = "${kafka.group-id}")
    public void receive(@Payload Event event) {
        LOGGER.info("received data: {} ", event);

//        System.out.println(service.getCategoryId());

        latch.countDown();
//        System.out.println(new BrowsingHistoryGenerator(event, service).generate());

    }
}
