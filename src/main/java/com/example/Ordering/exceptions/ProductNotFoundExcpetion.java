package com.example.Ordering.exceptions;

public class ProductNotFoundExcpetion extends RuntimeException {
    public ProductNotFoundExcpetion(Long id) {
      super("Couldn't find Product: " + id);
    }
  }
