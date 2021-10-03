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
public class ViewProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViewProducerApplication.class, args);
	}
}
