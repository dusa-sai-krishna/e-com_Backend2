package com.saiDeveloper.E_commerce2_Backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders") // order is a reserved keyword in SQL. Can't be a table name
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

    @OneToMany
    @PrimaryKeyJoinColumn(name = "order_items")
    @ToString.Exclude
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "shipping_address")
    private Address shippingAddress;

    @Embedded
//    @ElementCollection
//    @CollectionTable(name = "payment_details", joinColumns = @JoinColumn(name = "order_id"))
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
