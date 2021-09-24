package com.example.Ordering.services;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.Ordering.exceptions.*;
import com.example.Ordering.models.*;
import com.example.Ordering.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderEventHandler {

    private final OrderRepository orderRepository;

    OrderEventHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void updateQuantity(RestTemplate restTemplate, String productName, int quantity) throws Exception {
        String status = restTemplate.getForObject("http://localhost:8097/product/" + productName + "/updateQuantity/" + quantity, String.class);  
        System.out.println(status);
    }

    @EventListener
    public void handle(cOrder order) {
        // update quantity 
        try {
            updateQuantity(new RestTemplate(), order.getProductName(), order.getQuantity());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // save to repository
        orderRepository.save(order);
    }

}