package com.example.ASWS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ASWS.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}