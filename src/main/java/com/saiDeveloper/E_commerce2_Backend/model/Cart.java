package com.saiDeveloper.E_commerce2_Backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
@Scope("prototype")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
    @Column(name = "cart_items")
    @ToString.Exclude
    private List<CartItem> cartItems = new ArrayList<>();

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "total_item")
    private Integer totalItems;

    @Column(name = "total_discounted_price")
    private Integer totalDiscountedPrice;

    private Integer discount;


    }






















