package com.example.Ordering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class OrderingApplication {
	//private static final Logger log = LoggerFactory.getLogger(AswsApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(OrderingApplication.class, args);
		System.out.println("Ordering micro-service has started!");
	}	
}

	