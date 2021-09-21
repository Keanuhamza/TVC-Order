package com.example.ASWS.models;
import com.example.ASWS.repositories.*;
import com.example.ASWS.exceptions.*;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    
    private final OrderRepository repository;

    OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order addOrder(Order order) {   
        return repository.save(order);
    }

    public Order getOrder(Long id) {   
        return repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    public List<Order> getAllOrders() {   
        return repository.findAll();
    }

    public Order updateOrder(Order newOrder, Long id) {
    return repository.findById(id)
      .map(order -> {
        order.setCustID(newOrder.getCustID());
        order.setProductName(newOrder.getProductName());
        order.setQuantity(newOrder.getQuantity());
        return repository.save(order);
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
