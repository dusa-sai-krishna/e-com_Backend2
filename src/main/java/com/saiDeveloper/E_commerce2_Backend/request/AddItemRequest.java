package com.saiDeveloper.E_commerce2_Backend.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddItemRequest {

    @NotNull(message = "Product id cannot be empty")
    private Long productId;
    @NotEmpty(message = "Size cannot be empty")
    private String size;
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;



}




















