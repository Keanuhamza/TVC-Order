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

    public cOrder addOrder(cOrder order) {   
        return repository.save(order);
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
