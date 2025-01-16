package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.exception.OrderException;
import com.saiDeveloper.E_commerce2_Backend.model.Order;
import com.saiDeveloper.E_commerce2_Backend.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService {

    @Autowired
    private OrderRepo repo;

    @Autowired
    private CartServiceImplementation cartService;

    @Autowired
    private ProductService productService;
    @Override
    public Order findById(Long orderId) throws OrderException {
        Order order = repo.findById(orderId).orElse(null);
        if (order != null) {
            return order;
        }
        throw new OrderException("Order not found with id:" + orderId);
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) throws OrderException {
        return List.of();
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long ordeId) throws OrderException {
        return null;
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order canceledOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return List.of();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {

    }
}
