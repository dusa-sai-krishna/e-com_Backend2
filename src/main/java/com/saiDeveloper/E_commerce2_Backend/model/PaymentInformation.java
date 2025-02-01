package com.saiDeveloper.E_commerce2_Backend.model;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Scope("prototype")
public class PaymentInformation {

    @Column(name = "cardholder_name")
    private String cardholderName;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    @Column(name ="cvv")
    private String cvv;
}
