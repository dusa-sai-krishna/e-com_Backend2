package com.saiDeveloper.E_commerce2_Backend.controller;

import com.saiDeveloper.E_commerce2_Backend.exception.OrderException;
import com.saiDeveloper.E_commerce2_Backend.exception.UserException;
import com.saiDeveloper.E_commerce2_Backend.model.Address;
import com.saiDeveloper.E_commerce2_Backend.model.Order;
import com.saiDeveloper.E_commerce2_Backend.model.User;
import com.saiDeveloper.E_commerce2_Backend.service.OrderService;
import com.saiDeveloper.E_commerce2_Backend.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order", description = "Order APIs")
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Order> createOrder(
            @RequestBody Address shippingAddress,
            @RequestHeader("Authorization") String jwt) throws UserException, OrderException {
        User user=userService.findByJWT(jwt);
        Order order = service.createOrder(user,shippingAddress);
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity <List<Order>> usersOrderHistory(
            @RequestHeader("Authorization") String jwt
    ) throws UserException {
        User user = userService.findByJWT(jwt);
        List<Order> orders = service.usersOrderHistory(user.getId());
        return new ResponseEntity<List<Order>>(orders, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> findById(@PathVariable("orderId") Long orderId) throws OrderException {
        Order order = service.findById(orderId);
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }



}

















