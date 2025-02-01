package com.saiDeveloper.E_commerce2_Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Scope;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_item")
@Scope("prototype")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    private String size;

    private Integer quantity;

    private Integer price;

    @Column(name = "discounted_price")
    private Integer discountedPrice;

    @Column(name = "user_id")
    private Long userId;
}















