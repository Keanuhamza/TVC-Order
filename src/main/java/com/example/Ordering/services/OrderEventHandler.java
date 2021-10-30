package com.example.Ordering.services;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;

import com.example.Ordering.exceptions.*;
import com.example.Ordering.models.*;
import com.example.Ordering.repositories.*;

import org.apache.kafka.common.protocol.types.Field.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderEventHandler {
    @Autowired
    StreamBridge streamBridge;

    private final OrderRepository orderRepository;

    OrderEventHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // method call the product microservice and update the quantity
    public void updateQuantity(RestTemplate restTemplate, String productName, int quantity) throws Exception {
        String status = restTemplate.getForObject("http://localhost:8097/product/" + productName + "/updateQuantity/" + quantity, String.class);  
        System.out.println(status);
    }

    // handle the event of order being processed
    @EventListener
    public void handle(cOrder order) {
        System.out.println("handling event");
        // update quantity 
        try {
            updateQuantity(new RestTemplate(), order.getProductName(), order.getQuantity());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /// TODO send bridge request to BI-Order
     

       
        sendMessage(order);
        // save to reposito ry
        orderRepository.save(order);
    }

    public void sendMessage(cOrder order) {
        streamBridge.send("order-outbound", order);
    }

    

    

}