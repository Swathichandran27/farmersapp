package com.springboot.farmersapp.Service;

import com.springboot.farmersapp.Entity.Address;
import com.springboot.farmersapp.Entity.Users;
import com.springboot.farmersapp.Repository.AddressRepository;
import com.springboot.farmersapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public Users createUser(Users user) {
        if (user.getAddress() == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }

        // Save the address first
        Address address = user.getAddress();
        address = addressRepository.save(address); 
        user.setAddress(address);

        // Save the user
        return userRepository.save(user);
    }

    // GET All Users
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    // GET User by ID
     public Users getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    // UPDATE User
    @Transactional
    public Users updateUser(int id, Users updatedUser) {
        Optional<Users> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            Users user = existingUserOpt.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());

           
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                user.setPassword(updatedUser.getPassword());
            }

            
            if (updatedUser.getAddress() != null) {
                Address updatedAddress = updatedUser.getAddress();
                Address existingAddress = user.getAddress();

                if (existingAddress != null) {
                    existingAddress.setStreet(updatedAddress.getStreet());
                    existingAddress.setCity(updatedAddress.getCity());
                    existingAddress.setState(updatedAddress.getState());
                    existingAddress.setZipCode(updatedAddress.getZipCode());
                    existingAddress.setCountry(updatedAddress.getCountry());
                    addressRepository.save(existingAddress);
                } else {
                    Address savedAddress = addressRepository.save(updatedAddress);
                    user.setAddress(savedAddress);
                }
            }

            return userRepository.save(user);
        }
        return null;
    }

    // DELETE User
    public String deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User deleted successfully!";
        }
        return "User not found!";
    }
}
