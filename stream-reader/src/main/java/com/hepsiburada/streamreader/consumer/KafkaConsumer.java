package com.hepsiburada.streamreader.consumer;

import com.hepsiburada.streamreader.generator.BrowsingHistoryGenerator;
import com.hepsiburada.streamreader.model.BrowsingHistory;
import com.hepsiburada.streamreader.model.Event;
//import com.hepsiburada.streamreader.service.ProductService;
import com.hepsiburada.streamreader.service.BrowsingHistoryService;
import com.hepsiburada.streamreader.service.ProductService;
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

    private final ProductService productService;
    private final BrowsingHistoryService historyService;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private CountDownLatch latch = new CountDownLatch(1);
    private Event payload = null;
    private BrowsingHistory history = null;

    @KafkaListener(topics = "${kafka.topic}", id = "${kafka.group-id}")
    public void receive(@Payload Event payload) {
        LOGGER.info("received data: {} ", payload);

        BrowsingHistoryGenerator generator = new BrowsingHistoryGenerator(payload, productService);
        BrowsingHistory history = generator.generate();
        historyService.saveOrUpdate(history);

        setPayload(payload);
        setHistory(history);
        LOGGER.info("write data: {} ", history);

        latch.countDown();
    }
}
