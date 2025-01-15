package com.saiDeveloper.E_commerce2_Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="review")
@Scope("prototype")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id")
    private Long id;

    private String review;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable=false) // It shouldn't be null
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable=false) // It shouldn't be null
    @JsonIgnore
    private User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
