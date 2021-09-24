package com.example.Ordering.repositories;

import com.example.Ordering.models.cOrder;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<cOrder, Long> {

}
