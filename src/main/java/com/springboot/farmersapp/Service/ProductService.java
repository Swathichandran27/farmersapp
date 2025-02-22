package com.springboot.farmersapp.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.farmersapp.Entity.Product;
import com.springboot.farmersapp.Repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Create a new product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Read all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Read a product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Update a product
    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            updatedProduct.setProductId(id); // Retain the original productId
            return productRepository.save(updatedProduct);
        } else {
            return null; // Product not found
        }
    }

    // Delete a product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getInStockProducts() {
        return productRepository.findByAvailability("In Stock");
    }

    public List<Product> getOrganicProducts() {
        return productRepository.findByIsOrganicTrue();
    }

    public List<Product> getSeasonalProducts() {
        return productRepository.findByIsSeasonalTrue();
    }

    public List<Product> getProductsBelowPrice(Double price) {
        return productRepository.findByPriceLessThan(price);
    }

    public List<Product> getProductsInPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> searchProductsByName(String keyword) {
        return productRepository.searchByName(keyword);
    }

    public List<Product> getProductsWithHighQuantity(Integer quantity) {
        return productRepository.findByQuantityGreaterThan(quantity);
    }
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> getProductsByCategory(String category, Pageable pageable) {
        return productRepository.findByCategory(category, pageable);
    }
}
