package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.exception.OrderException;
import com.saiDeveloper.E_commerce2_Backend.exception.UserException;
import com.saiDeveloper.E_commerce2_Backend.model.*;
import com.saiDeveloper.E_commerce2_Backend.repo.OrderRepo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImplementation implements OrderService {

    @Autowired
    private OrderRepo repo;
    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartServiceImplementation cartService;

    @Autowired
    private OrderItemService orderItemService;
    @Override
    public Order createOrder(User user, Address shippingAddress) throws UserException{

        shippingAddress.setUser(user);
        if(!addressService.isExists(shippingAddress.getStreetAddress(),shippingAddress.getZipCode())) {
            log.info("Adding shipping address to user");
            user.getAddress().add(shippingAddress);
            user = userService.updateUser(user);
            addressService.saveAddress(shippingAddress);
        }
        else log.info("Shipping address already exists for user");

        Order order = new Order();
//create and save order then, add saved orderItems to it
        Cart cart = cartService.aggregateCost(user.getId()); // calculates total cart value

        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
//        order.setDeliveryDate(LocalDateTime.now());
        order.setShippingAddress(shippingAddress);

        order.setPaymentDetails(new PaymentDetails());
        order.getPaymentDetails().setStatus("PENDING");

        order.setTotalPrice(cart.getTotalPrice());
        order.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
        order.setDiscount(cart.getDiscount());
        order.setOrderStatus("PENDING");
        order.setTotalItems(cart.getTotalItems());
        order.setCreatedAt(LocalDateTime.now());
        log.info("Order object for user {} has been created successfully",user.getId());
        // Let's add order attribute to each orderItem and save thhem to Db


        Order savedOrder = repo.save(order);
        List<OrderItem> orderItems = new ArrayList<>();

        log.info("Mapping each cartItem with a corresponding orderItem");
        for(CartItem cartItem:cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(savedOrder);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setSize(cartItem.getSize());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setDiscountedPrice(cartItem.getDiscountedPrice());
            orderItem.setUserId(user.getId());

            orderItems.add(orderItemService.createOrderItem(orderItem));
        }

        savedOrder.setOrderItems(orderItems);

        savedOrder = repo.save(savedOrder);

        log.info("OrderItem object collection for order {} has been created successfully",savedOrder.getId());

        return savedOrder;

    }

    @Override
    public Order findById(Long orderId) throws OrderException {
        Order order = repo.findById(orderId).orElse(null);
        if (order != null) {
            log.info("Order found with id:{}", orderId);
            return order;
        }
        log.info("Order not found with id:{}", orderId);
        throw new OrderException("Order not found with id:" + orderId);
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        log.info("Getting all orders for user with id:{}",userId);
        return repo.getAllOrdersByUserId(userId);
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        Order order = findById(orderId);
        order.setOrderStatus("PLACED");

    order.getPaymentDetails().setStatus("COMPLETED");
        log.info("Order placed and payment completed with id:{}",orderId);
        return repo.save(order);

    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        Order order = findById(orderId);
        order.setOrderStatus("CONFIRMED");
        log.info("Order confirmed with id:{}",orderId);
        return repo.save(order);
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order = findById(orderId);
        order.setOrderStatus("SHIPPED");
        log.info("Order shipped with id:{}",orderId);
        return repo.save(order);
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        Order order = findById(orderId);
        order.setOrderStatus("DELIVERED");
        log.info("Order delivered with id:{}",orderId);
        return repo.save(order);
    }

    @Override
    public Order canceledOrder(Long orderId) throws OrderException {
        Order order = findById(orderId);
        order.setOrderStatus("CANCELLED");
        log.info("Order cancelled with id:{}",orderId);
        return repo.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        log.info("Getting all orders");
        return repo.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        findById(orderId);
        log.info("Order deleted with id:{}",orderId);
        repo.deleteById(orderId);
    }

    @Override
    public List<Order> findAll() {
        return repo.findAll();
    }
}
