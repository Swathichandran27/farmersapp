package com.springboot.farmersapp.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.farmersapp.Entity.Order;
import com.springboot.farmersapp.Repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Create a new order
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    // Retrieve all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Retrieve an order by its ID
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    // Update an order by ID
    public Order updateOrder(Long orderId, Order updatedOrder) {
        return orderRepository.findById(orderId)
            .map(order -> {
                order.setUserId(updatedOrder.getUserId());
                // Typically, orderDate is not updated once created.
                order.setTotalAmount(updatedOrder.getTotalAmount());
                order.setDeliveryAddress(updatedOrder.getDeliveryAddress());
                order.setDeliveryOption(updatedOrder.getDeliveryOption());
                order.setDeliverySchedule(updatedOrder.getDeliverySchedule());
                order.setOrderStatus(updatedOrder.getOrderStatus());
                order.setPaymentStatus(updatedOrder.getPaymentStatus());
                return orderRepository.save(order);
            })
            .orElseThrow(() -> new RuntimeException("Order not found with id " + orderId));
    }

    // Delete an order by ID
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
    public Page<Order> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
}
