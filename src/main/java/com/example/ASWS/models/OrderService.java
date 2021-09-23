package com.example.ASWS.models;
import com.example.ASWS.repositories.*;
import com.example.ASWS.exceptions.*;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    
    private final OrderRepository repository;

    private ApplicationEventPublisher publisher;

    @Autowired
    public void QuoteService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    OrderService(OrderRepository repository) {
        this.repository = repository;
    }
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
      return builder.build(); 
    }

    public Customer sendRequestCustomer(RestTemplate restTemplate, Long i) throws Exception {
      Customer customer = restTemplate.getForObject("http://localhost:8094/customer/" + i, Customer.class);  
      return customer;
    }

    public float sendRequestCheckInventory(RestTemplate restTemplate, String productName, int quantity) throws Exception {
      float cost = restTemplate.getForObject("http://localhost:8097/product/" + productName + "/quantity/" + quantity, float.class);  
      return cost;
    }
   
    public String addOrder(cOrder order) {
        boolean successRetrieved = false;
        try {
          // validating customer ID
          Customer customer = sendRequestCustomer(new RestTemplate(), order.getCustID());
          successRetrieved = true;
          order.setCustAddress(customer.getCompanyName());
          order.setCustPhone(customer.getContact().getPhone());

          // check inventory
          float price = sendRequestCheckInventory(new RestTemplate(), order.getProductName(), order.getQuantity());
          
          switch ((int)price) {
            case  -1:
              return "Not enough stock for product: " + order.getProductName();
            case  -2:
              return "Product doesn't exist: " + order.getProductName();
            default:
              order.setProdPrice(price);
              break;
          }

          publisher.publishEvent(order);
          
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
