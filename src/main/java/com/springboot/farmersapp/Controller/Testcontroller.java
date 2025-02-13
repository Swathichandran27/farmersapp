package com.springboot.farmersapp.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class Testcontroller {
    @GetMapping("/welcome")
    public String getWelcome() {
        return "swathi";
    }
   
    
}
