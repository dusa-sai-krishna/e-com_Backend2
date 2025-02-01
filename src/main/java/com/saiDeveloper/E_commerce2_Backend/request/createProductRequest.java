package com.saiDeveloper.E_commerce2_Backend.request;

import com.saiDeveloper.E_commerce2_Backend.model.Size;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component

public class createProductRequest {

    @jakarta.validation.constraints.Size(min = 3,message = "Title must be at least 3 characters long")
    private String title;
    @jakarta.validation.constraints.Size(min = 3, message = "Description must be at least 3 characters long")
    private String description;

    @PositiveOrZero(message="Price must be non-negative")
    @Min(value = 100, message = "Price must be at least 100")
    private int price;
    @PositiveOrZero(message="Price must be non-negative")
    @Min(value = 100, message = "Price must be at least 100")
    private int discountedPrice;
    @PositiveOrZero(message="Percentage must be non-negative")
    @Min(value = 0, message = "Percentage must be at least 0")
    @Max(value = 100, message = "Percentage must be at most 100")
    private int discountPercentage;
    @Min(value =1 , message = "Quantity must be at least 1")
    private int quantity;

    @NotEmpty
    private String brand;
    @NotEmpty
    private String color;

   @NotEmpty(message = "Size cannot be empty")
    private Set<Size> size = new HashSet<>();

   @NotEmpty(message = "Image URL cannot be empty")
    private String imageUrl;

   @NotEmpty(message = "Top Category cannot be empty")
    private String topLavelCategory;
    @NotEmpty(message = "Second Category cannot be empty")
    private String secondLavelCategory;
    @NotEmpty(message = " Third Category cannot be empty")
    private String thirdLavelCategory;
}
