package com.bluecoding.payment.service;

import com.bluecoding.payment.dto.PaymentInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InfoValidationServiceImpl implements InfoValidationService{

    @Override
    public boolean validatePaymentInfo(PaymentInfoDto dto) {
        boolean expirationDateValid = validateExpirationDate(dto.getExpirationDate());
        boolean validCvv = validateCvv(dto.getCvv(), dto.getCardNumber());
        return expirationDateValid && validCvv;
    }

    private boolean validateExpirationDate(LocalDate expirationDate){
        int year = expirationDate.getYear();
        int month = expirationDate.getMonthValue();
        LocalDate now = LocalDate.now();
        if(year >= now.getYear() && month > now.getMonthValue()){
            return true;
        }
        return false;
    }

    private boolean validateCvv(String cvv, String cardNumber){
        if(cvv.length() == 4 && (cardNumber.startsWith("34") || cardNumber.startsWith("37"))){
            return true;
        }else if(cvv.length() == 3 && !(cardNumber.startsWith("34") || cardNumber.startsWith("37"))){
            return true;
        }
        return false;
    }
}
