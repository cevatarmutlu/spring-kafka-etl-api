package com.hepsiburada.viewproducer.thread;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.hepsiburada.viewproducer.model.Event;
import com.hepsiburada.viewproducer.producer.KafkaProducer;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.sql.Timestamp;

@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class EventThread extends Thread{


    private final KafkaProducer kafkaProducer;
    private final String topic;
    private final String jsonPath;

    private static EventThread instance;

    public static EventThread getInstance(KafkaProducer kafkaProducer, String topic, String jsonPath) {
        if (instance == null)
            instance = new EventThread(kafkaProducer, topic, jsonPath);

        return instance;
    }

    @SneakyThrows
    @Override
    public void run() {
        JsonMapper mapper = new JsonMapper();
        System.out.println(jsonPath);
        File input = new File(jsonPath);

        MappingIterator<Event> it = mapper.readerFor(Event.class).readValues(input);

        while (it.hasNextValue()) {

            Event event = it.nextValue();
            event.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());

            kafkaProducer.send(topic, event);

            Thread.sleep(1000);
        }
    }
}
