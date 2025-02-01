package com.saiDeveloper.E_commerce2_Backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.context.annotation.Scope;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="address")
@Scope("prototype")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "First name is required")
    private String firstName;
    private String lastName;
    @Size(min = 10, max = 200, message = "Address must be between 10 and 200 characters")
    private String streetAddress;
    @Size(min=3, max =50, message = "City must be between 3 and 50 characters")
    private String city;
    @Size(min=2, max =50, message = "State must be between 2 and 50 characters")
    private String state;
    @Size(min=5, max =10, message = "Zip code must be between 5 and 10 characters")
    private String zipCode;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
