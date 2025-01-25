package com.saiDeveloper.E_commerce2_Backend.request;

import com.saiDeveloper.E_commerce2_Backend.model.Size;
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

    private String title;
    private String description;
    private int price;

    private int discountedPrice;
    private int discountPercentage;
    private int quantity;

    private String brand;
    private String color;

    private Set<Size> size = new HashSet<>();

    private String imageUrl;

    private String topLavelCategory;
    private String secondLavelCategory;
    private String thirdLavelCategory;
}
