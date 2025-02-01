package com.saiDeveloper.E_commerce2_Backend.controller;

import com.saiDeveloper.E_commerce2_Backend.exception.AddressException;
import com.saiDeveloper.E_commerce2_Backend.exception.UserException;
import com.saiDeveloper.E_commerce2_Backend.model.Address;
import com.saiDeveloper.E_commerce2_Backend.model.User;
import com.saiDeveloper.E_commerce2_Backend.service.AddressService;
import com.saiDeveloper.E_commerce2_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService service;

    @PostMapping("/address")
    public ResponseEntity<Address> addAddress(
            @RequestBody Address address,
            @RequestHeader("Authorization") String jwt
    ) throws UserException {
        User user = userService.findByJWT(jwt);
        address.setUser(user);
        return new ResponseEntity<>(service.saveAddress(address), HttpStatus.CREATED);
    }


    @GetMapping("/")
    public ResponseEntity<List<Address>> getAllAddressesOfUser(
            @RequestHeader("Authorization") String jwt
    ) throws UserException {
        User user = userService.findByJWT(jwt);
        return new ResponseEntity<>(service.getAllAddressOfUser(user.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> deleteAddress(
            @PathVariable("addressId") Long addressId,
            @RequestHeader("Authorization") String jwt
    ) throws UserException, AddressException {
        User user = userService.findByJWT(jwt);
        service.deleteAddress(user.getId(), addressId);
        return new ResponseEntity<>("Address deleted successfully", HttpStatus.OK);
    }


}
