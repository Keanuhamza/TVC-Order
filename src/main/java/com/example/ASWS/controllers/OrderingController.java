package com.example.ASWS.controllers;

import java.util.List;

import com.example.ASWS.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderingController {

  @Autowired
  private OrderService orderService;

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/orders")
  List<cOrder> all() {
    return orderService.getAllOrders();
  }
  // end::get-aggregate-root[]

  @PostMapping("/order")
  cOrder newOrder(@RequestBody cOrder newOrder) {
    return orderService.addOrder(newOrder);
  }

  // Single item
  @GetMapping("/order/{id}")
  cOrder one(@PathVariable Long id) {
    return orderService.getOrder(id);
  }

  @PutMapping("/order/{id}")
  cOrder replaceOrder(@RequestBody cOrder order, @PathVariable Long id) {
    return orderService.updateOrder(order, id);
  }

  @DeleteMapping("/order/{id}")
  void deleteOrder(@PathVariable("id") Long id) {
    orderService.deleteOrder(id);
  }
}
