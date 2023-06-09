package com.bluecoding.payment.service;

import com.bluecoding.payment.dto.PaymentInfoDto;
import com.bluecoding.payment.validators.CardInfoValidator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class InfoValidationServiceImplTest {

    @Mock
    CardInfoValidator cardInfoValidator;

    @InjectMocks
    InfoValidationServiceImpl infoValidationService;

    @Test
    public void testValidatePaymentInfo_ValidInfo_ReturnsTrue() {
        // Mock the cardInfoValidator methods
        when(cardInfoValidator.validateExpirationDate(any())).thenReturn(true);
        when(cardInfoValidator.validateCvv(any(), any())).thenReturn(true);
        when(cardInfoValidator.luhnValidation(any())).thenReturn(true);

        // Create a PaymentInfoDto object
        String cardNumber = "1234567890";
        String cvv = "123";
        LocalDate expirationDate = LocalDate.now();
        PaymentInfoDto paymentInfo = new PaymentInfoDto();
        paymentInfo.setExpirationDate(expirationDate);
        paymentInfo.setCvv(cvv);
        paymentInfo.setCardNumber(cardNumber);

        // Call the method to be tested
        boolean result = infoValidationService.validatePaymentInfo(paymentInfo);

        // Verify the interactions and assertions
        Assert.assertTrue(result);
        verify(cardInfoValidator).validateExpirationDate(expirationDate);
        verify(cardInfoValidator).validateCvv(cvv, cardNumber);
        verify(cardInfoValidator).luhnValidation(cardNumber);
    }
}