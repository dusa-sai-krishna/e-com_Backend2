package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.exception.OrderItemException;
import com.saiDeveloper.E_commerce2_Backend.model.Order;
import com.saiDeveloper.E_commerce2_Backend.model.OrderItem;
import com.saiDeveloper.E_commerce2_Backend.repo.OrderItemRepo;
import com.saiDeveloper.E_commerce2_Backend.repo.OrderRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderItemServiceImplementation implements OrderItemService{

    @Autowired
    private OrderItemRepo repo;
    @Autowired
    private OrderRepo orderRepo;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        log.info("Order item with id:{} created successfully",orderItem.getId());
        return repo.save(orderItem);
    }



    public OrderItem findOrderItemById(Long id) throws OrderItemException{
        return repo.findById(id).orElseThrow(() -> new OrderItemException("Order item with id:" + id + " not found"));
    }

    @Override
    public void deleteOrderItem(Long id) throws OrderItemException {
        OrderItem orderItem = findOrderItemById(id);
        Order order = orderItem.getOrder();
        order.getOrderItems().remove(orderItem);
        orderRepo.save(order);
        repo.delete(orderItem);
    }
}
