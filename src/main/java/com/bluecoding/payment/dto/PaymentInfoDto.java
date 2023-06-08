package com.bluecoding.payment.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class PaymentInfoDto {
    @NotBlank(message = "Card holder name is required")
    private String cardName;
    @Size(min = 16, max = 19, message = "Card number must be between 16 and 19 digits")
    private String cardNumber;
    @Size(min = 3, max = 4, message = "CVV number must be between 3 and 4 digits")
    private String cvv;
    @NotNull
    private LocalDate expirationDate;
}
