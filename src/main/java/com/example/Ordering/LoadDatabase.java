package com.example.Ordering;

import com.example.Ordering.repositories.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean @Order
  CommandLineRunner initDatabase(OrderRepository repository) {

    return args -> {
      

      log.info("Preloading " /*+ 
      repository.save(
        new com.example.Ordering.models.cOrder(
          1L, 40L, "33 Evergreen NSW 3088", 20L, "Hammer", 40f, 10
          ))*/); // preloading an order isn't necessary
      
      };
  } 

}