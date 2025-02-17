package com.springboot.farmersapp.Service;

import com.springboot.farmersapp.Entity.Address;
import com.springboot.farmersapp.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    // CREATE Address
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    // GET All Addresses
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    // GET Address by ID
    public Address getAddressById(int id) {
        return addressRepository.findById(id).orElse(null);
    }

    // UPDATE Address
    public Address updateAddress(int id, Address updatedAddress) {
        return addressRepository.findById(id).map(address -> {
            address.setStreet(updatedAddress.getStreet());
            address.setCity(updatedAddress.getCity());
            address.setState(updatedAddress.getState());
            address.setZipCode(updatedAddress.getZipCode());
            address.setCountry(updatedAddress.getCountry());
            return addressRepository.save(address);
        }).orElse(null);
    }

    // DELETE Address
    public String deleteAddress(int id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return "Address deleted successfully!";
        }
        return "Address not found!";
    }
}
