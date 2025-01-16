package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.exception.OrderException;
import com.saiDeveloper.E_commerce2_Backend.model.Order;

import java.util.List;

public interface OrderService {

    public Order findById(Long orderId) throws OrderException;

    public List<Order> usersOrderHistory(Long userId) throws OrderException;

    public Order placedOrder(Long orderId) throws OrderException;

    public Order confirmedOrder(Long orderId) throws OrderException;

    public Order shippedOrder(Long ordeId) throws OrderException;

    public Order deliveredOrder(Long orderId) throws OrderException;

    public Order canceledOrder(Long orderId) throws OrderException;

    public List<Order> getAllOrders();

    public void deleteOrder(Long orderId) throws OrderException;

}
















