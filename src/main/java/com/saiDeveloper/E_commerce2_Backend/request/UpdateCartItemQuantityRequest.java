package com.saiDeveloper.E_commerce2_Backend.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartItemQuantityRequest {

    @NotNull(message = "cartItem Id is null")
    private Long cartItemId;
    @Range(min = 1, max = 50,message = "Quantity should be atleast 1 and atmost 50")
    private Integer quantity;

}
