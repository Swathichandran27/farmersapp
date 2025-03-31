package com.springboot.farmersapp.Controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.farmersapp.Entity.Product;
import com.springboot.farmersapp.Repository.ProductRepository;
import com.springboot.farmersapp.Service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepo;

    // Create a new product and associate with farmers
    @PostMapping
    public ResponseEntity<Product> createProduct(
        @RequestBody Product product, 
        @RequestParam Set<Long> farmerIds) {
        
        System.out.println("Received request to create product: " + product.getName());
        
        Product savedProduct = productService.createProduct(product, farmerIds);
        return ResponseEntity.ok(savedProduct);
    }


    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    // Get a product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a product with farmer associations
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id, 
            @RequestBody Product updatedProduct, 
            @RequestParam Set<Long> farmerIds) {
        Product product = productService.updateProduct(id, updatedProduct, farmerIds);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

   

    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> getInStockProducts() {
        return ResponseEntity.ok(productService.getInStockProducts());
    }

    @GetMapping("/organic")
    public ResponseEntity<List<Product>> getOrganicProducts() {
        return ResponseEntity.ok(productService.getOrganicProducts());
    }

    @GetMapping("/price-below/{price}")
    public ResponseEntity<List<Product>> getProductsBelowPrice(@PathVariable Double price) {
        return ResponseEntity.ok(productService.getProductsBelowPrice(price));
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsInPriceRange(@RequestParam Double min, @RequestParam Double max) {
        return ResponseEntity.ok(productService.getProductsInPriceRange(min, max));
    }

    

    // Get paginated products
    @GetMapping("/paginated")
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    @GetMapping("/paginated/category/{category}")
    public ResponseEntity<Page<Product>> getProductsByCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(productService.getProductsByCategory(category, pageable));
    }

    // Get products by farmer ID
    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<Product>> getProductsByFarmerId(@PathVariable Long farmerId) {
        return ResponseEntity.ok(productService.getProductsByFarmerId(farmerId));
    }
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String keyword) {
        return ResponseEntity.ok(productService.searchProductsByName(keyword));
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

   
}
