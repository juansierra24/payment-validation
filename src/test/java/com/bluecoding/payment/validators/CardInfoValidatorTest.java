package com.bluecoding.payment.validators;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class CardInfoValidatorTest {

    private CardInfoValidator cardInfoValidator = new CardInfoValidator();

    @Test
    void givenAValidCardNumber_whenLuhnValidation_thenReturnTrue() {
        String cardNumber = "45320151128336244";
        boolean validCardNumber = cardInfoValidator.luhnValidation(cardNumber);
        Assert.assertTrue(validCardNumber);
    }

    @Test
    void givenAnInvalidCardNumber_whenLuhnValidation_thenReturnFalse() {
        String cardNumber = "4532015112833624";
        boolean validCardNumber = cardInfoValidator.luhnValidation(cardNumber);
        Assert.assertFalse(validCardNumber);
    }

    @Test
    void givenTheCurrentDateTime_whenValidateExpirationDate_thenReturnFalse() {
        LocalDate now = LocalDate.now();
        boolean validDate = cardInfoValidator.validateExpirationDate(now);
        Assert.assertFalse(validDate);
    }

    @Test
    void givenAPastMonth_whenValidateExpirationDate_thenReturnFalse() {
        LocalDate twoMonthsAgo = LocalDate.now().minusMonths(2);
        boolean validDate = cardInfoValidator.validateExpirationDate(twoMonthsAgo);
        Assert.assertFalse(validDate);
    }

    @Test
    void givenAFutureMonth_whenValidateExpirationDate_thenReturnTrue() {
        LocalDate twoMonthsAhead = LocalDate.now().plusMonths(2);
        boolean validDate = cardInfoValidator.validateExpirationDate(twoMonthsAhead);
        Assert.assertTrue(validDate);
    }

    @Test
    void givenA4DigitsCvvAndNonAmexCardNumber_whenValidateCvv_thenReturnFalse() {
        String cvv = "1234";
        String cardNumber = "1234567891234123";
        boolean validCvv = cardInfoValidator.validateCvv(cvv, cardNumber);
        Assert.assertFalse(validCvv);
    }

    @Test
    void givenA4DigitsCvvAndAmexCardNumber_whenValidateCvv_thenReturnTrue() {
        String cvv = "1234";
        String cardNumber = "3434567891234123";
        boolean validCvv = cardInfoValidator.validateCvv(cvv, cardNumber);
        Assert.assertTrue(validCvv);
    }

    @Test
    void givenA3DigitsCvvAndNonAmexCardNumber_whenValidateCvv_thenReturnTrue() {
        String cvv = "123";
        String cardNumber = "1234567891234123";
        boolean validCvv = cardInfoValidator.validateCvv(cvv, cardNumber);
        Assert.assertTrue(validCvv);
    }

    @Test
    void givenA3DigitsCvvAndAmexCardNumber_whenValidateCvv_thenReturnFalse() {
        String cvv = "123";
        String cardNumber = "3734567891234123";
        boolean validCvv = cardInfoValidator.validateCvv(cvv, cardNumber);
        Assert.assertFalse(validCvv);
    }

}