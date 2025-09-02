package com.saiDeveloper.E_commerce2_Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_item")
@Scope("prototype")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // This is because an order item is a product of specific color and size
    //There can be many order items each with a color or size variation of a product.
    @ManyToOne
    @JoinColumn(name = "product_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    private String size;

    private Integer quantity;

    private Integer price;

    @Column(name = "discounted_price")
    private Integer discountedPrice;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;


}
