package com.springboot.farmersapp.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.springboot.farmersapp.Entity.Product;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
   
    List<Product> findByAvailability(String availability);
   
    // Find products by farmer ID
    @Query("SELECT p FROM Product p JOIN p.farmers f WHERE f.farmerId = :farmerId")
    List<Product> findProductsByFarmerId(@Param("farmerId") Long farmerId);

    List<Product> findByIsOrganicTrue();

    List<Product> findByPriceLessThan(Double price);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    Page<Product> findAll(Pageable pageable);

    Page<Product> findByCategory(String category, Pageable pageable);


    List<Product> findByCategory(String category);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchByName(@Param("keyword") String keyword);
    
}
