package com.springboot.farmersapp.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.farmersapp.Entity.Order;
import java.util.*;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
     /*List<Order> findByUserId(Long userId);

    
    @Query("SELECT o FROM Order o WHERE o.orderStatus = :orderStatus")
    List<Order> findOrdersByOrderStatus(@Param("orderStatus") String orderStatus);*/
}
