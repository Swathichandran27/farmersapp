package com.springboot.farmersapp.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.farmersapp.Entity.Product;
import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);

    // Find products that are in stock
    List<Product> findByAvailability(String availability);

    // Find organic products
    List<Product> findByIsOrganicTrue();

    // Find seasonal products
    List<Product> findByIsSeasonalTrue();

    // Find products that have a price less than a given amount
    List<Product> findByPriceLessThan(Double price);

    // Find products with a price between a given range
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    // Find products containing a keyword in their name (case-insensitive search)
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchByName(@Param("keyword") String keyword);

    // Find all products that have a quantity greater than a given amount
    List<Product> findByQuantityGreaterThan(Integer quantity);
    // Fetch all products with pagination
    Page<Product> findAll(Pageable pageable);
    
    // Fetch products by category with pagination
    Page<Product> findByCategory(String category, Pageable pageable);
}    

