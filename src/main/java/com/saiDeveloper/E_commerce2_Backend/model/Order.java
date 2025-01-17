package com.saiDeveloper.E_commerce2_Backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order")
@Entity
@Scope("prototype")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @Column(name = "order_items")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @OneToOne
    @Column(name = "shipping_address")
    private Address shippingAddress;

    @Embedded
    @ElementCollection
    @CollectionTable(name = "payment_details", joinColumns = @JoinColumn(name = "order_id"))
    private PaymentDetails paymentDetails;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "total_discounted_price")
    private Integer totalDiscountedPrice;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "total_items")
    private Integer totalItems;

    private LocalDateTime createdAt;
}
