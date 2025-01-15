package com.saiDeveloper.E_commerce2_Backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Scope("prototype")
@Component
public class AuthResponse {

    private String jwt;
    private String message;


}
