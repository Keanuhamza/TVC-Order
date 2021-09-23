package com.example.ASWS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AswsApplication {
	//private static final Logger log = LoggerFactory.getLogger(AswsApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(AswsApplication.class, args);
		System.out.println("Ordering micro-service has started!");
	}	
}

	