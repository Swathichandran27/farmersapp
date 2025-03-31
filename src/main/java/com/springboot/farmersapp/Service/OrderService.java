package com.springboot.farmersapp.Service;

import com.springboot.farmersapp.Entity.Order;
import com.springboot.farmersapp.Entity.OrderItem;
import com.springboot.farmersapp.Repository.OrderItemRepository;
import com.springboot.farmersapp.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemrepository;
    // Retrieve all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Retrieve order by ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Create a new order
    public Order createOrder(Order order) {
        // Ensure order date is set
        if (order.getOrderDate() == null) {
            order.setOrderDate(java.time.LocalDateTime.now());
        }
        return orderRepository.save(order);
    }

    // Update an existing order
    public Order updateOrder(Long orderId, Order updatedOrder) {
        // Fetch the existing order from the database
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    
        // Update basic order details
        existingOrder.setUserId(updatedOrder.getUserId());
        existingOrder.setTotalAmount(updatedOrder.getTotalAmount());
        existingOrder.setDeliveryAddress(updatedOrder.getDeliveryAddress());
        existingOrder.setDeliveryOption(updatedOrder.getDeliveryOption());
        existingOrder.setDeliverySchedule(updatedOrder.getDeliverySchedule());
        existingOrder.setOrderStatus(updatedOrder.getOrderStatus());
        existingOrder.setPaymentStatus(updatedOrder.getPaymentStatus());
    
        // Handle order items correctly
        if (updatedOrder.getOrderItems() != null) {
            for (OrderItem updatedItem : updatedOrder.getOrderItems()) {
                if (updatedItem.getId() != null) {
                    // Fetch existing OrderItem
                    OrderItem existingItem = orderItemrepository.findById(updatedItem.getId())
                            .orElseThrow(() -> new RuntimeException("OrderItem not found"));
                    
                    // Update values
                    existingItem.setProductId(updatedItem.getProductId());
                    existingItem.setQuantity(updatedItem.getQuantity());
                    existingItem.setPrice(updatedItem.getPrice());
                } else {
                    // If no ID, it's a new item, so associate it with the order
                    updatedItem.setOrder(existingOrder);
                    existingOrder.getOrderItems().add(updatedItem);
                }
            }
        }
    
        // Save and return the updated order
        return orderRepository.save(existingOrder);
    }
    
    // Delete an order
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    // Get all items in a specific order
    public List<OrderItem> getOrderItems(Long orderId) {
        return orderRepository.findById(orderId)
                .map(Order::getOrderItems)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
