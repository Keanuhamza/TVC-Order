package com.example.Ordering.exceptions;

public class OrderNameNotFoundException extends RuntimeException {
    public OrderNameNotFoundException(String id) {
      super("Could not find order name: " + id);
    }
  }
