package com.bluecoding.payment.service;

import com.bluecoding.payment.dto.PaymentInfoDto;
import com.bluecoding.payment.validators.CardInfoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InfoValidationServiceImpl implements InfoValidationService{

    private final CardInfoValidator cardInfoValidator;

    @Override
    public boolean validatePaymentInfo(PaymentInfoDto dto) {
        boolean expirationDateValid = cardInfoValidator.validateExpirationDate(dto.getExpirationDate());
        boolean validCvv = cardInfoValidator.validateCvv(dto.getCvv(), dto.getCardNumber());
        boolean validCardNumber = cardInfoValidator.luhnValidation(dto.getCardNumber());
        return expirationDateValid && validCvv && validCardNumber;
    }
}
