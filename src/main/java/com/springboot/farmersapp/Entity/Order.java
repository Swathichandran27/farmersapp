package com.springboot.farmersapp.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId; 

    private Long userId; 
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private String deliveryAddress;
    private String deliveryOption;
    private LocalDateTime deliverySchedule;
    private String orderStatus;
    private String paymentStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> orderItems;

    public Order() {
        this.orderDate = LocalDateTime.now();
    }

    public Order(Long userId, BigDecimal totalAmount, String deliveryAddress, String deliveryOption, 
                 LocalDateTime deliverySchedule, String orderStatus, String paymentStatus, List<OrderItem> orderItems) {
        this.userId = userId;
        this.orderDate = LocalDateTime.now();
        this.totalAmount = totalAmount;
        this.deliveryAddress = deliveryAddress;
        this.deliveryOption = deliveryOption;
        this.deliverySchedule = deliverySchedule;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
        this.orderItems = orderItems;
    }

    // Getters and Setters
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }

    public String getDeliveryOption() { return deliveryOption; }
    public void setDeliveryOption(String deliveryOption) { this.deliveryOption = deliveryOption; }

    public LocalDateTime getDeliverySchedule() { return deliverySchedule; }
    public void setDeliverySchedule(LocalDateTime deliverySchedule) { this.deliverySchedule = deliverySchedule; }

    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
}
