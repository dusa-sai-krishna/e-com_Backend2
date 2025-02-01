package com.saiDeveloper.E_commerce2_Backend.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequest {

    @NotNull(message = "Product id is required")
    private Long productId;
    @NotEmpty
    @Size(min = 1, max = 5, message = "Rating must be between 1 and 5 inclusive")
    private Integer rating;

}
