package com.saiDeveloper.E_commerce2_Backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Scope("prototype")
public class PaymentDetails {

    private String paymentMethod;

    private String status;

    private String paymentId;

    private String razorpayPaymentLinkId;

    private String razorpayPaymentLinkReferenceId;

    private String razorpayPaymentLinkStatus;

    private String razorpayPaymentId;
}
