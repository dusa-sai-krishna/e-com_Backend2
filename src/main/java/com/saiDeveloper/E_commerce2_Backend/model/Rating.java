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
@Table(name="rating")
@Scope("prototype")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long id;

    @Column(name = "rating")
    private double rating;

    @Column(name = "review")
    private String review;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false) // It shouldn't be null
    @JsonIgnore // Ignore this field while json serializing
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable=false) // It shouldn't be null
    private User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
