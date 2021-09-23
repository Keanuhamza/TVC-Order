package com.example.ASWS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ASWS.models.Contact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class AswsApplication {
	//private static final Logger log = LoggerFactory.getLogger(AswsApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(AswsApplication.class, args);
		System.out.println("Ordering micro-service has started!");
	}
/*
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Contact quote = restTemplate.getForObject("http://localhost:8094/contact/", Contact.class);
			log.info(quote.toString());
		};
	} */



	
}

	