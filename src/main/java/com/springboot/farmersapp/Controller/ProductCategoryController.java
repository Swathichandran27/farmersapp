package com.springboot.farmersapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.farmersapp.Entity.ProductCategory;
import com.springboot.farmersapp.Service.ProductCategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService categoryService;

    // Create a new category
    @PostMapping
    public ResponseEntity<ProductCategory> createCategory(@RequestBody ProductCategory category) {
        ProductCategory createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    // Get all categories
    @GetMapping
    public ResponseEntity<List<ProductCategory>> getAllCategories() {
        List<ProductCategory> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getCategoryById(@PathVariable Long id) {
        Optional<ProductCategory> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update category
    @PutMapping("/{id}")
    public ResponseEntity<ProductCategory> updateCategory(@PathVariable Long id, @RequestBody ProductCategory updatedCategory) {
        try {
            ProductCategory category = categoryService.updateCategory(id, updatedCategory);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
