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
	public CommandLineRunner run() throws Exception {
		return args -> {
			Long i = 1L;
			try {
				while (!Thread.currentThread().isInterrupted()){
					//RNG for order
					Random rng = new Random();
					int min = 1;
					int max = 5;
					int upperBound = max - min + 1;
					long rCustomer = min + rng.nextInt(upperBound);
					//Generates random product name from array
					min = 0;
					max = 4;
					upperBound = max - min + 1;
					int rProudct = min + rng.nextInt(upperBound);
					min = 1;
					max = 10;
					int rQuantity = min + rng.nextInt(upperBound);

					String[] productList = {"Hammer","Spanner","Cake","Apple","Orange"};

					System.out.println(rCustomer + ": " + productList[rProudct]);

					cOrder order = new cOrder(i, rCustomer, productList[rProudct], rQuantity);
					log.info(order.toString());
					//The binder name "appliance-outbound" is defined in the application.yml.
					postOrder(new RestTemplate(), order);

					i++;
					Thread.sleep(1200);
				}
			}
			catch(Exception ignored){}
			return;
		};
	}

	// get customer
    public void postOrder(RestTemplate restTemplate, cOrder order) throws Exception {

		//String message = restTemplate.getForObject("http://localhost:8181/order" + order, String.class);  
		String message = restTemplate.postForObject("http://localhost:8181/order", order, String.class);

		System.out.println(message);
	}
}

	