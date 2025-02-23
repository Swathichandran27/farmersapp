package com.springboot.farmersapp.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.farmersapp.Entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Additional custom queries can be defined here if needed.
}
