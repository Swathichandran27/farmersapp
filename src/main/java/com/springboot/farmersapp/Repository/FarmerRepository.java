package com.springboot.farmersapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.farmersapp.Entity.Farmer;



@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    
}
