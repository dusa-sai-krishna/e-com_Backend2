package com.saiDeveloper.E_commerce2_Backend.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

    @NotEmpty(message = "First name is required")
    private String firstName;
    private String lastName;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number format")
    private String mobile;
}
