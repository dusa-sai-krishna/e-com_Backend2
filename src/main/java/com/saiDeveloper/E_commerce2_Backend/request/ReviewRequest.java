package com.saiDeveloper.E_commerce2_Backend.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {

    @NotNull(message = "Product id cannot be empty")
    private Long productId;
    @NotEmpty(message = "Review cannot be empty")
    @Size(min = 2, max = 500, message = "Review must be between 1 and 500 characters")
    private String review;

}
