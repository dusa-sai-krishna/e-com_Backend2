package com.saiDeveloper.E_commerce2_Backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
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

    @Column(name = "discount_percentage")
    private int discountPercentage;

    private int quantity;
    private String brand;
    private String color;



    @Column(name="image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    @ToString.Exclude
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();


    @Column(name="num_ratings")
    private int numRatings;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @ElementCollection
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "size_name")),
            @AttributeOverride(name = "quantity", column = @Column(name = "size_quantity"))
    })
    @CollectionTable(name = "product_size", joinColumns = @JoinColumn(name = "product_id"))
    private Set<Size> size = new HashSet<>();
}
