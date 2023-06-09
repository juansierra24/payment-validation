package com.bluecoding.payment.validators;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CardInfoValidator {

    /**
     * Luhn's algorithm to validate whether a card number is valid or not
     * @param number Card number to be validated
     * @return true if card number is valid otherwise false
     */
    public boolean luhnValidation(String number) {
        int sum = 0;
        boolean alternate = false;
        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit % 10) + 1;
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        return sum % 10 == 0;
    }

    /**
     * Validates expiration date is greater than current month
     * @param expirationDate Card expiration date
     * @return true if card has not expired otherwise false
     */
    public boolean validateExpirationDate(LocalDate expirationDate){
        int year = expirationDate.getYear();
        int month = expirationDate.getMonthValue();
        LocalDate now = LocalDate.now();
        if(year >= now.getYear() && month > now.getMonthValue()){
            return true;
        }
        return false;
    }

    /**
     * Validates if cvv is valid regarding that 34 and 37 are starting numbers used for AMEX
     * cards and these cards should have 4 digits cvv otherwise it should be 3 digits long.
     * @param cvv cvv number
     * @param cardNumber card number
     * @return true if cvv is valid otherwise false
     */
    public boolean validateCvv(String cvv, String cardNumber){
        if(cvv.length() == 4 && (cardNumber.startsWith("34") || cardNumber.startsWith("37"))){
            return true;
        }else if(cvv.length() == 3 && !(cardNumber.startsWith("34") || cardNumber.startsWith("37"))){
            return true;
        }
        return false;
    }


}
