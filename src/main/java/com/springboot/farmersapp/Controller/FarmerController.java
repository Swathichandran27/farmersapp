package com.springboot.farmersapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springboot.farmersapp.Entity.Farmer;
import com.springboot.farmersapp.Service.FarmerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    // CREATE (POST) - Save farmer along with products
    @PostMapping
    public Farmer addFarmer(@RequestBody Farmer farmer) {
        return farmerService.addFarmer(farmer);
    }

    // GET ALL FARMERS
    @GetMapping
    public List<Farmer> getAllFarmers() {
        return farmerService.getAllFarmers();
    }

    // GET FARMER BY ID
    @GetMapping("/{id}")
    public Optional<Farmer> getFarmerById(@PathVariable Long id) {
        return farmerService.getFarmerById(id);
    }

    // UPDATE FARMER
    @PutMapping("/{id}")
    public Farmer updateFarmer(@PathVariable Long id, @RequestBody Farmer farmerDetails) {
        return farmerService.updateFarmer(id, farmerDetails);
    }

    // DELETE FARMER
    @DeleteMapping("/{id}")
    public void deleteFarmer(@PathVariable Long id) {
        farmerService.deleteFarmer(id);
    }
}
