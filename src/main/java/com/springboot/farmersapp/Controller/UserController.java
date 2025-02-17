package com.springboot.farmersapp.Controller;

import com.springboot.farmersapp.Entity.Users;
import com.springboot.farmersapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // CREATE User
    @PostMapping("/add")
    public Users createUser(@RequestBody Users user) {
        return userService.createUser(user);
    }

    // GET All Users
    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    // GET User by ID
    @GetMapping("/{id}")
    public Users getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    // UPDATE User
    @PutMapping("/{id}")
    public Users updateUser(@PathVariable int id, @RequestBody Users user) {
        return userService.updateUser(id, user);
    }

    // DELETE User
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}
