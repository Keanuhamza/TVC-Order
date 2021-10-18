package com.example.Ordering.exceptions;

public class NotEnoughStockException extends RuntimeException {
    public NotEnoughStockException(String id) {
      super("Not enough stock for: " + id);
    }
  }
