package com.saiDeveloper.E_commerce2_Backend.controller;

import com.saiDeveloper.E_commerce2_Backend.model.User;
import com.saiDeveloper.E_commerce2_Backend.response.AuthResponse;
import com.saiDeveloper.E_commerce2_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService service;
    @PostMapping("/register")
    public AuthResponse register(@RequestBody User user){
        return service.saveUser(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody User user){
        return service.login(user);
    }
}
