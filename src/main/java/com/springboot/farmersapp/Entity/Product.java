package com.springboot.farmersapp.Entity;


import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String category;  
    private String availability;  
    private String restockingTime;
    private Boolean isOrganic;
    private Boolean isSeasonal;

    @ElementCollection
    private List<String> audioVideoContent; 

    // Constructors
    public Product() {}

    public Product(String name, String description, Double price, Integer quantity, String category, 
                   String availability, String restockingTime, Boolean isOrganic, Boolean isSeasonal, 
                   List<String> audioVideoContent) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.availability = availability;
        this.restockingTime = restockingTime;
        this.isOrganic = isOrganic;
        this.isSeasonal = isSeasonal;
        this.audioVideoContent = audioVideoContent;
    }

    // Getters and Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getRestockingTime() {
        return restockingTime;
    }

    public void setRestockingTime(String restockingTime) {
        this.restockingTime = restockingTime;
    }

    public Boolean getIsOrganic() {
        return isOrganic;
    }

    public void setIsOrganic(Boolean isOrganic) {
        this.isOrganic = isOrganic;
    }

    public Boolean getIsSeasonal() {
        return isSeasonal;
    }

    public void setIsSeasonal(Boolean isSeasonal) {
        this.isSeasonal = isSeasonal;
    }

    public List<String> getAudioVideoContent() {
        return audioVideoContent;
    }

    public void setAudioVideoContent(List<String> audioVideoContent) {
        this.audioVideoContent = audioVideoContent;
    }
}
