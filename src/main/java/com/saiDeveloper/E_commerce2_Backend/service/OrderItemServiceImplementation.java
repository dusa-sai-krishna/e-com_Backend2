package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.model.OrderItem;
import com.saiDeveloper.E_commerce2_Backend.repo.OrderItemRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderItemServiceImplementation implements OrderItemService{

    @Autowired
    private OrderItemRepo repo;
    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        log.info("Order item with id:{} created successfully",orderItem.getId());
        return repo.save(orderItem);
    }
}
