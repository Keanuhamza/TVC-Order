package com.example.ASWS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ASWS.models.cOrder;

public interface OrderRepository extends JpaRepository<cOrder, Long> {

}
