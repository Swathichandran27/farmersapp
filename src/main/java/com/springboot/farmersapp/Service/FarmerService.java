package com.springboot.farmersapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.farmersapp.Entity.Farmer;
import com.springboot.farmersapp.Entity.Product;
import com.springboot.farmersapp.Repository.FarmerRepository;
import com.springboot.farmersapp.Repository.ProductRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private ProductRepository productRepository;

    // CREATE a farmer with associated products
    public Farmer addFarmer(Farmer farmer) {
        if (farmer.getProducts() != null) {
            Set<Product> savedProducts = new HashSet<>(productRepository.saveAll(farmer.getProducts()));
            farmer.setProducts(savedProducts);
        }
        return farmerRepository.save(farmer);
    }

    // GET ALL FARMERS
    public List<Farmer> getAllFarmers() {
        return farmerRepository.findAll();
    }

    // GET FARMER BY ID
    public Optional<Farmer> getFarmerById(Long id) {
        return farmerRepository.findById(id);
    }

    // UPDATE FARMER
    public Farmer updateFarmer(Long id, Farmer farmerDetails) {
        Optional<Farmer> existingFarmerOpt = farmerRepository.findById(id);
        if (existingFarmerOpt.isPresent()) {
            Farmer existingFarmer = existingFarmerOpt.get();

            if (farmerDetails.getFarmName() != null)
                existingFarmer.setFarmName(farmerDetails.getFarmName());
            if (farmerDetails.getFarmDescription() != null)
                existingFarmer.setFarmDescription(farmerDetails.getFarmDescription());
            if (farmerDetails.getFarmLocation() != null)
                existingFarmer.setFarmLocation(farmerDetails.getFarmLocation());
            if (farmerDetails.getContactInfo() != null)
                existingFarmer.setContactInfo(farmerDetails.getContactInfo());
            if (farmerDetails.getRating() != null)
                existingFarmer.setRating(farmerDetails.getRating());
            if (farmerDetails.getAudioVideoContent() != null)
                existingFarmer.setAudioVideoContent(farmerDetails.getAudioVideoContent());

            // Update associated products if provided
            if (farmerDetails.getProducts() != null) {
                Set<Product> savedProducts = new HashSet<>(productRepository.saveAll(farmerDetails.getProducts()));
                existingFarmer.setProducts(savedProducts);
            }

            return farmerRepository.save(existingFarmer);
        }
        return null;
    }

    // DELETE FARMER
    public void deleteFarmer(Long id) {
        farmerRepository.deleteById(id);
    }

    // GET ALL PRODUCTS OF A FARMER
    public Set<Product> getProductsByFarmerId(Long farmerId) {
        return farmerRepository.findById(farmerId).map(Farmer::getProducts).orElse(new HashSet<>());
    }
}
