package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.exception.OrderException;
import com.saiDeveloper.E_commerce2_Backend.exception.UserException;
import com.saiDeveloper.E_commerce2_Backend.model.*;
import com.saiDeveloper.E_commerce2_Backend.repo.AddressRepo;
import com.saiDeveloper.E_commerce2_Backend.repo.OrderRepo;
import com.saiDeveloper.E_commerce2_Backend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
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
    private ProductService productService;
    @Autowired
    private OrderItemService orderItemService;
    @Override
    public Order createOrder(User user, Address shippingAddress) throws UserException, OrderException {

        Order order = new Order();



        addressService.saveAddress(shippingAddress);
        user.getAddress().add(shippingAddress);
        user = userService.updateUser(user);

        Cart cart = cartService.findUserCart(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();


        for(CartItem cartItem:cart.getCartItems()){
           OrderItem orderItem = new OrderItem();

           orderItem.setOrder(order);
           orderItem.setProduct(cartItem.getProduct());
           orderItem.setSize(cartItem.getSize());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setDiscountedPrice(cartItem.getDiscountedPrice());
            orderItem.setUserId(user.getId());

            orderItems.add(orderItem);
            // These can be saved to Db only after adding order attribute
        }

        order.setUser(user);
        order.setOrderItems(orderItems);
        order.setOrderDate(LocalDateTime.now());
//        order.setDeliveryDate(LocalDateTime.now());
        order.setShippingAddress(shippingAddress);
        order.getPaymentDetails().setStatus("PENDING");
        order.setTotalPrice(cart.getTotalPrice());
        order.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
        order.setDiscount(cart.getDiscount());
        order.setOrderStatus("PENDING");
        order.setTotalItems(cart.getTotalItems());
        order.setCreatedAt(LocalDateTime.now());

        // Let's add order attribute to each orderItem and save thhem to Db
        for(OrderItem orderItem:order.getOrderItems()){
            orderItem.setOrder(order);
            orderItemService.createOrderItem(orderItem);
        }

        return repo.save(order);


    }

    @Override
    public Order findById(Long orderId) throws OrderException {
        Order order = repo.findById(orderId).orElse(null);
        if (order != null) {
            return order;
        }
        throw new OrderException("Order not found with id:" + orderId);
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        return repo.getAllOrdersByUserId(userId);
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        Order order = findById(orderId);
        order.setOrderStatus("PLACED");
    order.getPaymentDetails().setStatus("COMPLETED");

        return repo.save(order);

    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        Order order = findById(orderId);
        order.setOrderStatus("CONFIRMED");
        return repo.save(order);
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order = findById(orderId);
        order.setOrderStatus("SHIPPED");
        return repo.save(order);
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        Order order = findById(orderId);
        order.setOrderStatus("DELIVERED");
        return repo.save(order);
    }

    @Override
    public Order canceledOrder(Long orderId) throws OrderException {
        Order order = findById(orderId);
        order.setOrderStatus("CANCELLED");
        return repo.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return repo.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        Order order = findById(orderId);
        repo.deleteById(orderId);
    }
}
