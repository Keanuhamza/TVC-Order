package com.example.Ordering;

import com.example.Ordering.models.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.function.Consumer;

@SpringBootApplication
public class OrderingApplication {
	private static final Logger log = LoggerFactory.getLogger(OrderingApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(OrderingApplication.class, args);
		System.out.println("Ordering micro-service has started!");
	}
	@Bean
	public CommandLineRunner run(StreamBridge streamBridge) throws Exception {
		return args -> {
			Long i = 1L;
			try {
				while (i < 20L){
					//RNG for order
					Random rng = new Random();
					int min = 1;
					int max = 5;
					int upperBound = max - min + 1;
					long rNum1 = min + rng.nextInt(upperBound);
					//Generates random product name from array
					min = 0;
					max = 1;
					upperBound = max - min + 1;
					int rNum2 = min + rng.nextInt(upperBound);
					String stor[] = {"Hammer","Steel copper alloy"};

					System.out.println(rNum1 + ": " + stor[rNum2]);

					cOrder order = new cOrder(i, rNum1, stor[rNum2], 1);
					log.info(order.toString());
					//The binder name "appliance-outbound" is defined in the application.yml.
					streamBridge.send("appliance-outbound", order);

					i = i + 1L;
				}
			}
			catch(Exception ignored){}
			return;
		};
	}
}

	