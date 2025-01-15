package com.saiDeveloper.E_commerce2_Backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
@Scope("prototype")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    private String title;
    private String description;
    private int price;

    @Column(name = "discounted_price")
    private int discountedPrice;

    private int quantity;
    private String brand;
    private String color;

    @Embedded
    @ElementCollection
    @CollectionTable(name = "product_sizes", joinColumns = @JoinColumn(name = "product_id"))
    private Set<Size> sizes = new HashSet<>();

    @Column(name="image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @Column(name="num_ratings")
    private int numRatings;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name="created_at")
    private LocalDateTime createdAt;

}
