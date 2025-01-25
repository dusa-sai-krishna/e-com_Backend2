package com.saiDeveloper.E_commerce2_Backend.controller;
import com.saiDeveloper.E_commerce2_Backend.request.UserLoginRequest;
import com.saiDeveloper.E_commerce2_Backend.request.UserRegisterRequest;
import com.saiDeveloper.E_commerce2_Backend.exception.UserException;
import com.saiDeveloper.E_commerce2_Backend.response.AuthResponse;
import com.saiDeveloper.E_commerce2_Backend.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody UserRegisterRequest user) throws UserException {
        return service.saveUser(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody UserLoginRequest user) throws UserException {
        return service.login(user);
    }
}
