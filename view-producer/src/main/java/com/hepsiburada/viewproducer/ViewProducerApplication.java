package com.hepsiburada.viewproducer;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.hepsiburada.viewproducer.model.Event;
import com.fasterxml.jackson.databind.MappingIterator;
import com.hepsiburada.viewproducer.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.sql.Timestamp;

@SpringBootApplication
public class ViewProducerApplication implements CommandLineRunner {

	@Value("${json.path}")
	String jsonPath;

	@Value("${kafka.topic}")
	private String topic;

	@Autowired
	KafkaProducer kafkaProducer;

	public static void main(String[] args) {
		SpringApplication.run(ViewProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		JsonMapper mapper = new JsonMapper();
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
