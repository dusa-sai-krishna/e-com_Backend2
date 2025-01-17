package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.model.OrderItem;
import com.saiDeveloper.E_commerce2_Backend.repo.OrderItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImplementation implements OrderItemService{

    @Autowired
    private OrderItemRepo repo;
    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {


        return repo.save(orderItem);
    }
}
