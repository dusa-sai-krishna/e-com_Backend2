package com.saiDeveloper.E_commerce2_Backend.controller;

import com.saiDeveloper.E_commerce2_Backend.exception.UserException;
import com.saiDeveloper.E_commerce2_Backend.model.User;
import com.saiDeveloper.E_commerce2_Backend.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User")
public class UserController {

    @Autowired
    private UserService service;



    @DeleteMapping("/delete")
    public String delete(@RequestBody User user){
        return service.deleteUser(user);
    }

    @GetMapping("/profile")
    public User profile(@RequestHeader("Authorization") String jwt) throws UserException {
        return service.findByJWT(jwt);
    }


}

