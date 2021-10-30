package com.example.Ordering.services;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.Ordering.exceptions.*;
import com.example.Ordering.models.*;
import com.example.Ordering.repositories.*;

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

    // get customer
    public Customer sendRequestCustomer(RestTemplate restTemplate, Long i) throws Exception {
      Customer customer = restTemplate.getForObject("http://localhost:8094/customer/" + i, Customer.class);  
      return customer;
    }

    // check for inventory from inventory and get price
    public float sendRequestCheckInventory(RestTemplate restTemplate, String productName, int quantity) throws Exception {
      float cost = restTemplate.getForObject("http://localhost:8097/product/" + productName + "/quantity/" + quantity, float.class);  
      return cost;
    }

    // get product
    public Product sendRequestProduct(RestTemplate restTemplate, String productName) throws Exception {
      Product prod = restTemplate.getForObject("http://localhost:8097/productN/" + productName, Product.class);  
      return prod;
    }
    
    // process a new order
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
              throw new NotEnoughStockException(order.getProductName());
            case  -2:
              throw new OrderNameNotFoundException(order.getProductName());
            default:
              order.setProdPrice(price);
              break;
          }
          
          // handle event
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

    // method to get a customer
    public Customer getCustomer(Long id) {
      Long custID = repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id)).getCustID();
      
      try {
        Customer customer = sendRequestCustomer(new RestTemplate(), custID);
        return customer;
      } catch (Exception e) {
        e.printStackTrace();
      }
      return null;   
    }

    // method to get product
    public Product getProduct(Long id) {
      String productName = repository.findById(id).orElseThrow(() -> new ProductNotFoundExcpetion(id)).getProductName();
      try {
        Product product = sendRequestProduct(new RestTemplate(),productName);
        return product;
      } catch (Exception e) {
        e.printStackTrace();
      }
      return null;   
    }

    public cOrder getOrder(Long id) {   
        return repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    public List<cOrder> getAllOrders() {   
        return repository.findAll();
    }

    public void deleteOrder(Long id) {   
        repository.deleteById(id);
    }

}
