package com.niru.wikimedia.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootKafkaProducerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaProducerApplication.class, args);
	}

	@Autowired
	private WikimediaChangeProducer wikimediaChangeProducer;

	@Override
	public void run(String... args) throws Exception {
		wikimediaChangeProducer.sendMessage();
	}
}
