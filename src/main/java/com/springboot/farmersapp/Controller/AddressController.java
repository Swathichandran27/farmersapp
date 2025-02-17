package com.springboot.farmersapp.Controller;

import com.springboot.farmersapp.Entity.Address;
import com.springboot.farmersapp.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // CREATE Address
    @PostMapping
    public Address createAddress(@RequestBody Address address) {
        return addressService.createAddress(address);
    }

    // GET All Addresses
    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    // GET Address by ID
   @GetMapping("/{id}")
    public Address getAddressById(@PathVariable int id) {
        return addressService.getAddressById(id);
    }

   // UPDATE Address
    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable int id, @RequestBody Address address) {
        return addressService.updateAddress(id, address);
    }

    // DELETE Address
    @DeleteMapping("/{id}")
    public String deleteAddress(@PathVariable int id) {
        return addressService.deleteAddress(id);
    }
}
