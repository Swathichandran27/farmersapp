package com.springboot.farmersapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.farmersapp.Entity.Order;
import com.springboot.farmersapp.Entity.OrderItem;
import com.springboot.farmersapp.Repository.OrderItemRepository;
import com.springboot.farmersapp.Repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    
    public OrderItem createOrderItem(Long orderId, OrderItem orderItem) {
        return orderRepository.findById(orderId).map(order -> {
            orderItem.setOrder(order); // Assign Order reference
            return orderItemRepository.save(orderItem);
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public OrderItem updateOrderItem(Long id, OrderItem orderItemDetails) {
        return orderItemRepository.findById(id).map(orderItem -> {
            orderItem.setProductId(orderItemDetails.getProductId());
            orderItem.setQuantity(orderItemDetails.getQuantity());
            orderItem.setPrice(orderItemDetails.getPrice());
            return orderItemRepository.save(orderItem);
        }).orElseThrow(() -> new RuntimeException("Order Item not found"));
    }

    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}
