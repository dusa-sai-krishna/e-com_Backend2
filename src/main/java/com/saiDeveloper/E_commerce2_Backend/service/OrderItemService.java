package com.saiDeveloper.E_commerce2_Backend.service;


import com.saiDeveloper.E_commerce2_Backend.exception.OrderItemException;
import com.saiDeveloper.E_commerce2_Backend.model.OrderItem;

public interface OrderItemService {

     OrderItem createOrderItem(OrderItem orderItem);

     OrderItem findOrderItemById(Long id) throws OrderItemException;

     void deleteOrderItem(Long id) throws OrderItemException;
}
