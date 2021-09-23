package com.example.ASWS.models;
import com.example.ASWS.repositories.*;
import com.example.ASWS.exceptions.*;
import com.example.ASWS.models.Contact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    
    private final OrderRepository repository;

    OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
      return builder.build();
	  
    }

    public Customer sendRequest(RestTemplate restTemplate, Long i) throws Exception {
      Customer customer = restTemplate.getForObject("http://localhost:8094/customer/" + i, Customer.class);  
      return customer;
    }
   
    public String addOrder(cOrder order) {
        boolean successRetrieved = false;
        try {
          Customer customer = sendRequest(new RestTemplate(), order.getCustID());
          successRetrieved = true;
          order.setCustAddress(customer.getCompanyName());
          order.setCustPhone(customer.getContact().getPhone());
          repository.save(order);
          return "Successfully added order: " + order.toString();
        } catch (Exception e) {
          e.printStackTrace();
          if (successRetrieved) {
            return "Error, Customer does not have Contact details";
          }
          return "Error, Customer ID not valid";
        }
    }

    public cOrder getOrder(Long id) {   
        return repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    public List<cOrder> getAllOrders() {   
        return repository.findAll();
    }

    public cOrder updateOrder(cOrder newOrder, Long id) {
    return repository.findById(id)
      .map(cOrder -> {
        cOrder.setCustID(newOrder.getCustID());
        cOrder.setProductName(newOrder.getProductName());
        cOrder.setQuantity(newOrder.getQuantity());
        return repository.save(cOrder);
      })
      .orElseGet(() -> {
        newOrder.setId(id);
        return repository.save(newOrder);
      });
    } 

    public void deleteOrder(Long id) {   
        repository.deleteById(id);
    }

}
