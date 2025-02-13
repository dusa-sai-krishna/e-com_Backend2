package com.saiDeveloper.E_commerce2_Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
@Scope("prototype")
public class User {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @Column(nullable = false, columnDefinition = "varchar(255) default 'USER'")
    private String role;

    @NotNull
    private String mobile;
//    This code snippet defines a one-to-many relationship between a `User` and multiple `Address` entities in a database.
//
//            * `@OneToMany` indicates that one user can have multiple addresses.
//            * `mappedBy = "user"` specifies that the relationship is owned by the `Address` entity, which has a field named `user` that references the `User` entity.
//* `cascade = CascadeType.ALL` means that any changes to the `User` entity (e.g., saving, updating, deleting) will be cascaded to the associated `Address` entities.
//
//    In other words, when you save a `User`, all their associated `Address` entities will also be saved.

   @Column(name = "address")
   @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
   @ToString.Exclude
   private List<Address> address = new ArrayList<>();

   @Embedded //indicates that payment_information is a value object but not an entity
   @ElementCollection // indicates it is a collection of value objects
   @CollectionTable(name = "payment_information", joinColumns = @JoinColumn(name = "user_id"))
   //indicates the name of the table to be used for storing payment_information as well foreign key to join with user
   private List<PaymentInformation> paymentInformation = new ArrayList<>();

//    @JsonIgnore indicates that this field should be excluded when serializing the User object to JSON.
   @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
   @JsonIgnore
   @ToString.Exclude
   private List<Rating> ratings = new ArrayList<>();

   @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
   @ToString.Exclude
   private List<Review> reviews = new ArrayList<>();

   @Column(name = "created_at")
   private LocalDateTime createdAt;

}
