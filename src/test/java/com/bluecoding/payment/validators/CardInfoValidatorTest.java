package com.bluecoding.payment.validators;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class CardInfoValidatorTest {

    private CardInfoValidator cardInfoValidator = new CardInfoValidator();

    @Test
    void givenAValidCardNumber_whenLuhnValidation_thenReturnTrue() {
        boolean validCardNumber = cardInfoValidator.luhnValidation("45320151128336244");
        Assert.assertTrue(validCardNumber);
    }

    @Test
    void givenAnInvalidCardNumber_whenLuhnValidation_thenReturnFalse() {
        boolean validCardNumber = cardInfoValidator.luhnValidation("4532015112833624");
        Assert.assertFalse(validCardNumber);
    }

    @Test
    void validateExpirationDate() {
    }

    @Test
    void validateCvv() {
    }
}