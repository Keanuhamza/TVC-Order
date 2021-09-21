package com.example.ASWS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AswsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AswsApplication.class, args);
		System.out.println("Ordering micro-service has started!");
	}
	
}

