package com.saiDeveloper.E_commerce2_Backend.controller;

import com.saiDeveloper.E_commerce2_Backend.model.User;
import com.saiDeveloper.E_commerce2_Backend.response.AuthResponse;
import com.saiDeveloper.E_commerce2_Backend.service.JwtService;
import com.saiDeveloper.E_commerce2_Backend.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "User")
public class UserController {

    @Autowired
    private UserService service;

//    @PutMapping("/api/update")
//    public String update(@RequestBody User user){
//        return service.updateUser(user);
//    }

    @DeleteMapping("/api/delete")
    public String delete(@RequestBody User user){
        return service.deleteUser(user);
    }


}

