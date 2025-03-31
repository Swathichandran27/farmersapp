package com.springboot.farmersapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.springboot.farmersapp.Entity.Farmer;
import com.springboot.farmersapp.Entity.Product;
import com.springboot.farmersapp.Repository.FarmerRepository;
import com.springboot.farmersapp.Repository.ProductRepository;

import jakarta.transaction.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FarmerRepository farmerRepository;

    // Create a new product and assign farmers
    @Transactional
    public Product createProduct(Product product, Set<Long> farmerIds) {
        Set<Farmer> farmers = new HashSet<>(farmerRepository.findAllById(farmerIds));
    
        // Ensure bidirectional mapping
        for (Farmer farmer : farmers) {
            farmer.getProducts().add(product);
        }
    
        product.setFarmers(farmers);
    
        Product savedProduct = productRepository.save(product);
        
        // Save farmers to update the relationship in DB
        farmerRepository.saveAll(farmers);
        return savedProduct;
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
    public Product updateProduct(Long id, Product updatedProduct, Set<Long> farmerIds) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            updatedProduct.setProductId(id);
            Set<Farmer> farmers = new HashSet<>(farmerRepository.findAllById(farmerIds));
            updatedProduct.setFarmers(farmers);
            return productRepository.save(updatedProduct);
        } else {
            return null;
        }
    }

    // Delete a product
    public String deleteProduct(Long id) {
        productRepository.deleteById(id);
        return "Product deleted successfully";
    }

    public List<Product> getInStockProducts() {
        return productRepository.findByAvailability("In Stock");
    }

    public List<Product> getOrganicProducts() {
        return productRepository.findByIsOrganicTrue();
    }

    public List<Product> getProductsBelowPrice(Double price) {
        return productRepository.findByPriceLessThan(price);
    }

    public List<Product> getProductsInPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> getProductsByCategory(String category, Pageable pageable) {
        return productRepository.findByCategory(category, pageable);
    }

    // Get products by farmer ID
    public List<Product> getProductsByFarmerId(Long farmerId) {
        return productRepository.findProductsByFarmerId(farmerId);
    }

    
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
    public List<Product> searchProductsByName(String keyword) {
        return productRepository.searchByName(keyword);
    }

}
