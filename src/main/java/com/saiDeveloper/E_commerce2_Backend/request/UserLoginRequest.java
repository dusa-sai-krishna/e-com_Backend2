package com.saiDeveloper.E_commerce2_Backend.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {

    @NotEmpty
    @Schema(example = "This is user's email")
    private String email;
    @NotEmpty
    @Schema(example = "This is user's password")
    private String password;
}
