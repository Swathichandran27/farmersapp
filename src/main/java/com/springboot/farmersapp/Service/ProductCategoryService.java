package com.springboot.farmersapp.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.farmersapp.Entity.ProductCategory;
import com.springboot.farmersapp.Repository.ProductCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository categoryRepository;

    // Create a new category
    public ProductCategory createCategory(ProductCategory category) {
        return categoryRepository.save(category);
    }

    // Get all categories
    public List<ProductCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Get a category by ID
    public Optional<ProductCategory> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Update a category
    public ProductCategory updateCategory(Long id, ProductCategory updatedCategory) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setCategoryName(updatedCategory.getCategoryName());
                    category.setDescription(updatedCategory.getDescription());
                    return categoryRepository.save(category);
                }).orElseThrow(() -> new RuntimeException("Category not found!"));
    }

    // Delete a category
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}


